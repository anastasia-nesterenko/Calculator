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
        ScriptEngineManager manager = new ScriptEngineManager();
        final ScriptEngine engine = manager.getEngineByName("js");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        equal = findViewById(R.id.buttonEqual);
        cancel = findViewById(R.id.buttonC);
        final CharSequence sequence = "";
        final StringBuffer buffer = new StringBuffer(sequence);

        Button buttons[] = new Button[17];
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);
        buttons[10] = findViewById(R.id.buttonPlus);
        buttons[11] = findViewById(R.id.buttonMinus);
        buttons[12] = findViewById(R.id.buttonMultiply);
        buttons[13] = findViewById(R.id.buttonDivide);
        buttons[14] = findViewById(R.id.buttonLeftBracket);
        buttons[15] = findViewById(R.id.buttonRighttBracket);
        buttons[16] = findViewById(R.id.buttonPoint);
        
        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number.append(button.getText());
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
                } catch (Exception ex) {
                    System.out.println("There ia an exception: " + ex);
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