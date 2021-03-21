package kz.hacknu.findroom.presentation.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


fun AppCompatActivity.toast(message: String, lengthShort: Boolean = false) {
    var length = Toast.LENGTH_LONG
    if (lengthShort)
        length = Toast.LENGTH_SHORT

    val toast: Toast = Toast.makeText(this, message, length)

    toast.show()
}

fun AppCompatActivity.toast(@StringRes message: Int, lengthShort: Boolean = false) {
    var length = Toast.LENGTH_LONG
    if (lengthShort)
        length = Toast.LENGTH_SHORT

    val toast: Toast = Toast.makeText(this, message, length)

    toast.show()
}

fun AppCompatActivity.toast(message: Any, lengthShort: Boolean = false) {
    var length = Toast.LENGTH_LONG
    if (lengthShort)
        length = Toast.LENGTH_SHORT

    var toast: Toast? = null
    if (message is Int)
        toast = Toast.makeText(this, message, length)
    else if (message is String)
        toast = Toast.makeText(this, message, length)


    toast?.show()
}

fun Fragment.toast(message: String, lengthShort: Boolean = false) {
    var length = Toast.LENGTH_LONG
    if (lengthShort)
        length = Toast.LENGTH_SHORT

    val toast: Toast = Toast.makeText(requireContext(), message, length)

    toast.show()
}

fun Fragment.toast(@StringRes message: Int, lengthShort: Boolean = false) {
    var length = Toast.LENGTH_LONG
    if (lengthShort)
        length = Toast.LENGTH_SHORT

    val toast: Toast = Toast.makeText(requireContext(), message, length)

    toast.show()
}

fun Fragment.toast(message: Any, lengthShort: Boolean = false) {
    try {
        var length = Toast.LENGTH_LONG
        if (lengthShort)
            length = Toast.LENGTH_SHORT

        var toast: Toast? = null
        if (message is Int)
            toast = Toast.makeText(requireContext(), message, length)
        else if (message is String)
            toast = Toast.makeText(requireContext(), message, length)

        toast?.show()
    } catch (e: Exception) {

    }
}

fun bitmapDescriptorFromVector(
    context: Context,
    @DrawableRes vectorResId: Int
): BitmapDescriptor? {
    val vectorDrawable =
        ContextCompat.getDrawable(context, vectorResId)
    vectorDrawable!!.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}