package apdroid.clinica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class PacienteActivity extends AppCompatActivity {

    String[] opEstilo = new String[]{"Dark", "Segundo Estilo"};
    String[] opIdioma = new String[]{"Español", "Ingles"};
    EditText edNombres , edApellidos , edDNI , edCorreo  ;
    Spinner spIdioma , spEstilo ;

    ArrayAdapter<String> aaIdioma, aaEstilo;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        configurarControles();

    }

    private void configurarControles(){
        edNombres = (EditText)findViewById( R.id.edNombres) ;
        edApellidos = (EditText)findViewById( R.id.edApellidos) ;
        edDNI = (EditText)findViewById( R.id.edDNI) ;
        edCorreo = (EditText)findViewById( R.id.edCorreo) ;

        spIdioma = (Spinner) findViewById(R.id.spIdioma) ;
        spEstilo = (Spinner) findViewById(R.id.spEstilo) ;

        aaIdioma = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opIdioma);
        spIdioma.setAdapter(aaIdioma);

        aaEstilo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opEstilo);
        spEstilo.setAdapter(aaEstilo);


    }


}