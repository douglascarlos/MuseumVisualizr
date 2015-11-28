package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by jszab on 22/11/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "main.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE", "onCreate!");
        db.execSQL(Visit.onCreateSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DATABASE", "onUpgrade!");
        db.execSQL("DROP TABLE IF EXISTS " + Visit.TABLE_NAME);
        onCreate(db);
    }

    public void open(){
        db = getWritableDatabase();
    }

    public SQLiteDatabase getDB(){
        return db;
    }

    public void close(){
        db.close();
    }

    public static String getToday(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) +"-"+
                c.get(Calendar.MONTH) +"-"+
                c.get(Calendar.DAY_OF_MONTH) +" "+
                c.get(Calendar.HOUR_OF_DAY) +":"+
                c.get(Calendar.MINUTE) +":"+
                c.get(Calendar.SECOND);
    }
}
