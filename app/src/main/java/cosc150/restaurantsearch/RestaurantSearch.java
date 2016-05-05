package cosc150.restaurantsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
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
    ArrayList<String> categoriesToSearch = new ArrayList<String>();
    BPlusTree allRestaurants = new BPlusTree(6);
    Boolean readyToDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

        /*
        Button request = (Button) findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent select = new Intent(view.getContext(), SelectedRestaurant.class);
                select.putExtra("restaurant_name", "Chicoli");
                select.putExtra("description", "We raise all our ingredients irresponsibly!");
                select.putExtra("overall_rating", 1.5);
                startActivityForResult(select, 0);
            }
        });
        */

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
            while (readyToDisplay.equals(new Boolean(false)) && System.currentTimeMillis() - startTime < 10000);

            ArrayList<Restaurant> restaurantList = allRestaurants.getRestaurants();
            System.out.println("SIZE: " + restaurantList.size());

            LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);


            for (Restaurant toAdd : restaurantList) {
                String cat = toAdd.restaurantCategory;
                final String name = toAdd.restaurantName;
                final String descr = toAdd.restaurantDescription;
                final double rat = toAdd.restaurantRating;

                TextView text = new TextView(this);

                text.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent select = new Intent(v.getContext(), SelectedRestaurant.class);
                        select.putExtra("restaurant_name", name);
                        select.putExtra("description", descr);
                        select.putExtra("overall_rating",rat);
                        startActivityForResult(select, 0);
                    }
                });

                text.setText(cat + name + descr + rat);
                text.setTextSize(20);
                linearLayout1.addView(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}