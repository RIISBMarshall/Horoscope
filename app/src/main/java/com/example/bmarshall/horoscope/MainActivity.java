package com.example.bmarshall.horoscope;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listSigns = getListView();
        ArrayAdapter<Horoscope> listAdapter = new ArrayAdapter<Horoscope>(this, android.R.layout.simple_list_item_1, Horoscope.horoscopes);
        listSigns.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        Intent intent = new Intent(MainActivity.this, HoroscopeActivity.class);
        intent.putExtra(HoroscopeActivity.EXTRA_HOROSCOPENO, (int) id);
        startActivity(intent);
    }

}
