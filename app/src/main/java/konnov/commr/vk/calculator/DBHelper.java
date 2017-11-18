package konnov.commr.vk.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 11/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "calculatorhistory";
    private static final String TABLE_NAME = "output";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SOLUTION = "solution";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SOLUTION + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String solution){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SOLUTION, solution);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList dbToList(){
        ArrayList<String> list = new ArrayList<String>();
        String dbString;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("solution")) != null) {
                dbString = "";
                dbString += c.getString(c.getColumnIndex("solution"));
                list.add(dbString);
            }
            c.moveToNext();
        }
        db.close();
        return list;
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
