package android.exercise.com.lab9ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager locMgr;
    TextView locProvider, mLat, mLong;
    Button btMap;
    Location location;
    LocationProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locProvider = findViewById(R.id.locProvider);
        mLat = findViewById(R.id.mlat);
        mLong = findViewById(R.id.mlong);
        btMap = findViewById(R.id.btMap);
    }

    @Override
    protected void onStart() {
        super.onStart();
        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }

        provider = locMgr.getProvider(LocationManager.GPS_PROVIDER);
        locProvider.setText("Location Provider : " + LocationManager.GPS_PROVIDER);
        location = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        showLocation(location);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locMgr.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    public void onProviderDisabled(String provider) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    private void showLocation(Location location) {
        if (location == null) {
            mLat.setText("Latitude : unknown");
            mLong.setText("Longitude : unknown");
        } else {
            mLat.setText("Latitude : " + location.getLatitude());
            mLong.setText("Longitude : " + location.getLongitude());
        }
    }

    // Intent launching a browser for viewing Google Map
    public void showMap(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + location.getLatitude() + "," + location.getLongitude() + "?z=17"));
        startActivity(intent);
    }

}