package com.app.module.traffic;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.utils.Logger;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddNavigationActivity extends BaseActivity {
    private MapFragment map1;
    private MapFragment map2;
    private MapView mapView;
    private Geocoder geocoder;
    private GoogleMap googleMap1;
    private GoogleMap googleMap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        map1 = (MapFragment) getFragmentManager().findFragmentById(R.id.map1);
        map1.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap1 = googleMap;
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(10.2878770000, 22.1046050000))
                        .title("Marker"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(10.2878770000, 22.1046050000)));
            }
        });
        map2 = (MapFragment) getFragmentManager().findFragmentById(R.id.map2);
        map2.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap2 = googleMap;
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(32.7457670904, 89.7298191416))
                        .title("Marker"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(32.7457670904, 89.7298191416)));
            }
        });

        geocoder = new Geocoder(mContext, Locale.CHINA);
    }

    public void geo(View view) {
        Observable.create(new Observable.OnSubscribe<List<Address>>() {
            @Override
            public void call(Subscriber<? super List<Address>> subscriber) {
                try {
                    subscriber.onNext(geocoder.getFromLocationName("新洲三街", 5));
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<List<Address>>() {
                               @Override
                               public void onStart() {
                                   super.onStart();
                                   progressDialog.show();
                               }

                               @Override
                               public void onCompleted() {
                                   progressDialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   Logger.d(tag, e.getMessage());
                                   progressDialog.dismiss();
                               }

                               @Override
                               public void onNext(List<Address> addresses) {
                                   for (Address address : addresses) {
                                       Logger.d(tag, address.getLatitude() + ":" + address.getLongitude());
                                   }
                                   googleMap1.addMarker(new MarkerOptions()
                                           .position(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()))
                                           .title("Marker"));
                                   googleMap1.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude())));
                               }
                           }

                );

    }
}
