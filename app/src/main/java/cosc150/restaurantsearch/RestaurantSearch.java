package cosc150.restaurantsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantSearch extends AppCompatActivity {

    Button request = (Button) findViewById(R.id.request);
    private String hostname = "52.90.92.72";
    private int portNumber = 40001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                


            }
        });
    }
}
