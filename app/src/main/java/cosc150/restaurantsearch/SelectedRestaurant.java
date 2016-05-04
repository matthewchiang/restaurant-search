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

        TextView restName = (TextView) findViewById(R.id.restName);
        restName.setText(importedName);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(importedDescription);

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
                userRating.setRating((float) singleReview.getUserRating());
                userReview.setText(singleReview.getUserReview());

                return rowView;
            }
        }

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandableListView);
        reviewAdapter adapter = new reviewAdapter(this, restReviews);
        lv.setAdapter(adapter);
    }

    public void goToMap (View view) {
        goToUrl("https://www.google.com/maps/place/O'Donovan+Dining+Hall,+Washington,+DC+20007/@38.9063818,-77.0757277,19z/data=!3m1!4b1!4m6!1m3!3m2!1s0x89b7b640ce9f8f6b:0x1c9d6d0554662882!2sGeorgetown+University!3m1!1s0x89b7b64169d9932b:0xb576adcc47fbda61");
    }

    protected void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
