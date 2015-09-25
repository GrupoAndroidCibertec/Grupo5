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
        configCalendar();

        configUser();




    }



    //<editor-fold desc="Config Spinner - Especialidad">
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
    //</editor-fold>



    //<editor-fold desc="Config Calendar">
    private SimpleDateFormat formatoFecha;
    private ImageButton btCalendar;
    private TextView tvFecha;
    Calendar calendario = Calendar.getInstance();

    private void configCalendar(){

        tvFecha = (TextView) findViewById(R.id.etFecha);
        btCalendar = (ImageButton) findViewById(R.id.btCal);

        btCalendar.setOnClickListener(btCalendarOnClickListener);
        tvFecha.setOnClickListener(tvFechaOnClickListener);

        formatoFecha = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    }

    DatePickerDialog.OnDateSetListener dpFechaOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFecha();

        }
    };

    View.OnClickListener tvFechaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFecha();
        }
    };


    View.OnClickListener btCalendarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setFecha();

        }
    };

    public void updateFecha() {
        tvFecha.setText(formatoFecha.format(calendario.getTime()));

    }

    public void setFecha() {
        new DatePickerDialog(NuevaCitaActivity.this, dpFechaOnDateSetListener,
                calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show();
    }
    //</editor-fold>

    //<editor-fold desc="Config - USER">
    private TextView tvUser;

    private void configUser(){

        tvUser= (TextView) findViewById(R.id.tvUser);

        String nuser = this.getIntent().getStringExtra(MainActivity.ARG_USUARIO);
        tvUser.setText(nuser);

    }
    //</editor-fold>


}
