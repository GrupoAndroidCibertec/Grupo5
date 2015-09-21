package apdroid.clinica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import apdroid.clinica.entidades.Paciente;
import apdroid.clinica.service.ClinicaService;


public class PacienteActivity extends AppCompatActivity {

    String[] opEstilo = new String[]{"Dark", "Segundo Estilo"};
    String[] opIdioma = new String[]{"Español", "Ingles"};
    EditText edNombres , edApellidos , edDNI , edCorreo  ;
    Spinner spIdioma , spEstilo ;
    TextView tvUser ;
    ArrayAdapter<String> aaIdioma, aaEstilo;
    Button btActualizarDatos ;

    private ClinicaService clinicaService;
    Paciente paciente = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        configurarControles();

    }

    private void configurarControles(){
        clinicaService = ClinicaService.getSingleton();


        edNombres = (EditText)findViewById( R.id.edNombres) ;
        edApellidos = (EditText)findViewById( R.id.edApellidos) ;
        edDNI = (EditText)findViewById( R.id.edDNI) ;
        edCorreo = (EditText)findViewById( R.id.edCorreo) ;
        btActualizarDatos = (Button) findViewById(R.id.btActualizarDatos) ;
        btActualizarDatos.setOnClickListener(btActualizarPacienteOnClickListener);

        tvUser = (TextView) findViewById(R.id.tvUser) ;
        String nuser = this.getIntent().getStringExtra(MainActivity.ARG_USUARIO);
        paciente = clinicaService.consultaPacientexid(1) ;
        edNombres.setText(paciente.getNombres());
        edApellidos.setText(paciente.getApellidos());
        edDNI.setText(paciente.getNum_dni());
        edCorreo.setText(paciente.getCorreo());



        tvUser.setText(nuser);

        spIdioma = (Spinner) findViewById(R.id.spIdioma) ;
        spEstilo = (Spinner) findViewById(R.id.spEstilo) ;

        aaIdioma = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opIdioma);
        spIdioma.setAdapter(aaIdioma);

        aaEstilo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opEstilo);
        spEstilo.setAdapter(aaEstilo);
        //paciente.setIdioma("Ingles");
        //paciente.setEstilo("Segundo Estilo");
        int spinnerPosition = aaIdioma.getPosition(paciente.getIdioma());
        spIdioma.setSelection(spinnerPosition);
        spinnerPosition = aaEstilo.getPosition(paciente.getEstilo());
        spEstilo.setSelection(spinnerPosition);



    }
    View.OnClickListener btActualizarPacienteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validaCampos()== true) {
                Intent resultIntent = new Intent();
                Paciente data = new Paciente();
                data.setId_paciente(paciente.getId_paciente());
                data.setNum_dni(edDNI.getText().toString());
                data.setNombres(edNombres.getText().toString());
                data.setApellidos(edApellidos.getText().toString());
                data.setCorreo(edCorreo.getText().toString());
                data.setEstilo(spEstilo.getSelectedItem().toString());
                data.setIdioma(spIdioma.getSelectedItem().toString());
                clinicaService.actualizarPaciente(data);
                resultIntent.putExtra("data", data);
                setResult(RESULT_OK, resultIntent);
                finish();
            }

        }
    };

    private boolean validaCampos(){
        return true;
    }


}