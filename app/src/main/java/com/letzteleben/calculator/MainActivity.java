package com.letzteleben.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {

    EditText number;
    Button equal, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO 1 add brackets
        //TODO 2 add decimal point

        ScriptEngineManager manager = new ScriptEngineManager();
        final ScriptEngine engine = manager.getEngineByName("js");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        equal = findViewById(R.id.buttonEqual);
        cancel = findViewById(R.id.buttonC);
        final CharSequence sequence = "";
        final StringBuffer buffer = new StringBuffer(sequence);

        final Button operations[] = new Button[4];
        operations[0] = findViewById(R.id.buttonPlus);
        operations[1] = findViewById(R.id.buttonMinus);
        operations[2] = findViewById(R.id.buttonMultiply);
        operations[3] = findViewById(R.id.buttonDivide);

        for (final Button operation : operations) {
            operation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.append(operation.getText().toString());
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

        for (final Button digit : digits) {
            digit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.append(digit.getText());
                }
            });
        }

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    buffer.append(number.getText().toString());
                    Object result = engine.eval(buffer.toString());
                    number.setText(result.toString());
                    System.out.println("You !!!" + result);
                } catch (Exception ex) {
                    System.out.println("You hit !!!" + ex);
                }
                buffer.setLength(0);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer.setLength(0);
                number.setText("");
            }
        });
    }
}
