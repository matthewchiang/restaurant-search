package cosc150.restaurantsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {

    boolean[] categoryBooleans;
    ArrayList<String> categoriesToSearch = new ArrayList<String>();
    BPlusTree allRestaurants = new BPlusTree(6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);

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

        categoryBooleans = getIntent().getBooleanArrayExtra("selected");
        if (categoryBooleans[0]) categoriesToSearch.add("Aztec");
        if (categoryBooleans[1]) categoriesToSearch.add("Chinese");
        if (categoryBooleans[2]) categoriesToSearch.add("Japanese");
        if (categoryBooleans[3]) categoriesToSearch.add("Italian");
        if (categoryBooleans[4]) categoriesToSearch.add("French");
        if (categoryBooleans[5]) categoriesToSearch.add("MiddleEastern");
        if (categoryBooleans[6]) categoriesToSearch.add("Canadian");
        if (categoryBooleans[7]) categoriesToSearch.add("Mexican");

        try {
            Client client = new Client(categoriesToSearch, allRestaurants);
            ArrayList<Node> nodeList = new ArrayList<Node>();

            Restaurant test = new Restaurant("Mexican", "JoesRest", "Best rest", 4.3);

            LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

            //for (Node n : nodeList) {
            for (int i = 0; i < 40; i++) {

                String cat = test.restaurantCategory;
                String name = test.restaurantName;
                String descr = test.restaurantDescription;
                double rat = test.restaurantRating;

                TextView text = new TextView(this);
                text.setText(cat + name + descr + rat);
                text.setTextSize(20);
                linearLayout1.addView(text);
            }
            //Node
            //while ()
            //allRestaurants.find();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}