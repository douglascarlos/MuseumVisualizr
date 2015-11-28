package douglas_carlos.museu.feevale.br.museumvisualizr.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserHelper {

    static final String PREF_VISITOR = "Preferences";
    static final String VISITOR_NAME = "NAME";
    static SharedPreferences shPref;

    public static String get(Context context){
        shPref = context.getSharedPreferences(PREF_VISITOR, 0);
        return shPref.getString(VISITOR_NAME, "Sem Nome!");
    }

    public static void set(String newUserName){
        SharedPreferences.Editor editor = shPref.edit();
        editor.putString(VISITOR_NAME, newUserName);
        editor.commit();
    }

}
