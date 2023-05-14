package com.example.minhaagenda;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Compromisso {
    private UUID mUUID;
    private String mData;
    private String mHora;
    private String mDescricao;

    public Compromisso(String data, String hora, String descricao) {
        mData = data;
        mHora = hora;
        mDescricao = descricao;
        mUUID = UUID.randomUUID();
    }

    public Compromisso(UUID uuid, String data, String hora, String descricao) {
        mData = data;
        mHora = hora;
        mDescricao = descricao;
        mUUID = uuid;
    }

    public String getData() { return mData; }
    public String getHora() { return mHora; }
    public String getDescricao() { return mDescricao; }
    public UUID getUUID() { return mUUID; }

    public void setUUID(UUID uuid) { mUUID = uuid; }
    public void setData(String data) { mData = data; }
    public void setHora(String hora) { mHora = hora; }
    public void setDescricao(String descricao) { mDescricao = descricao; }
}
