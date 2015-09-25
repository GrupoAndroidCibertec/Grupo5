package apdroid.clinica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import apdroid.clinica.adapter.spinner.SPEspecialidadAdapter;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Especialidad;
import apdroid.clinica.service.ClinicaService;

/**
 * Created by AngeloPaulo on 01/septiembre/2015.
 */
public class NuevaCitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevacita);

        configSpEspecialidad();




    }



    private Spinner spEspecialidad;
    private ClinicaService clinicaService;
    private SPEspecialidadAdapter spEspecialidadAdapter;

    private void configSpEspecialidad(){

        spEspecialidad = (Spinner)findViewById(R.id.spEspecialidad);
        clinicaService = ClinicaService.getSingleton();
        //spEspecialidad.setOnItemSelectedListener(spEspOnItemSelectedListener);

        ArrayList<Especialidad> listEspec = clinicaService.listarEspecialidades();
        ArrayList<Especialidad> lstSpinner = new ArrayList<>(listEspec);
        lstSpinner.add(0, new Especialidad(-1, "<Seleccione Especialidad>") );
        spEspecialidadAdapter = new SPEspecialidadAdapter(this, lstSpinner);
        spEspecialidad.setAdapter(spEspecialidadAdapter);

    }


}
