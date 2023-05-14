package com.example.minhaagenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String DATABASE = "db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DBSchema.CompromissosTbl.NOME + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                DBSchema.CompromissosTbl.Cols.uuid + "," +
                DBSchema.CompromissosTbl.Cols.descricao + "," +
                DBSchema.CompromissosTbl.Cols.data + "," +
                DBSchema.CompromissosTbl.Cols.hora + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBSchema.CompromissosTbl.NOME);
        onCreate(db);
    }
}
