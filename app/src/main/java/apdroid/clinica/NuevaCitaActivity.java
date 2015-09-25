package apdroid.clinica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import apdroid.clinica.adapter.spinner.SPDoctorAdapter;
import apdroid.clinica.adapter.spinner.SPEspecialidadAdapter;
import apdroid.clinica.adapter.spinner.SPHorarioAdapter;
import apdroid.clinica.dao.DB_Helper;
import apdroid.clinica.dao.DB_Manager;
import apdroid.clinica.dao.DoctorDao;
import apdroid.clinica.dao.EspecialidadDao;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Doctor;
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
        configSpDoctor();
        configSpHorario();
        configBtReservarCita();
        configUser();
    }



    //<editor-fold desc="Config Spinner - Especialidad">
    private Spinner spEspecialidad;
    private ClinicaService clinicaService;
    private SPEspecialidadAdapter spEspecialidadAdapter;

    private void configSpEspecialidad(){

        spEspecialidad = (Spinner)findViewById(R.id.spEspecialidad);
        clinicaService = ClinicaService.getSingleton();
        spEspecialidad.setOnItemSelectedListener(spEspOnItemSelectedListener);

        ArrayList<Especialidad> listEspec = clinicaService.listarEspecialidades();
        ArrayList<Especialidad> lstSpinner = new ArrayList<>(listEspec);
        lstSpinner.add(0, new Especialidad(-1, "<Seleccione Especialidad>") );
        spEspecialidadAdapter = new SPEspecialidadAdapter(this, lstSpinner);
        spEspecialidad.setAdapter(spEspecialidadAdapter);

    }
    //</editor-fold>

    //<editor-fold desc="Config Spinner Doctor">
    private Spinner spDoctor;
    //private ClinicaService clinicaService;
    //
    private SPDoctorAdapter spDoctorAdapter;

    //private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private DB_Manager manager;


    private void configSpDoctor(){

        spDoctor= (Spinner)findViewById(R.id.spDoctor);
        spDoctor.setOnItemSelectedListener(spDocOnItemSelectedListener);




        ArrayList<Doctor> listDoc = clinicaService.listarDoctores();

        ArrayList<Doctor> lstSpinnerDoc = new ArrayList<>(listDoc);

        spDoctorAdapter= new SPDoctorAdapter(this,0,lstSpinnerDoc);
        spDoctor.setAdapter(spDoctorAdapter);

    }
    //</editor-fold>

    //<editor-fold desc="Doc Seleccionado">
    String docSel;

    AdapterView.OnItemSelectedListener spDocOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    docSel="Esperanza";
                    break;
                case 1:
                    docSel="Aldo";
                    break;
                case 2:
                    docSel="Pedro";
                    break;
                case 3:
                    docSel="Ruth";
                    break;
                case 4:
                    docSel="Aria";
                    break;
                case 5:
                    docSel="Minerva";
                    break;
                case 6:
                    docSel="Laura";
                    break;
                case 7:
                    docSel="Marla";
                    break;
                case 8:
                    docSel="Margarita";
                    break;
                case 9:
                    docSel="Rita";
                    break;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    //</editor-fold>

    //<editor-fold ref">
    //    private void activeSpinner(String selection) {
//
//        //Creando Adaptador para ArtistSpinner con el id del género
//        spDoctorAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_spinner_item,
//                dataSource.getArtistsByGenre(genreSelection),
//                new String[]{DataBaseScript.ArtistColumns.NAME_ARTIST},
//                new int[]{android.R.id.text1},
//                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//
//        //Seteando el adaptador creado
//        artistSpinner.setAdapter(artistSpinnerAdapter);
//
//        //Relacionado la escucha
//        artistSpinner.setOnItemSelectedListener(this);
//
//    }
    //</editor-fold>

    //<editor-fold desc=" Select - Especialidad">

    String espSel;

    AdapterView.OnItemSelectedListener spEspOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 1:
                    espSel="Cardiologia";
                    break;
                case 2:
                    espSel="Odontologia";
                    break;
                case 3:
                    espSel="Medicina General";
                    break;
                case 4:
                    espSel="Pediatría";
                    break;
                case 5:
                    espSel="Geriatría";
                    break;
                case 6:
                    espSel="Gastroenterología";
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    //</editor-fold>

    //<editor-fold desc="Config Spinner - Horario">

    private Spinner spHorario;
    //private ClinicaService clinicaService;
    private SPHorarioAdapter spHorarioAdapter;

    private void configSpHorario(){

        spHorario= (Spinner)findViewById(R.id.spHorario);
        ArrayList<Doctor> listDoc = clinicaService.listarDoctores();

        ArrayList<Doctor> lstSpinnerDoc = new ArrayList<>(listDoc);

        spHorarioAdapter= new SPHorarioAdapter(this,0,lstSpinnerDoc);
        spHorario.setAdapter(spHorarioAdapter);

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

    //<editor-fold desc="Config Button Reservar Cita">
    private Button btReservCita;
    private DatosCita datosCita;

    private void configBtReservarCita() {
        btReservCita = (Button) findViewById(R.id.btReservCita);
        btReservCita.setOnClickListener(btReservCitaOnClickListener);
    }



    View.OnClickListener btReservCitaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent resultIntent=new Intent();
            DatosCita data=new DatosCita();

            data.setEspecialidad(espSel);
            data.setDoctor(docSel);
//            tvFecha.setText(formatoFecha.format(calendario.getTime()));
            data.setFecha(tvFecha.getText().toString());
            data.setDetalleConsulta("Detalle");
            data.setEstado("Estado");
            data.setHora("Hora");
            data.setIdCita(5);
            data.setIdDoctor(3);
            data.setIdEspecialidad(2);
            data.setIdPaciente(1);
//            data.setDoctor(tvUser.getText().toString());
//            data.setFecha(tvFecha.getText().toString());
//            data.setDoctor(spDoctor.getSelectedItem().toString());
//            data.setHora(spHorario.getSelectedItem().toString());
            resultIntent.putExtra("data", data);
            setResult(RESULT_OK, resultIntent);
            //Toast.makeText(getApplicationContext(),"Muchas Gracias", Toast.LENGTH_LONG).show();
            finish();



            //Intent intent= new Intent();
            //datosCita.setFecha(tvFecha.getText().toString());
            //datosCita.setHora((String) spHorario.getSelectedItem());
            //datosCita.setEspecialidad((String) spEspecialidad.getSelectedItem());
            //datosCita.setDoctor((String) spDoctor.getSelectedItem());

            //intent.putExtra(MainActivity.ARG_DATOS_CITA, datosCita);

            //clinicaService.nuevaCita(datosCita);

//            setResult(RESULT_OK, intent);
//            finish();



            //Toast.makeText(getApplicationContext(),"Muchas Gracias", Toast.LENGTH_LONG).show();


        }
    };
    //</editor-fold>


}
