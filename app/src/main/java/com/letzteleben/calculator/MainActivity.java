package com.letzteleben.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number;
    Button equal, cancel;
    int totalSum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        equal = findViewById(R.id.buttonEqual);
        cancel = findViewById(R.id.buttonC);
        totalSum = 0;
        operation = "na";

        Button mOperations[] = new Button[4];
        mOperations[0] = findViewById(R.id.buttonPlus);
        mOperations[1] = findViewById(R.id.buttonMinus);
        mOperations[2] = findViewById(R.id.buttonMultiply);
        mOperations[3] = findViewById(R.id.buttonDivide);

        for (final Button mOperation : mOperations) {
            mOperation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (operation.equals("na")) {
                        totalSum = Integer.parseInt(number.getText().toString());
                        operation = mOperation.getTag().toString();
                        number.setText("");
                    }
                }
            });
        }

        Button digits[] = new Button[10];
        digits[0] = findViewById(R.id.button0);
        digits[1] = findViewById(R.id.button1);
        digits[2] = findViewById(R.id.button2);
        digits[3] = findViewById(R.id.button3);
        digits[4] = findViewById(R.id.button4);
        digits[5] = findViewById(R.id.button5);
        digits[6] = findViewById(R.id.button6);
        digits[7] = findViewById(R.id.button7);
        digits[8] = findViewById(R.id.button8);
        digits[9] = findViewById(R.id.button9);

        int index = 0;
        for (final Button digit : digits) {
            final int finalIndex = index;
            digit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (number.getText().toString().length() > 0)
                        number.setText((String.format("%s%s", number.getText().toString(), String.valueOf(finalIndex))).replaceAll("^0d*", ""));
                    else
                        number.setText(String.valueOf(finalIndex));
                }
            });
            index++;
        }

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operation.equals("plus"))
                    totalSum += Integer.parseInt(number.getText().toString());
                else if (operation.equals("minus"))
                    totalSum -= Integer.parseInt(number.getText().toString());
                else if (operation.equals("multiply"))
                    totalSum *= Integer.parseInt(number.getText().toString());
                else if (operation.equals("divide"))
                    if (!number.getText().toString().equals("0"))
                        totalSum /= Integer.parseInt(number.getText().toString());
                    else
                        Toast.makeText(getApplicationContext(), "Are you sure you want to divide by 0?", Toast.LENGTH_SHORT).show();
                number.setText(Integer.toString(totalSum));
                operation = "na";
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "na";
                totalSum = 0;
                number.setText("");
            }
        });
    }
}
