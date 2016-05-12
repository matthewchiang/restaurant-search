package cosc150.restaurantsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public class RestaurantSearch extends AppCompatActivity {

    boolean[] categoryBooleans;
    ArrayList<String> categoriesToSearch = new ArrayList<String>(); //categories used for search in the server
    BPlusTree allRestaurants = new BPlusTree(6); //data structure to hold all incoming results from the server
    Boolean readyToDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        
        //Importing boolean flags of catagory selection from the previous activity
        categoryBooleans = getIntent().getBooleanArrayExtra("selected");
        if (categoryBooleans[0]) categoriesToSearch.add("Aztec");
        if (categoryBooleans[1]) categoriesToSearch.add("Chinese");
        if (categoryBooleans[2]) categoriesToSearch.add("Japanese");
        if (categoryBooleans[3]) categoriesToSearch.add("Italian");
        if (categoryBooleans[4]) categoriesToSearch.add("French");
        if (categoryBooleans[5]) categoriesToSearch.add("Middle Eastern");
        if (categoryBooleans[6]) categoriesToSearch.add("Canadian");
        if (categoryBooleans[7]) categoriesToSearch.add("Mexican");

        try {
            Client client = new Client(this);

            long startTime = System.currentTimeMillis();
            while (readyToDisplay.equals(new Boolean(false)));

            ArrayList<Restaurant> restaurantList = allRestaurants.getRestaurants();
            
            //Creating custom restaurant adapter for all search results
            ResAdapter myAdapter = new ResAdapter(
                    this,
                    R.layout.restaurantlayout,
                    restaurantList
            );
            
            //Applying the adapter to a ListView in the screen
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(myAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
