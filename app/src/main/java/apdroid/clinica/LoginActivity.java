package apdroid.clinica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText etUser, etPass;
    Button btIngresar;

    View.OnClickListener btIngresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = etUser.getText().toString();
            String pass = etPass.getText().toString();

            if (user.equalsIgnoreCase("angelo") && pass.equalsIgnoreCase("123456")) {
//                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user", 1);
                startActivity(i);
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


    }










}
