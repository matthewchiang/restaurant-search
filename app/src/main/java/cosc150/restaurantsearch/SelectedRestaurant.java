package cosc150.restaurantsearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectedRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_restaurant);

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
