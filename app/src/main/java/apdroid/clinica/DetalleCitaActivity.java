package apdroid.clinica;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import apdroid.clinica.entidades.DatosCita;


public class DetalleCitaActivity extends AppCompatActivity {

    private TextView tvNombreEspecialidad , tv_fecha_hora, tvNombreDoctor , tv_nombre_local , tv_detalle_consulta ,tv_l_detalle_consulta ;
    private Button     btanularcita , btreprogramar ;

    private int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cita);
        configurarControles();
    }

    private void configurarControles(){
        tvNombreEspecialidad = (TextView) findViewById(R.id.tvNombreEspecialidad);
        tv_fecha_hora = (TextView) findViewById(R.id.tv_fecha_hora);
        tvNombreDoctor = (TextView) findViewById(R.id.tvNombreDoctor);
        tv_nombre_local = (TextView) findViewById(R.id.tv_nombre_local);
        tv_detalle_consulta = (TextView) findViewById(R.id.tv_detalle_consulta);
        tv_l_detalle_consulta = (TextView) findViewById(R.id.tv_l_detalle_consulta);

        btanularcita = (Button) findViewById(R.id.btanularcita);
        btreprogramar = (Button) findViewById(R.id.btreprogramar);
        btanularcita.setOnClickListener(btanularcitaOnClickListener);
        btreprogramar.setOnClickListener(btreprogramarOnClickListener);


        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(MainActivity.ARG_DATOS_CITA)) {
            DatosCita datosCita = getIntent().getParcelableExtra(MainActivity.ARG_DATOS_CITA);

            if(datosCita.getEstado().equals("PROGRAMADO")){
                tv_detalle_consulta.setVisibility(View.INVISIBLE);
                tv_l_detalle_consulta.setVisibility(View.INVISIBLE);
                btanularcita.setVisibility(View.VISIBLE);
                btreprogramar.setVisibility(View.VISIBLE);
                tv_detalle_consulta.setText("" );
            }else{
                tv_detalle_consulta.setText(datosCita.getDetalleConsulta() );

                tv_detalle_consulta.setVisibility(View.VISIBLE);
                tv_l_detalle_consulta.setVisibility(View.VISIBLE);
                btanularcita.setVisibility(View.INVISIBLE);
                btreprogramar.setVisibility(View.INVISIBLE);

            }
            tvNombreEspecialidad.setText(datosCita.getEspecialidad());
            tv_fecha_hora.setText(datosCita.getFecha().concat(" ").concat(datosCita.getHora()) );
            tvNombreDoctor.setText(datosCita.getDoctor());
            tv_nombre_local.setText(datosCita.getLocal());



            position = getIntent().getIntExtra(MainActivity.ARG_POSITION, -1);
        }



    }

    View.OnClickListener btanularcitaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    View.OnClickListener btreprogramarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };





}
