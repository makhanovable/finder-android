package kz.hacknu.findroom.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.new_picker_view.view.*
import kz.hacknu.findroom.R
import org.koin.core.KoinComponent

class OwnNewPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes), KoinComponent {

    init {
        LayoutInflater.from(context).inflate(R.layout.new_picker_view, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ownAttrs, 0, 0
            )

            val title = typedArray.getString(R.styleable.ownAttrs_label)
            if (title != null) setTitle(title)

            val hint = typedArray.getString(R.styleable.ownAttrs_hint)
            if (hint != null) setHint(hint)

            typedArray.recycle()
        }
    }

    fun setHint(hint: String) {
        content_tv.text = hint
    }

    override fun setOnClickListener(l: OnClickListener?) {
        container_cv.setOnClickListener(l)
    }

    fun setTitle(title: String) {
        title_tv.text = title
    }

    fun setContent(text: String) {
        content_tv.setTextColor(ContextCompat.getColor(context, R.color.black))
        content_tv.text = text
    }

    fun getText(): String? {
        return content_tv.text.toString()
    }

    fun setText(text: String) {
        setContent(text)
    }

    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}