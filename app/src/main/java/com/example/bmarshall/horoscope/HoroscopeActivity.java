package com.example.bmarshall.horoscope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HoroscopeActivity extends AppCompatActivity {

    public static final String EXTRA_HOROSCOPENO = "horoscopeNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        int horoscopeNo = (Integer) getIntent().getExtras().get(EXTRA_HOROSCOPENO);
        Horoscope horoscope = Horoscope.horoscopes[horoscopeNo];

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(horoscope.getName());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(horoscope.getDescription());

        TextView sign = (TextView) findViewById(R.id.sign);
        sign.setText(horoscope.getSign());

        TextView month = (TextView) findViewById(R.id.month);
        month.setText(horoscope.getMonth());

        TextView horoscopeView = (TextView) findViewById(R.id.horoscope);
        horoscopeView.setText(horoscope.getHoroscope());
    }
}
