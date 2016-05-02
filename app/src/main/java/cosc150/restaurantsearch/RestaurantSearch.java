package cosc150.restaurantsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

        final ListView listview = (ListView) findViewById(R.id.listView);
        String[] values = new String[]{"Michaucan Restaurant", "Taco Cantina",
                "Cinco Mayo", "Azteca Mex", "The Azteca", "Somato", "Grand Azteca",
                "Plaza Azteca", "Aztec Cantina" , "El Pollo Loco", "Big Joses",
                "Garcia and Martinez' Grill", "Margarita's Margitas",
                "We're number Juan", "Jose's and Josb's" , "Grill and Steak"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }
}
