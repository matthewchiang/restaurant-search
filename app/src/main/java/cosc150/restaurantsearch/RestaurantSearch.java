package cosc150.restaurantsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {

    boolean[] categoryBooleans;
    ArrayList<String> categoriesToSearch = new ArrayList<String>();

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

        // UNCOMMENT WHEN READY TO DO HADOOP REQUESTS
     //   /*
        try {
            Client client = new Client(categoriesToSearch);
        } catch (IOException e) {
            e.printStackTrace();
        }
     //   */
    }
}