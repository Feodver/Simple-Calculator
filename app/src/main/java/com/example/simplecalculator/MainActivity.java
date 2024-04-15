package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView display, memoTop, memoBottom;
    String input, buffer, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.editText);
        memoTop = findViewById(R.id.memoTop);
        memoBottom = findViewById(R.id.memoBottom);
        input = display.getText().toString();
        operator = "";
        buffer = "0";
    }

    public void clickNumber(View view) {
        input = display.getText().toString().equals("0") ? "": display.getText().toString();
        ((Button)findViewById(R.id.btnC)).setText("C");
        ((Button)findViewById(R.id.btnC)).setTextSize(39);

//        Ввод цифр
        if (view.getId() == R.id.btn1) input += "1";
        else if (view.getId() == R.id.btn2) input += "2";
        else if (view.getId() == R.id.btn3) input += "3";
        else if (view.getId() == R.id.btn4) input += "4";
        else if (view.getId() == R.id.btn5) input += "5";
        else if (view.getId() == R.id.btn6) input += "6";
        else if (view.getId() == R.id.btn7) input += "7";
        else if (view.getId() == R.id.btn8) input += "8";
        else if (view.getId() == R.id.btn9) input += "9";
        else if (view.getId() == R.id.btn0) input += "0";

//        Изменение размера шрифта в зависимости от длины числа
        if (input.length() < 8) display.setTextSize(78);
        else if (input.length() < 23) display.setTextSize(Math.round(624.0/input.length()));
        else input = input.substring(0, input.length() - 1);

//        Запрет изменения после нажатия "="
        if (operator.equals("=") & !input.equals("0")) {
            input = input.substring(0, input.length() - 1);
            if (input.length() > 8) display.setTextSize(Math.round(624.0/input.length()));
        }
        display.setText(input);
    }
    public void clickDot(View view) {
        if (!input.contains(".")) input += ".";
        display.setText(input);
    }
    public void clickOperator(View view) {
        buffer = display.getText().toString();
        input = "0";
        display.setText(input);
        if (view.getId() == R.id.btnDiv) operator = "/";
        if (view.getId() == R.id.btnMul) operator = "*";
        if (view.getId() == R.id.btnPlus) operator = "+";
    }
    public void clickMinus(View view) {
        if (input.equals("0") & !operator.equals("=")) input = "-";
        else {
            buffer = display.getText().toString();
            input = "0";
            display.setText(input);
            operator = "-";
        }
        display.setText(input);
    }
    public void clickSqrt(View view) {
        if (input.startsWith("-")) Toast.makeText(this, "Negative number!", Toast.LENGTH_SHORT).show();
        else input = String.valueOf(Math.sqrt(Double.parseDouble(input)));
        if(input.length() > 1) if (input.substring(input.length() - 2, input.length()).equals(".0")) input = input.substring(0, input.length() - 2);
        if (input.length() > 8) display.setTextSize(Math.round(624.0/input.length()));
        else display.setTextSize(78);
        operator = "=";
        display.setText(input);
    }
    public void clickPow(View view) {
        if (input.equals("0")) {
            Toast.makeText(this, "Division by zero!", Toast.LENGTH_SHORT).show();
        }
        else {
            input = String.valueOf(1/Double.parseDouble(input));
            operator = "=";
        }
        if(input.length() > 1) if (input.substring(input.length() - 2, input.length()).equals(".0")) input = input.substring(0, input.length() - 2);
        if (input.length() > 8) display.setTextSize(Math.round(624.0/input.length()));
        else display.setTextSize(78);
        display.setText(input);
    }
    public void clickBackspace(View view) {

        if (!operator.equals("=")) {
            if (input.length() > 1) input = input.substring(0, input.length() - 1);
            else {
                input = "0";
                ((Button)findViewById(R.id.btnC)).setText("AC");
                ((Button)findViewById(R.id.btnC)).setTextSize(27);
            }
        }


        if (input.length() > 8) display.setTextSize(Math.round(624.0/input.length()));
        else display.setTextSize(78);
        display.setText(input);
    }
    public void clickEquals(View view) {
        memoTop.setText(memoBottom.getText());
        memoBottom.setText(String.format("%s%s%s", buffer, operator, input));
        switch (operator) {
            case "/": if (input.equals("0")) {
                Toast.makeText(this, "Division by zero!", Toast.LENGTH_SHORT).show();
                input = buffer;
                operator = "";
            }
            else input = String.valueOf(Double.parseDouble(buffer) / Double.parseDouble(input)); break;
            case "*": input = String.valueOf(Double.parseDouble(buffer) * Double.parseDouble(input)); break;
            case "-": input = String.valueOf(Double.parseDouble(buffer) - Double.parseDouble(input)); break;
            case "+": input = String.valueOf(Double.parseDouble(buffer) + Double.parseDouble(input)); break;
        }
        if (!operator.equals("")) operator = "=";
        if (input.equals("0")) operator = "";
        buffer = input;
        if(input.length() > 1) if (input.substring(input.length() - 2, input.length()).equals(".0")) input = input.substring(0, input.length() - 2);
        if (input.length() > 8) display.setTextSize(Math.round(624.0/input.length()));
        else display.setTextSize(78);
        display.setText(input);
    }
    public void clickC(View view) {
        if (((Button)findViewById(R.id.btnC)).getText().equals("C")) {
            ((Button)findViewById(R.id.btnC)).setText("AC");
            ((Button)findViewById(R.id.btnC)).setTextSize(27);
        }
        else {
            memoTop.setText("");
            memoBottom.setText("");
        }
        input = "0";
        buffer = "0";
        operator = "";
        display.setTextSize(78);
        display.setText("0");
    }

}