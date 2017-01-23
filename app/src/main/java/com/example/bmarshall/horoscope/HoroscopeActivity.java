package com.example.bmarshall.horoscope;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

        new FetchDailyHoroscope().execute(horoscope.getName());
    }

    private class FetchDailyHoroscope extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String sign = params[0].toLowerCase();

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String horoscopeJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://a.knrz.co/horoscope-api/current/" + sign);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                horoscopeJsonStr = buffer.toString();
                return horoscopeJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
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

            String pred = "prediction";
            int ind = s.indexOf(pred) + 13;

            TextView horoscopeView = (TextView) findViewById(R.id.horoscope);


            horoscopeView.setText(s.substring(ind, s.length() - 3));
            Log.i("json", s);
        }
    }
}
