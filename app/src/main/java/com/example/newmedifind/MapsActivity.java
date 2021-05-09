package com.example.newmedifind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        String storename=getIntent().getStringExtra("storename1");
       // String username=LoginActivity.getUsername();
        //Log.i("value",storename);

       // LatLng bangalore = new LatLng(12.956525, 77.461972);
        if(storename.equals("Apollo"))
        {
            LatLng apollo = new LatLng(12.281973, 76.644880);

            mMap.addMarker(new MarkerOptions().position(apollo).title("Marker on Apollo"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(apollo,15f));
        }

        if(storename.equals("Healthplus"))
        {
            LatLng healthplus = new LatLng(12.299249, 76.626952);

            mMap.addMarker(new MarkerOptions().position(healthplus).title("Marker on Healthplus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(healthplus,15f));
        }

        if(storename.equals("Medcare"))
        {
            LatLng medcare = new LatLng(12.322058, 76.637673);

            mMap.addMarker(new MarkerOptions().position(medcare).title("Marker on Medcare"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medcare,15f));
        }

        if(storename.equals("Maruti Medicals"))
        {
            LatLng marutimedicals = new LatLng(12.297488, 76.657614);

            mMap.addMarker(new MarkerOptions().position(marutimedicals).title("Marker on Maruti Medicals"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marutimedicals,15f));
        }

        if(storename.equals("Vivekanand Medicals"))
        {
            LatLng vivekanand = new LatLng(12.319123, 76.622999);

            mMap.addMarker(new MarkerOptions().position(vivekanand).title("Marker on vivekanand medicals"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vivekanand,15f));
        }

        if(storename.equals("Medplus"))
        {
            LatLng medplus = new LatLng(12.322393, 76.673918);

            mMap.addMarker(new MarkerOptions().position(medplus).title("Marker on medplus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(medplus,15f));
        }









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}