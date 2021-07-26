package com.me.amapcontroller

import android.Manifest
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.*
import com.me.amapcontroller.databinding.ActivityMainBinding
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.RequestCallback

class MainActivity : AppCompatActivity(),AMap.OnMarkerDragListener{
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.mapView.onCreate(savedInstanceState)
        val map = binding.mapView.map
        val myLocationStyle = MyLocationStyle()
//        myLocationStyle.interval(2000)
        myLocationStyle.showMyLocation(true)
        map.myLocationStyle = myLocationStyle
        map.uiSettings.isMyLocationButtonEnabled=true
        map.isMyLocationEnabled = true
        map.setOnMarkerDragListener(this)
        map.setOnMyLocationChangeListener {
            setMarker(map,LatLng(it.latitude,it.longitude))
        }
        initPermission()
    }

    private fun setMarker(map:AMap,latLng: LatLng) {
        val markerOptions = MarkerOptions()
        markerOptions.draggable(true)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources,R.drawable.ic_location)))
        val marker = map.addMarker(markerOptions)
        marker.position=latLng
    }

    private fun initPermission() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .request(RequestCallback { allGranted, grantedList, deniedList ->
                if (allGranted) {

                }
            })
    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMarkerDragEnd(p0: Marker?) {
        Log.d("flag",p0?.position?.latitude.toString())
        Log.d("flag",p0?.position?.longitude.toString())

    }


}