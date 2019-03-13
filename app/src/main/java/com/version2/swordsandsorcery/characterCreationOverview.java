package com.version2.swordsandsorcery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class characterCreationOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_overview);

//        Integer[] levels = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        // attempting to use the same method for an int drop down, hopefully it will work
        // create an adapter for the array instantiated above and set the spinner to activate it
//        ArrayAdapter<Integer> intAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, levels);
//        lvlSpinner.setAdapter(intAdapter);
//
//        lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("lvl", (Integer) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // auto-generated method that sets the default to the item at index 0;
//                    }
//            });


        // spinner is implemented dynamically in the java activity file.
        Spinner lvlSpinner = (Spinner) findViewById(R.id.lvl_spinner);
        String[] items = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        // create arrayAdapter using the string array and a default
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);

        lvlSpinner.setAdapter(adapter);
        lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.v("level", (String) parent.getItemAtPosition(position));
                    }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                    {
                        // auto generated program stub will set the initial to the object at index 0,
                        // could we make it so that there is some kind of interface between the settings
                        // screen and the drop down interface here? Boolean?
                    }
            });
    }


}
