package kz.hacknu.findroom.presentation.auth


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import kz.hacknu.findroom.R
import kz.hacknu.findroom.presentation.base.BaseBottomSheet

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class AuthBottomSheet () : BaseBottomSheet() {

    override fun getTheme(): Int = R.style.BottomSheetGreyDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_auth, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeLiveData()
        setInitData()
    }

    private fun initUI() {
    }

    override fun onPause() {
        super.onPause()

//        val houseN = houseNumber.getText()
//        val flatN = flat.getText()
//        viewModel.save(houseN, flatN)
//
//        val data = viewModel.saved.value
//        if (data != null) action.invoke(data)
    }

    override fun onDismiss(dialog: DialogInterface) {



        super.onDismiss(dialog)
    }


    private fun observeLiveData() {
//        viewModel.toast.observe(viewLifecycleOwner, Observer {
//            toast(it)
//        })
//        viewModel.selectedAddr.observe(viewLifecycleOwner, Observer {
//            if (settingsRepo.isRu())
//                address.setText(it.NAME_RU.toString())
//            else address.setText(it.NAME_KZ.toString())
//        })
//        viewModel.selectedBuildingTypeId.observe(viewLifecycleOwner, Observer {
//            if (it.ID != null && it.ID == "3")
//                flat.visibility = VISIBLE
//            else flat.visibility = GONE
//            if (settingsRepo.isRu())
//                buildingType_et.setText(it.NAME_RU.toString())
//            else buildingType_et.setText(it.NAME_KZ.toString())
//        })
//        viewModel.selectedStreet.observe(viewLifecycleOwner, Observer {
//            if (settingsRepo.isRu())
//                street.setText(it.NAME_RU.toString())
//            else street.setText(it.NAME_KZ.toString())
//        })
//        viewModel.saved.observe(viewLifecycleOwner, Observer {
//            action.invoke(it)
//            dismiss()
//        })
    }

    private fun setInitData() {
//        if (actualAddress != null) {
//            if (actualAddress.localityId != null)
//                viewModel.loadLocalityString(actualAddress.localityId)
//            if (actualAddress.houseNumber != null)
//                houseNumber.setText(actualAddress.houseNumber)
//            if (actualAddress.buildingTypeId != null)
//                viewModel.loadBuildingTypeId(actualAddress.buildingTypeId)
//            if (actualAddress.buildingTypeId == "3") // TODO USE GLOABAL CONSTANT
//                flat.visibility = VISIBLE
//            if (actualAddress.flatNumber != null) {
//                flat.setText(actualAddress.flatNumber.toString())
//            }
//            if (actualAddress.streetId != null)
//                viewModel.loadStreetString(actualAddress.streetId)
//        }
    }
}