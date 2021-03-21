package kz.hacknu.findroom.presentation.create_room

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_create_room.*
import kz.hacknu.findroom.R
import kz.hacknu.findroom.presentation.base.BaseBottomSheet
import kz.hacknu.findroom.presentation.view.toast
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class CreateRoomBottomSheet() : BaseBottomSheet() {
    private val viewModel: CreateRoomViewModel by viewModel()
    private var lat: Double = 0.0
    private var long: Double = 0.0

    override fun getTheme(): Int = R.style.BottomSheetGreyDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_create_room, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeLiveData()
    }

    @SuppressLint("MissingPermission")
    private fun initUI() {
        val lm = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location != null) {
            lat = location.latitude
            long = location.longitude
        }
        val locationListener = LocationListener { p0 ->
            lat = p0.latitude
            long = p0.longitude
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, 10F, locationListener)
        save_btn.setOnClickListener {
            val title = title_et.getText()
            val link = link_et.getText()
            val left = time_et.getText()
            val tags = tags_et.getText()

            if (title == null || title.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            if (link == null || link.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            if (left == null || left.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            if (tags == null || tags.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            try {
                val time = left.toLong()

                viewModel.create(
                    lat = lat,
                    long = long,
                    title = title,
                    link = link,
                    leftTime = time,
                    tags = tags
                )
            } catch (e: Exception) {
                toast("error1${e.toString()}")
                return@setOnClickListener
            }
        }
    }


    private fun observeLiveData() {
        viewModel.toast.observe(viewLifecycleOwner, Observer {
            if (it != null)
                toast(it)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progress_bar.visibility = VISIBLE
                container.visibility = GONE
            } else {
                progress_bar.visibility = GONE
                container.visibility = VISIBLE
            }
        })
        viewModel.close.observe(viewLifecycleOwner, Observer {
            if (it) dismiss()
        })
    }
}