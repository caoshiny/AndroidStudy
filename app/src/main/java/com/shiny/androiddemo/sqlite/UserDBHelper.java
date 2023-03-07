package com.shiny.androiddemo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiny
 * @description
 * @date 2023-3-7 9:33
 */
public class UserDBHelper extends SQLiteOpenHelper {
    private static UserDBHelper userDBHelper = null;

    private static final String DB_NAME = "user.db";
    private static final String TABLE_NAME = "user_info";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase readDB = null;
    private SQLiteDatabase writeDB = null;

    // 私有构造方法 单例模式
    private UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 懒汉模式
    public static UserDBHelper getInstance(Context context) {
        if (userDBHelper == null) {
            userDBHelper = new UserDBHelper(context);
        }
        return userDBHelper;
    }

    // 打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (readDB == null || !readDB.isOpen()) {
            readDB = userDBHelper.getReadableDatabase();
        }
        return readDB;
    }

    // 打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (writeDB == null || !writeDB.isOpen()) {
            writeDB = userDBHelper.getWritableDatabase();
        }
        return writeDB;
    }

    // 创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INTEGER NOT NULL," +
                "height LONG NOT NULL," +
                "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(createTableSql);
    }

    // 数据库版本更新时执行此方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 返回新增数据的行号
    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        values.put("married", user.isMarried());
        return writeDB.insert(TABLE_NAME, null, values);
    }

    // 返回一共删除了几行
    public long deleteByName(String name) {
        // 删除所有
        // writeDB.delete(TABLE_NAME, "1=1", null);
        return writeDB.delete(TABLE_NAME, "name=?", new String[]{name});
    }

    // 更新的总行数
    public long update(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        values.put("married", user.isMarried());
        return writeDB.update(TABLE_NAME, values, "name=?", new String[]{user.getName()});
    }

    // 查询数据库里所有记录
    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Cursor cursor = readDB.query(TABLE_NAME, null, null, null, null, null, null);
        // 循环取出游标指向的每条记录
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setAge(cursor.getInt(2));
            user.setHeight(cursor.getLong(3));
            user.setWeight(cursor.getFloat(4));
            user.setMarried(cursor.getInt(5) != 0);
            list.add(user);
        }

        return list;
    }

    // 关闭数据库连接
    public void closeLink() {
        if (readDB != null && readDB.isOpen()) {
            readDB.close();
            readDB = null;
        }

        if (writeDB != null && writeDB.isOpen()) {
            writeDB.close();
            writeDB = null;
        }
    }
}
