package com.example.mvvm.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.viewmodel.CalculatorViewModel;

public class MainActivity extends AppCompatActivity {

    private CalculatorViewModel calculatorViewModel;

    private EditText inputNumber1, inputNumber2;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

        inputNumber1 = findViewById(R.id.input_number1);
        inputNumber2 = findViewById(R.id.input_number2);
        resultText = findViewById(R.id.text_result);

        calculatorViewModel.getResult().observe(this, result -> {
            resultText.setText(result);
        });

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnSubtract = findViewById(R.id.btn_subtract);
        Button btnMultiply = findViewById(R.id.btn_multiply);
        Button btnDivide = findViewById(R.id.btn_divide);

        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSubtract.setOnClickListener(v -> calculate("-"));
        btnMultiply.setOnClickListener(v -> calculate("*"));
        btnDivide.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String operation) {
        try {
            double number1 = Double.parseDouble(inputNumber1.getText().toString());
            double number2 = Double.parseDouble(inputNumber2.getText().toString());

            calculatorViewModel.performCalculation(number1, number2, operation);

        } catch (NumberFormatException e) {
            resultText.setText("Please enter valid numbers");
        }
    }
}
