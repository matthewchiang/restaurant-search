package cosc150.restaurantsearch;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

    boolean[] selected = new boolean[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if (selected[0])
                    aztec.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    aztec.clearColorFilter();
            }
        });
        chinese.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[1] = !selected[1];
                if (selected[1])
                    chinese.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    chinese.clearColorFilter();
            }
        });
        japanese.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[2] = !selected[2];
                if (selected[2])
                    japanese.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    japanese.clearColorFilter();
            }
        });
        italian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[3] = !selected[3];
                if (selected[3])
                    italian.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    italian.clearColorFilter();
            }
        });
        french.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[4] = !selected[4];
                if (selected[4])
                    french.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    french.clearColorFilter();
            }
        });
        middleEastern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[5] = !selected[5];
                if (selected[5])
                    middleEastern.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    middleEastern.clearColorFilter();
            }
        });
        canadian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[6] = !selected[6];
                if (selected[6])
                    canadian.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    canadian.clearColorFilter();
            }
        });
        mexican.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected[7] = !selected[7];
                if (selected[7])
                    mexican.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                else
                    mexican.clearColorFilter();
            }
        });

        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchRestaurants();
            }
        });
    }

    public void searchRestaurants() {
        Intent mapIntent = new Intent(this, RestaurantSearch.class);
        startActivity(mapIntent);
    }
}
