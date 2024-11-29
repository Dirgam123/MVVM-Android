package com.example.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.CalculatorModel;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorModel calculatorModel = new CalculatorModel();
    private final MutableLiveData<String> result = new MutableLiveData<>();

    public LiveData<String> getResult() {
        return result;
    }

    public void performCalculation(double number1, double number2, String operation) {
        try {
            double res;
            switch (operation) {
                case "+":
                    res = calculatorModel.add(number1, number2);
                    break;
                case "-":
                    res = calculatorModel.subtract(number1, number2);
                    break;
                case "*":
                    res = calculatorModel.multiply(number1, number2);
                    break;
                case "/":
                    res = calculatorModel.divide(number1, number2);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
            result.setValue("Result: " + res);
        } catch (ArithmeticException e) {
            result.setValue(e.getMessage());
        } catch (Exception e) {
            result.setValue("Error: " + e.getMessage());
        }
    }
}
