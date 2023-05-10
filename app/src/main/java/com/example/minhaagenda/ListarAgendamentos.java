package com.example.minhaagenda;

import android.app.DatePickerDialog;
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
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarAgendamentos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarAgendamentos extends Fragment {

    EditText dataEdt;
    ListView lstAgendamentos;
    Button btnBuscar;

    public ListarAgendamentos() {
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
        View view = inflater.inflate(R.layout.fragment_listar_agendamentos, container, false);
        dataEdt = (EditText)view.findViewById(R.id.listar_edt_data);
        lstAgendamentos = (ListView)view.findViewById(R.id.lst_agendamentos);
        btnBuscar = (Button)view.findViewById(R.id.btn_buscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Etapa3", "Clicou no botao buscar na data " + dataEdt.getText());
            }
        });

        String[] agendamentosTeste = { "Estudar - 10:30", "Fazer compras - 11:30", "Dominar o mundo - 12:30" };
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.item_agendamento, agendamentosTeste);
        lstAgendamentos.setAdapter(adapter);

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
        return view;
    }
}