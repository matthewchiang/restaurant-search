package cosc150.restaurantsearch;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ImageView aztec;
    ImageView chinese;
    ImageView japanese;
    ImageView italian;
    ImageView french;
    ImageView middleEastern;
    ImageView canadian;
    ImageView mexican;

    boolean[] selected = new boolean[8]; //Boolean selection flags 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading icons on to the ImageViews
        aztec = (ImageView)findViewById(R.id.Aztec);
        chinese = (ImageView)findViewById(R.id.Chinese);
        japanese = (ImageView)findViewById(R.id.Japanese);
        italian = (ImageView)findViewById(R.id.Italian);
        french = (ImageView)findViewById(R.id.French);
        middleEastern = (ImageView)findViewById(R.id.MiddleEastern);
        canadian = (ImageView)findViewById(R.id.Canadian);
        mexican = (ImageView)findViewById(R.id.Mexican);

        aztec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[0] = !selected[0];
                if (selected[0]) //if icon is pressed, turn its shade red to identify selection
                    aztec.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    aztec.clearColorFilter();
            }
        });
        chinese.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[1] = !selected[1];
                if (selected[1]) //if icon is pressed, turn its shade red to identify selection
                    chinese.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    chinese.clearColorFilter();
            }
        });
        japanese.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[2] = !selected[2];
                if (selected[2]) //if icon is pressed, turn its shade red to identify selection
                    japanese.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    japanese.clearColorFilter();
            }
        });
        italian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[3] = !selected[3];
                if (selected[3]) //if icon is pressed, turn its shade red to identify selection
                    italian.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    italian.clearColorFilter();
            }
        });
        french.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[4] = !selected[4];
                if (selected[4]) //if icon is pressed, turn its shade red to identify selection
                    french.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    french.clearColorFilter();
            }
        });
        middleEastern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[5] = !selected[5];
                if (selected[5]) //if icon is pressed, turn its shade red to identify selection
                    middleEastern.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    middleEastern.clearColorFilter();
            }
        });
        canadian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[6] = !selected[6];
                if (selected[6]) //if icon is pressed, turn its shade red to identify selection
                    canadian.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    canadian.clearColorFilter();
            }
        });
        mexican.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[7] = !selected[7];
                if (selected[7]) //if icon is pressed, turn its shade red to identify selection
                    mexican.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else //if pressed again, turn off red shade
                    mexican.clearColorFilter();
            }
        });

        //When 'search' button is pressed, call the intent function below
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchToRestaurantSearch();
            }
        });
    }

    //Creates intent sent to next activity including the boolean selection flags of categories
    public void switchToRestaurantSearch() {
        Intent restaurantSearchIntent = new Intent(this, RestaurantSearch.class);
        restaurantSearchIntent.putExtra("selected", selected);

        startActivity(restaurantSearchIntent);
    }
}
