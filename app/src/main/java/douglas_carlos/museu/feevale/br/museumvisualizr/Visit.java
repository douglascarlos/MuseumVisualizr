package douglas_carlos.museu.feevale.br.museumvisualizr;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Visit {

    public static String TABLE_NAME = "visits";
    public static String COL_ID = "id_visit";
    public static String COL_DATE = "date";
    public static String COL_KIOSK_CODE = "kiosk_code";
    public static String COL_SYNCED = "synced";
    public static String COL_USER_NAME = "user_name";
    public static String COLUMNS[] = {COL_ID, COL_DATE, COL_KIOSK_CODE, COL_SYNCED, COL_USER_NAME};

    public static String onCreateSQL() {
        return "create table " + TABLE_NAME + "(" +
            COL_ID + " integer primary key autoincrement," +
            COL_DATE + " text not null," +
            COL_KIOSK_CODE + " text not null," +
            COL_SYNCED + " integer not null," +
            COL_USER_NAME + " text not null" +
            ")";
    }

    private long idVisit;
    private String date;
    private String kioskCode;
    private int synced;
    private String userName;

    public Visit(){
        idVisit = 0;
        synced = 0;
    }

    public Visit(Cursor row){
        idVisit = row.getInt(row.getColumnIndex(COL_ID));
        date = row.getString(row.getColumnIndex(COL_DATE));
        kioskCode = row.getString(row.getColumnIndex(COL_KIOSK_CODE));
        synced = row.getInt(row.getColumnIndex(COL_SYNCED));
    }

    public long getId(){
        return idVisit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKioskCode() {
        return kioskCode;
    }

    public void setKioskCode(String kioskCode) {
        this.kioskCode = kioskCode;
    }

    public void setUserName(String userName){ this.userName = userName; }

    public String getUserName(){ return this.userName; }

    public boolean save(DBHelper db){
        if(idVisit == 0){
            ContentValues values = new ContentValues();
            values.put(COL_DATE, date);
            values.put(COL_KIOSK_CODE, kioskCode);
            values.put(COL_SYNCED, synced);
            values.put(COL_USER_NAME, userName);
            idVisit = db.getDB().insert(TABLE_NAME, null, values);

            return true;
        }
        else{
//            in case of updates
        }

        return false;
    }

    public static void saveVisit(DBHelper db, String code, String userName) {
        Visit visit = new Visit();
        visit.setKioskCode(code);
        visit.setDate(DBHelper.getToday());
        visit.setUserName(userName);
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
