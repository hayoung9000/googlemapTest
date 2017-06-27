package com.example.lg.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.839269,128.631892),17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark=new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.loc)).position(latLng,100f,100f);
                googleMap.addGroundOverlay(loc_mark);
            }
        });
    }

    public static final int ITEM_SATELLITE=1;
    public static final int ITEM_NOMRMAL=2;
    public static final int ITEM_BUDAPEST=3;
    public static final int ITEM_OKTAGON=4;
    public static final int ITEM_MARK_CLEAR=5;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"위성 지도");
        menu.add(0,2,0,"일반 지도");
        SubMenu hotmenu=menu.addSubMenu("핫플");
        hotmenu.add(0,3,0,"부다페스트");
        hotmenu.add(0,4,0,"옥타곤");
        menu.add(0,ITEM_MARK_CLEAR,0,"위치 아이콘 제거");
//        menu.add(0,3,0,"부다페스트");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case ITEM_SATELLITE: googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NOMRMAL:googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_BUDAPEST: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.496393, 19.039534),17));
                return true;
            case ITEM_OKTAGON: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.511566, 127.031622),17)); //17은 줌 level
                return true;
            case ITEM_MARK_CLEAR: googleMap.clear(); return  true;
        }
        return false;
    }
}