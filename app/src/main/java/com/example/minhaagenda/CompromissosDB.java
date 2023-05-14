package com.example.minhaagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.minhaagenda.Compromisso;
import com.example.minhaagenda.DBHelper;
import com.example.minhaagenda.DBSchema;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class CompromissosDB {
    private Context context;
    private SQLiteDatabase db;

    public CompromissosDB(Context _context) {
        context = _context.getApplicationContext();
        db = new DBHelper(context).getWritableDatabase();
    }

    private static ContentValues obterValoresConteudo(Compromisso compromisso) {
        ContentValues valores = new ContentValues();
        valores.put(DBSchema.CompromissosTbl.Cols.uuid, compromisso.getUUID().toString());
        valores.put(DBSchema.CompromissosTbl.Cols.descricao, compromisso.getDescricao());
        valores.put(DBSchema.CompromissosTbl.Cols.data, compromisso.getData().toString());
        valores.put(DBSchema.CompromissosTbl.Cols.hora, compromisso.getHora().toString());
        return valores;
    }

    public void insertCompromisso(Compromisso compromisso) {
        ContentValues valores = obterValoresConteudo(compromisso);
        db.insert(DBSchema.CompromissosTbl.NOME, null, valores);
    }

    public ArrayList<Compromisso> selectCompromissos(String where, String[] args) {
        ArrayList<Compromisso> compromissos = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.CompromissosTbl.NOME, null, where, args, null, null,  null);

        if(cursor != null) {
            try {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.uuid)));
                    String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.descricao));
                    String data = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.data));
                    String hora = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.hora));
                    Compromisso compromisso = new Compromisso(uuid, data, hora, descricao);
                    compromissos.add(compromisso);
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }

        return compromissos;
    }

    public ArrayList<Compromisso> selectCompromissos() {
        ArrayList<Compromisso> compromissos = new ArrayList<>();
        Cursor cursor = db.query(DBSchema.CompromissosTbl.NOME, null, null, null, null, null,  null);

        if(cursor != null) {
            try {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.uuid)));
                    String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.descricao));
                    String data = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.data));
                    String hora = cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.CompromissosTbl.Cols.hora));
                    Compromisso compromisso = new Compromisso(uuid, data, hora, descricao);
                    compromissos.add(compromisso);
                    cursor.moveToNext();
                }
            } catch (Exception e) {
                Log.d("ERRO", e.getLocalizedMessage());
            } finally {
                cursor.close();
            }
        }

        return compromissos;
    }

    public void close() {
        db.close();
    }
}
