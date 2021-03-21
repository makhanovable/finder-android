package kz.hacknu.findroom.presentation.view

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.new_edit_text_view.view.*
import kz.hacknu.findroom.R
import org.koin.core.KoinComponent
import org.koin.core.inject


class OwnEditTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes), KoinComponent {

    init {
        LayoutInflater.from(context).inflate(R.layout.new_edit_text_view, this, true)
        container_cv.setOnClickListener {
            if (content_et.visibility == View.VISIBLE) {
                content_et.requestFocus()
                val imm: InputMethodManager? =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ownAttrs, 0, 0
            )

            val title = typedArray.getString(R.styleable.ownAttrs_label)
            if (title != null) setTitle(title)

            val hint = typedArray.getString(R.styleable.ownAttrs_hint)
            if (hint != null) setHint(hint)

            content_et.filters = arrayOf<InputFilter>(
                InputFilter.LengthFilter(
                    typedArray.getInteger(R.styleable.ownAttrs_android_maxLength, 500)
                )
            )
            content_et.inputType = typedArray.getInteger(
                R.styleable.ownAttrs_android_inputType, InputType.TYPE_CLASS_TEXT
            )

            typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        title_tv.text = title
    }

    fun setText(text: String) {
        setContent(text)
    }

    fun getText(): String? {
        var res = content_et.text.toString()
        if (res.trim().isEmpty())
            return null

        return res
    }


    fun setContent(text: String) {
        content_tv.setTextColor(ContextCompat.getColor(context, R.color.black))
        content_tv.text = text
        content_et.setText(text)
    }

    fun setHint(hint: String) {
        content_et.hint = hint
    }

    override fun hasFocus(): Boolean {
        try {
            return content_et.hasFocus()
        } catch (e: Exception) {
            return false
        }
    }

    fun getEditText() = content_et

    fun addTextChangedListener(textWatcher: TextWatcher) {
        content_et.addTextChangedListener(textWatcher)
    }

    fun doOnChangedText(
        action: (
            text: CharSequence?
        ) -> Unit
    ) {
        content_et.doOnTextChanged { text, start, count, after ->
            action.invoke(text)
        }
    }
}