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

        final Button requestButton = (Button)findViewById(R.id.request);
        requestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchToSelectedRestaurant();
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

    public void switchToSelectedRestaurant() {
        Intent selectedRestaurantIntent = new Intent(this, RestaurantSearch.class);
        startActivity(selectedRestaurantIntent);
    }

}