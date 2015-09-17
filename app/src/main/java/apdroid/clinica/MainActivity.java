package apdroid.clinica;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import apdroid.clinica.adapter.DrawerItem;
import apdroid.clinica.adapter.DrawerListAdapter;
import apdroid.clinica.adapter.recyclerview.RVDatosCitasAdapter;
import apdroid.clinica.adapter.spinner.SPEspecialidadAdapter;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Especialidad;
import apdroid.clinica.service.ClinicaService;
import apdroid.clinica.util.Constantes;

/**
 * Clase para la pantalla principal despues del Login
 */
public class MainActivity extends AppCompatActivity implements RVDatosCitasAdapter.RVDatosCitasAdapterListener  {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar; //Declarando toolbar

    private String[] tagTitles;
    private CharSequence activityTitle;
    private CharSequence itemTitle;


    private Spinner spEspecialidad;
    private SPEspecialidadAdapter spEspecialidadAdapter;

    private RecyclerView lstDatosCitas;
    private RVDatosCitasAdapter rvDatosCitasAdapter;

    private ClinicaService clinicaService;

    public MainActivity() {
        Log.d("MainActivity", "Constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ONCreate", "oncreate");
        setContentView(R.layout.activity_main);
        DB_Helper manager= new DB_Helper(this);
        try {
            manager.createDataBase();
            manager.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clinicaService = ClinicaService.getSingleton();

        configurarMenu(savedInstanceState);
        configurarControles();



    }

    private void configurarControles(){
        spEspecialidad = (Spinner)findViewById(R.id.spEspecialidad);

        ArrayList<Especialidad> listEspec = clinicaService.listarEspecialidades();
        if(spEspecialidadAdapter == null){

            ArrayList<Especialidad> lstSpinner = new ArrayList<>(listEspec);
            lstSpinner.add(0, new Especialidad(-1, "<Seleccione Especialidad>") );
            spEspecialidadAdapter = new SPEspecialidadAdapter(this, lstSpinner);
            spEspecialidad.setAdapter(spEspecialidadAdapter);
        }



        spEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtrarCitas((Especialidad) parent.getItemAtPosition(position), null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lstDatosCitas =(RecyclerView) findViewById(R.id.lstDatosCitas);
        lstDatosCitas.setHasFixedSize(true);
        lstDatosCitas.setLayoutManager(new LinearLayoutManager(this));
        rvDatosCitasAdapter = new RVDatosCitasAdapter(MainActivity.this);
        lstDatosCitas.setAdapter(rvDatosCitasAdapter);

    }

    private void filtrarCitas(Especialidad especialidad, String fecha){
        ArrayList<DatosCita> listaCitas = null;
        DatosCita datosCita = new DatosCita();
        datosCita.setIdEspecialidad( especialidad.getIdEspecialidad() );
        datosCita.setFecha(fecha);
        listaCitas = clinicaService.filtrarCitas(datosCita);
        rvDatosCitasAdapter.setNewSource(listaCitas);
    }

    private void configurarMenu(Bundle savedInstanceState){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        tagTitles = getResources().getStringArray(R.array.Tags);
        itemTitle = activityTitle = getTitle();

        //setear toolbar
        toolbar.dismissPopupMenus();
        setSupportActionBar(toolbar);


        // Setear una sombra sobre el contenido principal cuando el drawer se despliegue
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerLayout.setDrawerListener(drawerToggle); //Seteamos la escucha

        //Creamos lista de items de Menu
        ArrayList<DrawerItem> items = new ArrayList<>();
        items.add(new DrawerItem(tagTitles[0], R.drawable.ic_nuevacita));
        items.add(new DrawerItem(tagTitles[1], R.drawable.ic_micuenta));
        items.add(new DrawerItem(tagTitles[2], R.drawable.ic_locales));
        items.add(new DrawerItem(tagTitles[3], R.drawable.ic_locales));

        //Creamos adaptador y seteamos al Drawerlist
        drawerList.setAdapter(new DrawerListAdapter(this, items));
        //Seteamos escucha
        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        // Habilitar el icono de la app por si hay algún estilo que lo deshabilitó
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Crear ActionBarDrawerToggle para la apertura y cierre
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(itemTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(activityTitle);
            }
        };
        //Seteamos la escucha al drawer layout
        drawerLayout.setDrawerListener(drawerToggle);


//        if (savedInstanceState == null) {
//            selectItem(0);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            // Toma los eventos de selección del toggle aquí
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sincronizar el estado del drawer
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Cambiar las configuraciones del drawer si hubo modificaciones
        drawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Seleccion de menu
     * @param position
     */
    private void selectItem(int position){
        cargarPantalla(position);

        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }

    /**
     * Muestra la pantalla segun el menu seleccionado
     * @param menuSelected
     */
    private void cargarPantalla(int menuSelected){

        switch (menuSelected){
            case 0: //Nueva Cita
                cargarNuevaCita();
                break;
            case 1:
                cargarMiCuenta();
                break;
            case 2:
                cargarNuestrosLocales();
                break;
            case 3:
                salir();
                break;

        }
    }

    private void salir() {
        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().clear().commit();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * Carga la pantalla de nueva cita
     */

    //private int Req_Code=22;
    public static String ARG_USUARIO =  "npersona";

    private void cargarNuevaCita(){
        //int usuario = this.getIntent().getIntExtra("user",0);
        String nusuario=this.getIntent().getStringExtra( ARG_USUARIO );


        Intent intent = new Intent(MainActivity.this, NuevaCitaActivity.class);
        //intent.putExtra("user",usuario);
        intent.putExtra(ARG_USUARIO ,nusuario);

        startActivityForResult(intent, Constantes.REQUEST_NUEVACITA); // declarar ... private int Req_Code=22;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Constantes.REQUEST_NUEVACITA ){
            if( resultCode == RESULT_OK ){
                DatosCita dataOAR=data.getParcelableExtra("data");
                Toast.makeText(this,dataOAR.getEspecialidad().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getFecha().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getDoctor().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getHora().toString(),Toast.LENGTH_LONG).show();

            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
            }

        }else {

        }


    }



    /**
     * Carga la pantalla de mi cuenta
     */
    private void cargarMiCuenta(){
        int usuario = this.getIntent().getIntExtra("user",0);
        Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
        intent.putExtra("user",usuario);
        startActivity(intent);

    }

    /**
     * Carga la pantalla de nuestros locales
     */
    private void cargarNuestrosLocales(){

    }

    public final static String ARG_DATOS_CITA = "datosCita", ARG_POSITION = "position";

    @Override
    public void onSelectedItem(DatosCita datosCita, int position) {
        Intent intent = new Intent(MainActivity.this, DetalleCitaActivity.class);
        intent.putExtra(ARG_DATOS_CITA, datosCita);
        intent.putExtra(ARG_POSITION, position);
        startActivityForResult(intent, Constantes.REQUEST_DETALLECITA);

    }
}
