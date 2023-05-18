package com.example.minhaagenda;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarAgendamentos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarAgendamentos extends Fragment implements AutoCloseable {

    CompromissosDB db;
    ArrayList<String> compromissos;

    EditText dataEdt;
    ListView lstAgendamentos;
    Button btnBuscar;

    public ListarAgendamentos() {
        compromissos = new ArrayList<>();
    }

    public static ListarAgendamentos newInstance() {
        ListarAgendamentos fragment = new ListarAgendamentos();
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

        View view = inflater.inflate(R.layout.fragment_listar_agendamentos, container, false);
        dataEdt = (EditText)view.findViewById(R.id.listar_edt_data);
        lstAgendamentos = (ListView)view.findViewById(R.id.lst_agendamentos);
        btnBuscar = (Button)view.findViewById(R.id.btn_buscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarCompromissos();
            }
        });

        BuscarCompromissos();

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

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dataEdt.setText("");
                    }
                });
            }
        });
        return view;
    }

    public void BuscarCompromissos() {
        compromissos.clear();
        String data = dataEdt.getText().toString();

        ArrayList<Compromisso> resultado = new ArrayList<>();

        if(data.length() > 0) {
            resultado = db.selectCompromissos("data = ?", new String[]{ data });
        } else {
            resultado = db.selectCompromissos();
        }

        for(int i = 0; i < resultado.size(); i++) {
            Compromisso c = resultado.get(i);
            compromissos.add(c.getDescricao() + " - " + c.getData() + " - " + c.getHora());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.item_agendamento, compromissos);
        lstAgendamentos.setAdapter(adapter);
    }

    @Override
    public void close() throws Exception {
        db.close();
    }
}