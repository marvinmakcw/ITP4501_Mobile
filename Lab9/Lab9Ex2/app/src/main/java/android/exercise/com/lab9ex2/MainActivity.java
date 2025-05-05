package android.exercise.com.lab9ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText ipGeocoding;
    private TextView mLatitude, mLongitude, mAddress;
    private Button button;
    private Geocoder geocoder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipGeocoding = findViewById(R.id.input1);
        mLatitude = findViewById(R.id.latitude);
        mLongitude = findViewById(R.id.longitude);
        mAddress = findViewById(R.id.address);

        button = findViewById(R.id.geocode_button);

        geocoder = new Geocoder(this);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                try {
                    List<Address> addresses = geocoder.getFromLocationName(ipGeocoding.getText().toString(), 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String addressText = String.format("%s, %s, %s", address.getAddressLine(0), address.getAddressLine(1), address.getCountryName());
                        mAddress.setText("Address : " + addressText);
                        mLatitude.setText("Latitude : " + address.getLatitude());
                        mLongitude.setText("Longitude : " + address.getLongitude());
                    } else
                        Toast.makeText(getApplicationContext(), "Error - Geocoder service does not work", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e("GeocodExample", "Error", e);
                    Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}