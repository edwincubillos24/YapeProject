import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.edwinacubillos.yapeproject.databinding.CustomBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var customBottomSheetDialogBinding: CustomBottomSheetDialogBinding
    private var onConfirmClicked: () -> Unit = {}
    private var onCancelConfirm: () -> Unit = {}
    private var customTitle: String? = null
    private var customDescription: String? = null
    private var customConfirmButtonTitle: String? = null
    private var customCancelButtonTitle: String? = null
    private var isCancelButtonVisible: Boolean? = null

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            customTitle: String,
            customDescription: String,
            customConfirmButtonTitle: String? = null,
            customCancelButtonTitle: String? = null,
            onConfirmClicked: () -> Unit = {},
            onCancelConfirm: () -> Unit = {},
            isCancelButtonVisible: Boolean = true
        ) {
            val baseBottomSheetDialogFragment = BaseBottomSheetDialogFragment()
            baseBottomSheetDialogFragment.setOnConfirmClicked(onConfirmClicked)
            baseBottomSheetDialogFragment.setOnCancelClicked(onCancelConfirm)
            baseBottomSheetDialogFragment.setCustomTitle(customTitle)
            baseBottomSheetDialogFragment.setCustomDescription(customDescription)
            baseBottomSheetDialogFragment.setCustomConfirmButtonTitle(customConfirmButtonTitle)
            baseBottomSheetDialogFragment.setCustomCancelButtonTitle(customCancelButtonTitle)
            baseBottomSheetDialogFragment.setIsCancelButtonVisible(isCancelButtonVisible)
            baseBottomSheetDialogFragment.show(
                fragmentManager,
                BaseBottomSheetDialogFragment::class.simpleName
            )
        }
    }

    private fun setCustomTitle(customTitle: String) {
        this.customTitle = customTitle
    }

    private fun setCustomDescription(customDescription: String) {
        this.customDescription = customDescription
    }

    private fun setOnConfirmClicked(onConfirmClicked: () -> Unit) {
        this.onConfirmClicked = onConfirmClicked
    }

    private fun setOnCancelClicked(onCancelConfirm: () -> Unit) {
        this.onCancelConfirm = onCancelConfirm
    }

    private fun setCustomConfirmButtonTitle(customConfirmButtonTitle: String?) {
        this.customConfirmButtonTitle = customConfirmButtonTitle
    }

    private fun setCustomCancelButtonTitle(customCancelButtonTitle: String?) {
        this.customCancelButtonTitle = customCancelButtonTitle
    }

    private fun setIsCancelButtonVisible(isCancelButtonVisible: Boolean) {
        this.isCancelButtonVisible = isCancelButtonVisible
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        customBottomSheetDialogBinding = CustomBottomSheetDialogBinding.inflate(inflater, container, false)
        setValuesOfView()
        setListeners()
        return customBottomSheetDialogBinding.root
    }

    private fun setValuesOfView() {
        customBottomSheetDialogBinding.titleTextView.text = this.customTitle ?: ""
        customBottomSheetDialogBinding.descriptionTextView.text = this.customDescription ?: ""
        customBottomSheetDialogBinding.positiveButton.text = this.customConfirmButtonTitle ?: ""
        customBottomSheetDialogBinding.negativeButton.text = this.customCancelButtonTitle ?: ""
        isCancelButtonVisible?.let {
            if (it.not()) customBottomSheetDialogBinding.negativeButton.visibility = View.INVISIBLE
        }
    }

    private fun setListeners() {
        // Positive button
        customBottomSheetDialogBinding.positiveButton.setOnClickListener {
            this.onConfirmClicked.invoke()
            this.dismiss()
        }
        // Negative button
        customBottomSheetDialogBinding.negativeButton.setOnClickListener {
            this.onCancelConfirm.invoke()
            this.dismiss()
        }
    }
}
