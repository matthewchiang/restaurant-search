package cosc150.restaurantsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {

//    private String hostname = "52.90.92.72";
//    private int portNumber = 40001;

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
    }


}
