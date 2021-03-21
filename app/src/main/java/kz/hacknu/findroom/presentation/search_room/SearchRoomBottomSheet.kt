package kz.hacknu.findroom.presentation.search_room

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_search_room.*
import kz.hacknu.findroom.R
import kz.hacknu.findroom.presentation.base.BaseBottomSheet
import kz.hacknu.findroom.presentation.view.toast
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class SearchRoomBottomSheet(
    private val res: (list: SearchResponse) -> Unit
) : BaseBottomSheet() {
    private val viewModel: SearchViewModel by viewModel()
    private var lat: Double = 0.0
    private var long: Double = 0.0


    override fun getTheme(): Int = R.style.BottomSheetGreyDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_search_room, container, false)


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
        search_btn.setOnClickListener {
            val tags = tags_et.getText()
            val radius = radius_et.getText()
            if (tags == null || tags.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            if (radius == null || radius.isBlank()) {
                toast("error1")
                return@setOnClickListener
            }

            try {
                val r = radius.toLong()

                viewModel.search(
                    lat = lat,
                    long = long,
                    radius = r,
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
                progress_bar.visibility = View.VISIBLE
                container.visibility = View.GONE
            } else {
                progress_bar.visibility = View.GONE
                container.visibility = View.VISIBLE
            }
        })
        viewModel.result.observe(viewLifecycleOwner, Observer {
            res.invoke(it)
            dismiss()
        })
    }
}