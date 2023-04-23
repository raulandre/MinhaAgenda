package com.example.minhaagenda;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Compromisso {
    private UUID mUUID;
    private Date mData;
    private Time mHora;
    private String mDescricao;

    Compromisso(Date data, Time hora, String descricao) {
        mData = data;
        mHora = hora;
        mDescricao = descricao;
        mUUID = UUID.randomUUID();
    }

    public Date getData() { return mData; }
    public Time getHora() { return mHora; }
    public String getDescricao() { return mDescricao; }
    public UUID getUUID() { return mUUID; }

    public void setData(Date data) { mData = data; }
    public void setHora(Time hora) { mHora = hora; }
    public void setDescricao(String descricao) { mDescricao = descricao; }
}
