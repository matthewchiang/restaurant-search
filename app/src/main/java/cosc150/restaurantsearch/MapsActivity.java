package cosc150.restaurantsearch;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Random random = new Random();
        Double latitude = (random.nextDouble() * 7.7) + 33.3;
        Double longitude = -(random.nextDouble() * 33.2) - 84.3;

        LatLng restaurantLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(restaurantLocation).title("Restaurant Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, new Float(12.0)));
    }
}
