package com.example.bmarshall.horoscope;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HoroscopeActivity extends AppCompatActivity {

    public static final String EXTRA_HOROSCOPENO = "horoscopeNo";

    private Horoscope horoscope;

    private int horoscopeNo;

    private TextView name;
    private TextView description;
    private TextView sign;
    private TextView month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        horoscopeNo = (Integer) getIntent().getExtras().get(EXTRA_HOROSCOPENO);

        horoscope = Horoscope.horoscopes[horoscopeNo];

        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        sign = (TextView) findViewById(R.id.sign);
        month = (TextView) findViewById(R.id.month);

        name.setText(horoscope.getName());
        description.setText(horoscope.getDescription());
        sign.setText(horoscope.getSign());
        month.setText(horoscope.getMonth());

        new FetchDailyHoroscope().execute();
    }

    public String jsonParser(String input) {
        String output;

        try {
            JSONObject obj = new JSONObject(input);
            output = obj.getString("prediction");
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private class FetchDailyHoroscope extends AsyncTask<Void, Void, String> {
        String sign;

        @Override
        protected void onPreExecute(){
            sign = horoscope.getName().toLowerCase();
        }

        @Override
        protected String doInBackground(Void...voids) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String horoscopeJsonStr = null;

            try {
                URL url = new URL("http://a.knrz.co/horoscope-api/current/" + sign);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                horoscopeJsonStr = buffer.toString();
                return horoscopeJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String prediction = jsonParser(s);

            TextView horoscopeView = (TextView) findViewById(R.id.horoscope);

            horoscopeView.setText(prediction);
        }
    }
}
