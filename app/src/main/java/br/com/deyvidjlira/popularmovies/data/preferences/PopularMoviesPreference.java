package br.com.deyvidjlira.popularmovies.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Deyvid on 05/01/2017.
 */
public class PopularMoviesPreference {

    private static PopularMoviesPreference m_Instance;
    private static SharedPreferences m_SharedPreferences;

    public  static PopularMoviesPreference getInstance(Context context) {
        if(m_Instance == null) {
            m_Instance = new PopularMoviesPreference(context);
        }
        return m_Instance;
    }

    private PopularMoviesPreference(Context context) {
        m_SharedPreferences = context.getSharedPreferences(PreferenceParameters.m_NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = m_SharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return m_SharedPreferences.getBoolean(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = m_SharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return m_SharedPreferences.getString(key, defaultValue);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = m_SharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        return m_SharedPreferences.getInt(key, defaultValue);
    }

}
