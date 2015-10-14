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
//    private Map<String, String> all;

    public KioskManager(Context ctxt) throws IOException {
        context = ctxt;

//        AssetManager assetMan = ctxt.getResources().getAssets();
//        String assetList[] = assetMan.list("android.resource://.");

//        all = new HashMap();

//        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures";
//        Log.d("Files", "Path: " + path);
//        File f = new File("file:///android_res/raw");
//        File files[] = f.listFiles();
//
//        for(File f1: files){
//            String path = f1.getName();
//            Log.d("ASSET________", path);
//            all.put(path, path);
//        }
    }

//    private Collection getAllAsCollection(){
//        return all.values();
//    }

//    public String getURLFrom(int index){
//        Object[] c = getAllAsCollection().toArray();
//
////        return "file:///android_res/raw/" + str + ".html";
//        return (String) c[index];
//    }

    public String getURLFrom(String str){
        return "file:///android_res/raw/" + str + ".html";
    }

//    public int getIndexOf(String txt){
//        Collection c = getAllAsCollection();
//        int index = 0;
//
//        for(Object str : c){
//            if(str.toString().equals(txt))
//                return index;
//
//            index++;
//        }
//
//        return -1;
//    }
//
//    public boolean has(String str){
//        return getIndexOf(str) > -1;
//    }

    public boolean has(String str){
        int id = context.getResources().getIdentifier(str, "raw", context.getPackageName());
        return id != 0;
    }
}
