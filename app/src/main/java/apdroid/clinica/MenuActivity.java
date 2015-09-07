package apdroid.clinica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** ok
 * Created by AngeloPaulo on 28/agosto/2015.
 */
public class MenuActivity extends AppCompatActivity {

    Button btNuevaCita;
    TextView tvUser;
    View.OnClickListener btNuevaCitaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i2 = new Intent(MenuActivity.this, NuevaCitaActivity.class);
            startActivity(i2);

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        int usuario = this.getIntent().getIntExtra("user", 0);

        tvUser = (TextView) findViewById(R.id.tvUser);

        switch (usuario) {
            case 1:
                tvUser.setText("Angelo Paulo Verástegui Ponce");
                break;
        }


        btNuevaCita = (Button) findViewById(R.id.btNuevaCita);
        btNuevaCita.setOnClickListener(btNuevaCitaOnClickListener);


}

}
