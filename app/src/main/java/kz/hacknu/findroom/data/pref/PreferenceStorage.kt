package kz.hacknu.findroom.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kz.hacknu.findroom.data.pref.utils.BooleanPreference
import kz.hacknu.findroom.data.pref.utils.StringPreference

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
class PreferenceStorage(context: Context) {
    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    }
    var isPinLoginSet by BooleanPreference(prefs, IS_PIN_LOGIN_SET, false)
    var pinKey by StringPreference(prefs, PIN_KEY, null)

    companion object {
        private const val PREFS_NAME = "find_room_prefs"
        private const val IS_PIN_LOGIN_SET = "is_pin_login_set"
        private const val PIN_KEY = "pin_key"
    }
}