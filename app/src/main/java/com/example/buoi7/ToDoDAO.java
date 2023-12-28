package com.example.buoi7;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ToDoDAO {
    private final DbHelper dbHelper;
    public ToDoDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<ToDo> getListTOdo(){
        ArrayList<ToDo> list =new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TODO", null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    list.add(new ToDo(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5)));

                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e(TAG, "getListToDo:"+ e);
        }finally {
            database.endTransaction();
        }
        return  list;
    }

    public void addToDo(ToDo toDo) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", toDo.getTitle());
        values.put("CONTENT", toDo.getContent());
        values.put("DATE", toDo.getDate());
        values.put("TYPE", toDo.getType());
        values.put("STATUS", toDo.getStatus());

        database.insert("TODO", null, values);
        database.close();
    }

    public void updateToDo(ToDo toDo) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", toDo.getTitle());
        values.put("CONTENT", toDo.getContent());
        values.put("DATE", toDo.getDate());
        values.put("TYPE", toDo.getType());
        values.put("STATUS", toDo.getStatus());

        String whereClause = "ID=?";
        String[] whereArgs = {String.valueOf(toDo.getId())};

        database.update("TODO", values, whereClause, whereArgs);
        database.close();
    }

    public void deleteToDo(ToDo toDo) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String whereClause = "ID=?";
        String[] whereArgs = {String.valueOf(toDo.getId())};

        database.delete("TODO", whereClause, whereArgs);

        database.close();
    }



}
