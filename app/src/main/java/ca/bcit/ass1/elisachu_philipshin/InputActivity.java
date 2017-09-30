package ca.bcit.ass1.elisachu_philipshin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class InputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float inputDecimal = 0;
                EditText input = (EditText) findViewById(R.id.input);
                if(input.getText().toString().isEmpty()){
                    return;
                }
                inputDecimal = Float.parseFloat(input.getText().toString());

                Spinner temperatureSpinner = (Spinner) findViewById(R.id.temperature);
                String selection = String.valueOf(temperatureSpinner.getSelectedItem());
                String convertedTemp = getTemperature(selection, inputDecimal);

                Intent intent = new Intent(InputActivity.this, OutputActivity.class);
                intent.putExtra("result", convertedTemp);
                startActivity(intent);
            }

        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public String getTemperature(String conversion, float input) {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);

        String result = df.format(input);
        switch (conversion) {
            case "Celsius to Fahrenheit":
                result = result + " °C converts to "
                        + df.format((input*1.8) + 32) + " °F";
                break;
            case "Celsius to Kelvin":
                result = result + " °C converts to "
                        + df.format((input+273.15)) + " °K";
                break;
            case "Fahrenheit to Kelvin":
                result = result + " °F converts to "
                        + df.format((input+459.67) * .5555556) + " °K";
                break;
            case "Fahrenheit to Celsius":
                result = result + " °F converts to "
                        + df.format((input-32)* .5555556) + " °C";
                break;
            case "Kelvin to Celsius":
                result = result + " °K converts to "
                        + df.format((input-273.15)) + " °C";
                break;
            case "Kelvin to Fahrenheit":
                result = result + " °K converts to "
                        + df.format((input*1.8) - 459.67) + " °F";
                break;

            //French
            case "Celsius à Fahrenheit":
                result = result + " °C se transforme en "
                        + df.format((input*1.8) + 32) + " °F";
                break;
            case "Celsius à Kelvin":
                result = result + " °C se transforme en "
                        + df.format((input+273.15)) + " °K";
                break;
            case "Fahrenheit à Kelvin":
                result = result + " °F se transforme en "
                        + df.format((input+459.67) * .5555556) + " °K";
                break;
            case "Fahrenheit à Celsius":
                result = result + " °F se transforme en "
                        + df.format((input-32)* .5555556) + " °C";
                break;
            case "Kelvin à Celsius":
                result = result + " °K se transforme en "
                        + df.format((input-273.15)) + " °C";
                break;
            case "Kelvin à Fahrenheit":
                result = result + " °K se transforme en "
                        + df.format((input*1.8) - 459.67) + " °F";
                break;
            default:
                result = "Entrée invalide\n";
        }
        return result;
    }
}
