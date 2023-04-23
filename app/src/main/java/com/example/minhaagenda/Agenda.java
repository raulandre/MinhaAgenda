package com.example.minhaagenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private ArrayList<Compromisso> mCompromissos;

    public Agenda() {
        mCompromissos = new ArrayList<Compromisso>();
    }

    public void AdicionaCompromisso(Compromisso compromisso) {
        mCompromissos.add(compromisso);
    }

    public void RemoveCompromisso(Compromisso compromisso) {
        mCompromissos.removeIf(c -> c.getUUID().equals(compromisso.getUUID()));
    }

    public List<Compromisso> ObterPorData(Date data) {
        return mCompromissos.stream().filter(c -> c.getData().equals(data)).collect(Collectors.toList());
    }
}
