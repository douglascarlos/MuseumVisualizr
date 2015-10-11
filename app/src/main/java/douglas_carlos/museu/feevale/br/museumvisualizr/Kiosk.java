package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.util.Log;
import android.view.inputmethod.ExtractedTextRequest;

import java.io.File;

/**
 * Created by Douglas on 11/10/2015.
 */
public class Kiosk {

    private static String kiosks[] = {"quiosque01", "quiosque02", "quiosque03"};

    private static String getURLString(String str){
        return "file:///android_res/raw/" + str + ".html";
    }

    public static String getURLFrom(int index){
        return getURLString(kiosks[index]);
    }

    public static String[] getAll(){
        return kiosks;
    }

    public static int getIndexOf(String txt){
        int index = 0;
        for(String str : kiosks){
            if(str.equals(txt))
                return index;

            index++;
        }

        return 0;
    }
}
