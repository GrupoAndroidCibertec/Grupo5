package apdroid.clinica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import apdroid.clinica.entidades.DatosCita;

public class ReprogramarCitaActivity extends AppCompatActivity {

    private DatosCita datosCita;

    private TextView tvEspecialidad;
    private TextView tvDoctor;
    private TextView etFecha;
    private Spinner spHorarioReprog;

    private ArrayAdapter<String> aaHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprogramar_cita);
        tvEspecialidad = (TextView)findViewById(R.id.tvEspecialidad);
        tvDoctor = (TextView)findViewById(R.id.tvDoctor);
        etFecha = (TextView)findViewById(R.id.etFecha);
        spHorarioReprog = (Spinner)findViewById(R.id.spHorarioReprog);

        String []opHorario = getResources().getStringArray(R.array.ListaHorarios);
        aaHorario = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, opHorario);
        spHorarioReprog.setAdapter(aaHorario);

        cargarDatosCita();

    }

    private void cargarDatosCita(){
        Intent intent = getIntent();
        if( intent != null ){
            datosCita = intent.getParcelableExtra(MainActivity.ARG_DATOS_CITA);
            tvEspecialidad.setText(datosCita.getEspecialidad());
            tvDoctor.setText(datosCita.getDoctor());
            etFecha.setText(datosCita.getFecha());


        }


    }




}
