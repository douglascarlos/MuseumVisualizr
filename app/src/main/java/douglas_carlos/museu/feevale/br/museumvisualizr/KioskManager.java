package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.view.inputmethod.ExtractedTextRequest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Douglas on 11/10/2015.
 */
public class KioskManager {

    private Context context;

    public KioskManager(Context ctxt) throws IOException {
        context = ctxt;
    }

    public String getURLFrom(String str){
        return "file:///android_res/raw/" + str + ".html";
    }

    public boolean has(String str){
        int id = context.getResources().getIdentifier(str, "raw", context.getPackageName());
        return id != 0;
    }
}
