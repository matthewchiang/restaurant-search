package cosc150.restaurantsearch;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SelectedRestaurant extends AppCompatActivity {

    final int reviewCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_restaurant);

        String importedName = "";
        String importedDescription = "";
        double importedRating = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            importedName = extras.getString("restaurant_name");
            importedDescription = extras.getString("description");
            importedRating = extras.getDouble("overall_rating");
        }

        TextView restName = (TextView) findViewById(R.id.restName);
        restName.setText(importedName);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(importedDescription);

        RatingBar restRating = (RatingBar) findViewById(R.id.overallRating);
        restRating.setRating((float)importedRating);

        Review[] restReviews = ReviewGenerator.createReviews(importedRating, reviewCount);

        class reviewAdapter extends ArrayAdapter<Review> {
            private final Context context;

            public reviewAdapter(Context context, Review[] reviews){
                super(context, R.layout.reviewlayout, reviews);
                this.context = context;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.reviewlayout, parent, false);
                RatingBar userRating = (RatingBar) rowView.findViewById(R.id.userRating);
                TextView userReview = (TextView) rowView.findViewById(R.id.userReview);

                Review singleReview = getItem(position);
                userRating.setRating((float)singleReview.getUserRating());
                userReview.setText(singleReview.getUserReview());

                return rowView;
            }
        }

        ListView lv = (ListView) findViewById(R.id.ListView);
        reviewAdapter adapter = new reviewAdapter(this, restReviews);
        lv.setAdapter(adapter);
    }

    public void goToMap (View view) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }

    protected void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
