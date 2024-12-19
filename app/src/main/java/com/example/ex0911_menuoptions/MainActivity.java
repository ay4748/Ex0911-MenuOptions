package com.example.ex0911_menuoptions;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView tVans;
    String num1;
    String num2;
    double Dnum1,Dnum2;
    double ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();
    }

    private void initviews() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tVans = findViewById(R.id.tVans);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu (menu);
    }

    public boolean canAct(String st) {
        if (st.length() == 0 || st.equals("-") || st.equals(".") || st.equals("+") || st.equals("-.") || st.equals("+."))
        {
            tVans.setText("invalid input");
            return false;
        } else {
            return true;
        }
    }

    public String bigNumSimplifier(double value)
    {
        String scientificNotation = String.format("%.4e", value);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;

        return String.format("%.4f * 10^%d", base, exponent);
    }


    public boolean onOptionsItemSelected(@Nullable MenuItem item)
    {
        int id = item.getItemId();
        num1 = et1.getText().toString().trim();
        num2 = et2.getText().toString().trim();
        if(canAct(num1) && canAct(num2))
        {
            Dnum1 = Double.valueOf(num1);
            Dnum2 = Double.valueOf(num2);
            if(id == R.id.menuPlus)
            {
                ans = Dnum1 + Dnum2;

            }
            else if(id == R.id.menuMinus)
            {
                ans = Dnum1 - Dnum2;
            }
            else if(id == R.id.menuMul){
                ans = Dnum1 * Dnum2;
            }
            else if(id == R.id.menuDiv){
                if(Dnum2!= 0)
                {
                    ans = Dnum1 / Dnum2;
                }
                else
                {
                    tVans.setText("Cannot divide by zero");
                    return true;
                }
            }
            else if(id == R.id.menuClear)
            {
                et1.setText("");
                et2.setText("");
                tVans.setText("");
                return true;
            }


            if(ans > 10000000 || ans < -10000000)
                tVans.setText(bigNumSimplifier(ans));
            else if (ans < 0.000001 && ans > 0.000001){
                tVans.setText(bigNumSimplifier(ans));
            }
            else{
                tVans.setText("ans =  " + String.format("%.4f", ans));
            }


        }


        return true;
    }
}