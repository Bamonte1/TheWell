package com.example.thewell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TheWell";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PHONE_TYPE = "phone_type";
    private static final String KEY_NOTE = "notes";
    private static final String KEY_DELIVERY = "delivery";
    private static final String KEY_DATE = "date";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate: Table is being created.");

        String createQuery = "Create Table " + TABLE_CONTACTS + "(" +
                KEY_ID + " Integer Primary Key, " +
                KEY_NAME + " Text, " +
                KEY_ADDRESS + " Text, " +
                KEY_CITY + " Text, " +
                KEY_STATE + " Text, " +
                KEY_PHONE + " Text, " +
                KEY_PHONE_TYPE + " Text, " +
                KEY_NOTE + " Text, " +
                KEY_DELIVERY + " Text, " +
                KEY_DATE + " Text" + ")";

        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: Database being upgraded.");

        db.execSQL("Drop Table If Exists " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addInfo(UserInfo info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, info.getName());
        values.put(KEY_ADDRESS, info.getAddress());
        values.put(KEY_CITY, info.getCity());
        values.put(KEY_STATE, info.getState());
        values.put(KEY_PHONE, info.getPhone());
        values.put(KEY_PHONE_TYPE, info.getPhoneType());
        values.put(KEY_NOTE, info.getNote());
        values.put(KEY_DELIVERY, info.getDelivery());
        values.put(KEY_DATE, info.getDate());

        db.insert(TABLE_CONTACTS, null, values);

        db.close();

    }

    public List<UserInfo> getAllInfo() {
        List<UserInfo> infoList = new ArrayList<UserInfo>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                UserInfo info = new UserInfo();
                info.setId(Integer.parseInt(cursor.getString(0)));
                info.setName(cursor.getString(1));
                info.setAddress(cursor.getString(2));
                info.setCity(cursor.getString(3));
                info.setState(cursor.getString(4));
                info.setPhone(cursor.getString(5));
                info.setPhoneType(cursor.getString(6));
                info.setNote(cursor.getString(7));
                info.setDelivery(cursor.getString(8));
                info.setDate(cursor.getString(9));

                infoList.add(info);
            } while (cursor.moveToNext());
        }

        return infoList;

    }

}
