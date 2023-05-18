package com.example.minhaagenda;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NovoAgendamento extends Fragment implements AutoCloseable {

    CompromissosDB db;

    EditText dataEdt;
    EditText horaEdt;
    EditText descricaoEdt;

    Button btnSalvar;

    public NovoAgendamento() { }

    public static NovoAgendamento newInstance() {
        NovoAgendamento fragment = new NovoAgendamento();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new CompromissosDB(getContext());

        View view = inflater.inflate(R.layout.fragment_novo_agendamento, container, false);
        dataEdt = (EditText)view.findViewById(R.id.edt_data);
        horaEdt = (EditText)view.findViewById(R.id.edt_hora);
        descricaoEdt = (EditText)view.findViewById(R.id.descricao);
        btnSalvar = (Button)view.findViewById(R.id.btn_salvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Compromisso c = new Compromisso(dataEdt.getText().toString(), horaEdt.getText().toString(), descricaoEdt.getText().toString());
                    db.insertCompromisso(c);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    ListarAgendamentos listarAgendamentos = null;
                    for(Fragment f : fm.getFragments()) {
                        if(f instanceof ListarAgendamentos) {
                            listarAgendamentos = (ListarAgendamentos)f;
                            break;
                        }
                    }

                    if(listarAgendamentos != null) {
                        listarAgendamentos.BuscarCompromissos();
                    }

                    descricaoEdt.setText("");
                    dataEdt.setText("");
                    horaEdt.setText("");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        dataEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dataEdt.setText(String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear, year));
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        horaEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int min) {
                        horaEdt.setText(String.format("%02d:%02d", hora, min));
                    }
                }, c.getTime().getHours(), c.getTime().getMinutes(), false);
                timePickerDialog.show();
            }
        });

        return view;
    }

    @Override
    public void close() throws Exception {
        db.close();
    }
}