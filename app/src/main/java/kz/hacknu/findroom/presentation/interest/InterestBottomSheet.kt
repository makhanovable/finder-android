package kz.hacknu.findroom.presentation.interest

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_enum_list.*
import kz.hacknu.findroom.R
import kz.hacknu.findroom.data.room.Interests
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class InterestBottomSheet(
    private val data: List<Interests>?,
    private val onItemSelected: (list: List<Interests>) -> Unit
) : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    val viewModel: InterestsViewModel by viewModel()
    lateinit var adapter: EnumCheckBoxListAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_enum_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= EnumCheckBoxListAdapter(requireContext())

        list.adapter = adapter
        // save_btn.visibility = VISIBLE
        viewModel.pairedList.observe(viewLifecycleOwner, Observer {
            adapter.submitData(it)
        })

        viewModel.start(data)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        adapter.getSelectedItems().let { onItemSelected.invoke(it) }
    }


}