package kz.hacknu.findroom.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kz.hacknu.findroom.R
import kz.hacknu.findroom.presentation.create_room.CreateRoomBottomSheet
import kz.hacknu.findroom.presentation.main.as_list.AsListBottomSheet
import kz.hacknu.findroom.presentation.main.point.PointAdapter
import kz.hacknu.findroom.presentation.main.point.PointTag
import kz.hacknu.findroom.presentation.search_room.SearchRoomBottomSheet
import kz.hacknu.findroom.presentation.view.bitmapDescriptorFromVector
import kz.hacknu.findroom.presentation.view.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        observeLiveData()
        requestP()
    }

    private fun initUI() {
        val mapFragment = mapview as SupportMapFragment
        mapFragment.getMapAsync(this)

        addRoom.setOnClickListener {
            CreateRoomBottomSheet().show(supportFragmentManager, null)
        }
        searchRoom.setOnClickListener {
            SearchRoomBottomSheet {
                viewModel.searchResult.postValue(it)
            }.show(supportFragmentManager, null)
        }
        showAsList.setOnClickListener {
            AsListBottomSheet().show(supportFragmentManager, null)
        }
    }

    private fun observeLiveData() {
        viewModel.searchResult.observe(this, Observer {
            val list = it.data
            if (list != null && list.isNotEmpty()) {
                map.clear()
                for (item in list) {
                    if (item.long != null &&
                        item.lat != null &&
                        item.createdAt != null &&
                        item.title != null &&
                        item.left != null &&
                        item.link != null
                    ) {
                        val pointTag = PointTag(
                            title = item.title,
                            link = item.link,
                            endDate = (Date(item.createdAt + (60000 * item.left))).toString()
                        )
                        addMarker(item.lat, item.long, pointTag)
                    }
                }
            } else {
                toast("No list")
            }
        })
    }

    private fun requestP() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                200
            )
        }
    }

    private fun addMarker(latitude: Double, longitude: Double, tag: PointTag) {
        val marker = LatLng(latitude, longitude)
        val m = MarkerOptions().position(marker)
        m.icon(bitmapDescriptorFromVector(this, R.drawable.ic_point))
        val res = map.addMarker(m)
        res.tag = tag
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20F))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestP()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            map = p0
            val marker = LatLng(43.237043, 76.905180)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15F))
            map.setInfoWindowAdapter(PointAdapter(layoutInflater))
            map.setOnInfoWindowClickListener {
                val tag = it.tag
                if (tag != null && tag is PointTag) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(tag.link)
                    startActivity(i)
                }
            }
        }
    }
}