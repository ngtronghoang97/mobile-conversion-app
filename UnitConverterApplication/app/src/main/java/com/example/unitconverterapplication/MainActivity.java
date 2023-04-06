package com.example.unitconverterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText valueEditText;
    private Button convertButton;
    private TextView resultTextView;

    private String[] lengthUnits, weightUnits, temperatureUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        valueEditText = findViewById(R.id.valueEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Load unit arrays from resources
        lengthUnits = getResources().getStringArray(R.array.length_units);
        weightUnits = getResources().getStringArray(R.array.weight_units);
        temperatureUnits = getResources().getStringArray(R.array.temperature_units);

        // Populate source and destination unit spinners with data
        List<CharSequence> allUnits = new ArrayList<>();
        allUnits.addAll(Arrays.asList(weightUnits));
        allUnits.addAll(Arrays.asList(lengthUnits));
        allUnits.addAll(Arrays.asList(temperatureUnits));

        ArrayAdapter<CharSequence> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allUnits);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(unitAdapter);
        destinationUnitSpinner.setAdapter(unitAdapter);

        // Set click listener for convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    // Method to perform conversion
    private void convert() {
        // Get selected units, input value, and perform conversion
        String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
        String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
        double inputValue = Double.parseDouble(valueEditText.getText().toString());
        double convertedValue = 0.0;

        //Validate input value
        if(valueEditText.getText().toString().isEmpty()) {
            //Show error message if input value is empty
            valueEditText.setError("Please enter a value");
        } else {
            try {
                if(sourceUnit.equals(destinationUnit)){
                    resultTextView.setText("Source and destination units cannot be the same");
                } else {
                    // Perform conversion logic based on selected units
                    /*if (sourceUnit.equals("inch") && destinationUnit.equals("cm")) {
                    convertedValue = inputValue * 2.54;
                    } else if (sourceUnit.equals("foot") && destinationUnit.equals("cm")) {
                    convertedValue = inputValue * 30.48;
                    }*/
                    switch(sourceUnit) {
                        case "inch":
                            switch(destinationUnit) {
                                case "cm":
                                    convertedValue = inputValue * 2.54;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "foot":
                            switch(destinationUnit) {
                                case "cm":
                                    convertedValue = inputValue * 30.48;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "yard":
                            switch(destinationUnit) {
                                case "cm":
                                    convertedValue = inputValue * 91.44;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "mile":
                            switch(destinationUnit) {
                                case "km":
                                    convertedValue = inputValue * 1.60934;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "pound":
                            switch(destinationUnit) {
                                case "kg":
                                    convertedValue = inputValue * 0.453592;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "ounce":
                            switch(destinationUnit) {
                                case "g":
                                    convertedValue = inputValue * 28.3495;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "ton":
                            switch(destinationUnit) {
                                case "kg":
                                    convertedValue = inputValue * 907.185;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "Celsius":
                            switch(destinationUnit) {
                                case "Fahrenheit":
                                    convertedValue = (inputValue * 1.8) + 32;
                                    break;
                                case "Kelvin":
                                    convertedValue = inputValue + 273.15;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "Fahrenheit":
                            switch(destinationUnit) {
                                case "Celsius":
                                    convertedValue = (inputValue - 32) / 1.8;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        case "Kelvin":
                            switch(destinationUnit) {
                                case "Celsius":
                                    convertedValue = inputValue - 273.15;
                                    break;
                                default:
                                    resultTextView.setText("Invalid input is entered.");
                            }
                            break;
                        default:
                            resultTextView.setText("Invalid input is entered.");
                    }
                    // Update result text view with converted value
                    resultTextView.setText(String.valueOf(convertedValue));
                }
            } catch (NumberFormatException e) {
                //Show error message if input value is not a valid number
                valueEditText.setError("Please enter a valid number");
            }
        }

    }
}