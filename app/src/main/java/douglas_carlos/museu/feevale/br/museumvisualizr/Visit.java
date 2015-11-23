package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jszab on 23/11/2015.
 */
public class Visit {

    public static String TABLE_NAME = "visits";
    public static String COL_ID = "id_visit";
    public static String COL_DATE = "date";
    public static String COL_KIOSK_CODE = "kiosk_code";
    public static String COL_SYNCED = "synced";
    public static String COLUMNS[] = {COL_ID, COL_DATE, COL_KIOSK_CODE, COL_SYNCED};

    public static String onCreateSQL() {
        return "create table " + TABLE_NAME + "(" +
            COL_ID + " integer primary key autoincrement," +
            COL_DATE + " integer not null," +
            COL_KIOSK_CODE + " text not null," +
            COL_SYNCED + " integer not null" +
            ")";
    }

    private long idVisit;
    private long date;
    private String kioskCode;
    private int synced;

    public Visit(){
        idVisit = 0;
        synced = 0;
    }

    public Visit(Cursor row){
        idVisit = row.getInt(row.getColumnIndex(COL_ID));
        date = row.getInt(row.getColumnIndex(COL_DATE));
        kioskCode = row.getString(row.getColumnIndex(COL_KIOSK_CODE));
        synced = row.getInt(row.getColumnIndex(COL_SYNCED));
    }

    public long getId(){
        return idVisit;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getKioskCode() {
        return kioskCode;
    }

    public void setKioskCode(String kioskCode) {
        this.kioskCode = kioskCode;
    }

    public boolean save(DBHelper db){
        if(idVisit == 0){
            ContentValues values = new ContentValues();
            values.put(COL_DATE, date);
            values.put(COL_KIOSK_CODE, kioskCode);
            values.put(COL_SYNCED, synced);
            idVisit = db.getDB().insert(TABLE_NAME, null, values);

            return true;
        }
        else{
//            in case of updates
        }

        return false;
    }

    public static void saveVisit(DBHelper db, String code) {
        Visit visit = new Visit();
        visit.setKioskCode(code);
        visit.setDate(System.currentTimeMillis() / 1000L);
        visit.save(db);
    }

    public static List<Visit> all(DBHelper db) {
        List<Visit> list = new ArrayList<Visit>();
        Cursor cursor = db.getDB().query(TABLE_NAME, COLUMNS, null, null, null, null, COL_ID);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Visit visit = new Visit(cursor);
                list.add(visit);
            }while(cursor.moveToNext());
        }

        return list;
    }
}
