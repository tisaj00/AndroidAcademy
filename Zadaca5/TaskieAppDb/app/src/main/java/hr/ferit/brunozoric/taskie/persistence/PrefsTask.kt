package hr.ferit.brunozoric.taskie.persistence

import android.preference.PreferenceManager
import hr.ferit.brunozoric.taskie.Taskie

object PrefsTask {

    const val KEY_PREFS_NAME = "KEY_PREFS_NAME"

    private fun sharedPrefs()=PreferenceManager.getDefaultSharedPreferences(Taskie.getAppContext())

    fun store(key:String,value:String){
        val editor = sharedPrefs().edit()
        editor.putString(key, value).apply()
    }
    fun getString(key: String,defaultValue:String):String?{
        return sharedPrefs().getString(key,defaultValue)
    }

}