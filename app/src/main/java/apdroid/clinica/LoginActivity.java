package apdroid.clinica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apdroid.clinica.entidades.DatosCita;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private EditText etUser, etPass;
    private Button btIngresar;
    private final String ARG_USER="arg_user";
    private final String ARG_PASS="arg_pass";
    private final String ARG_NUSER="arg_pass";



    View.OnClickListener btIngresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
// Se tiene q cambiar por el tipo de Perfil

            if (user.equalsIgnoreCase("angelo") && pass.equalsIgnoreCase("123456")
                    ||user.equalsIgnoreCase("antonio") && pass.equalsIgnoreCase("abcde")
                    ||user.equalsIgnoreCase("edinson") && pass.equalsIgnoreCase("qwerty")) {

                //Intent i=new Intent(getApplicationContext(),MainActivity.class);
                //i.putExtra("user", 1);
                //startActivity(i);


                user=user.toLowerCase();

                int nUser=0;
                if (user.equals("angelo")){
                    nUser=1;
                }
                if (user.equals("edinson")){
                    nUser=2;
                }
                if (user.equals("antonio")){
                    nUser=3;
                }



                SharedPreferences.Editor spe = sp.edit();
                spe.putString(ARG_USER, user);
                spe.putString(ARG_PASS, pass);
                spe.putInt(ARG_NUSER, nUser);
                spe.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                intent.putExtra("user",nUser);
                startActivity(intent);
                finish();


            }else{
                Toast.makeText(getApplicationContext(), "Ups !!!", Toast.LENGTH_LONG).show();
            }



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        btIngresar = (Button) findViewById(R.id.btIngresar);
        btIngresar.setOnClickListener(btIngresarOnClickListener);

        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        if (sp.contains(ARG_USER)
                && sp.contains(ARG_NUSER)
                && sp.contains(ARG_PASS)
         //       && !sp.getString(ARG_USER, "").isEmpty()
        //        && !sp.getString(ARG_PASS, "").isEmpty()
            )
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user",sp.getInt(ARG_NUSER,0));
            startActivity(intent);
            finish();
        }



    }










}
