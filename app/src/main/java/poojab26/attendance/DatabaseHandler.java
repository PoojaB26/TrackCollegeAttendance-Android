package poojab26.attendance;

/**
 * Created by pblead26 on 28-Nov-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "attendanceManager";

    // Contacts table name
    private static final String TABLE_NAME = "attendance";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SUBID = "subid";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_ABSENTNO = "absentno";
    private static final String KEY_PERC = "perc";
    private static final String[] Table_AllKeys = new String[] { KEY_SUBID, KEY_TOTAL, KEY_ABSENTNO };


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SUBID + " INTEGER, " + KEY_TOTAL + " DOUBLE," + KEY_ABSENTNO + " DOUBLE,"
                + KEY_PERC + " DOUBLE" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    public boolean insertData(String subid, double TOTAL, double ABSENTNO, double PERC) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SUBID,subid);
        contentValues.put(KEY_TOTAL,TOTAL);
        contentValues.put(KEY_ABSENTNO,ABSENTNO);
        contentValues.put(KEY_PERC,PERC);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public Cursor getRowData(String subid) {
        SQLiteDatabase db = this.getWritableDatabase();

       // String WHERE = KEY_SUBID + "=?";
     /*   Cursor cursor = db.query(TABLE_NAME, Table_AllKeys,
                WHERE, new String[] { subid },
                null, null, null);*/
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_SUBID + "='" + subid.trim()+"'";
        Cursor res = db.rawQuery(query, null);



        return res;
    }


    public Cursor getPercData(String subid) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT perc FROM " + TABLE_NAME + " WHERE " + KEY_SUBID + "='" + subid.trim()+"'";
        Cursor res = db.rawQuery(query, null);
    return res;
    }

    public boolean updateData(String subid, double TOTAL, double ABSENTNO, double PERC) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // contentValues.put(KEY_ID,id);
        contentValues.put(KEY_SUBID,subid);
        contentValues.put(KEY_TOTAL,TOTAL);
        contentValues.put(KEY_ABSENTNO,ABSENTNO);
        contentValues.put(KEY_PERC,PERC);
        db.update(TABLE_NAME, contentValues, "SUBID = ?",new String[] { subid });
        return true;
    }
}
