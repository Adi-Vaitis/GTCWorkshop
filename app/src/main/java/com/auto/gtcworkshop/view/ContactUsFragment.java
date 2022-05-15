package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.auto.gtcworkshop.MainActivity;
import com.auto.gtcworkshop.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactUsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String MAP_FRAGMENT = "fragment_contact_us";
        FragmentManager fragmentManager = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentByTag(MAP_FRAGMENT);
        if(mapFragment == null){
            mapFragment = SupportMapFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.mapView, mapFragment, MAP_FRAGMENT)
                    .commit();
        }
        mapFragment.getMapAsync(this);

        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng gtc = new LatLng(55.89174155755711, 9.773715885254278);
        mMap.addMarker(new MarkerOptions()
        .position(gtc)
        .title("Our workshop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gtc));
    }


}
