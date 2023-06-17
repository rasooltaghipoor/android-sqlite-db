package com.barana.location.mydatabase1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rasool on 5/8/17.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "students.db";
    public static final String TBL_NAME = "stud_info";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO: Uncommect the line below in first run. It creates initial database
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE stud_info (stuID VARCHAR PRIMARY KEY  NOT NULL , stuName VARCHAR, stuMajor VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+TBL_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertToDB(String id , String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("stuID" , id);
        cn.put("stuName" , name);
        long res = db.insert(TBL_NAME , null , cn);
        if(res == -1)
            return false;
        return  true;

    }

    public boolean deleteDB(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TBL_NAME , "stuID = ?" , new String[]{id});
        if(res == 0)
            return false;
        return  true;

    }

    public boolean updateDB(String id , String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("stuID" , id);
        cn.put("stuName" , name);
        long res = db.update(TBL_NAME , cn ,  "stuID = ?" , new String[]{id});
        if(res < 1)
            return false;
        return  true;

    }

    public Cursor showAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TBL_NAME , null);
        return result;
    }
}
