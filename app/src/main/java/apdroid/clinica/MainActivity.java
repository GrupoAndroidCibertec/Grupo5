package apdroid.clinica;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import apdroid.clinica.adapter.DrawerItem;
import apdroid.clinica.adapter.DrawerListAdapter;
import apdroid.clinica.adapter.recyclerview.RVDatosCitasAdapter;
import apdroid.clinica.adapter.spinner.SPEspecialidadAdapter;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Especialidad;
import apdroid.clinica.service.ClinicaService;

/**
 * Clase para la pantalla principal despues del Login
 */
public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarMenu(savedInstanceState);
        configurarControles();
        DB_Manager manager= new DB_Manager(this);

        clinicaService = new ClinicaService();




    }

    private void configurarControles(){
        spEspecialidad = (Spinner)findViewById(R.id.spEspecialidad);

        ArrayList<Especialidad> list = new ArrayList<>();
        list.add(new Especialidad(1, "Medicina General"));
        list.add(new Especialidad(2, "Pediatria"));
        list.add(new Especialidad(3, "Odontologia"));
        spEspecialidadAdapter = new SPEspecialidadAdapter(this, list);
        spEspecialidad.setAdapter(spEspecialidadAdapter);
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
        rvDatosCitasAdapter = new RVDatosCitasAdapter(new ArrayList<DatosCita>());
        lstDatosCitas.setAdapter(rvDatosCitasAdapter);

    }

    private void filtrarCitas(Especialidad especialidad, String fecha){
        ArrayList<DatosCita> listaCitas = null;
        DatosCita datosCita = new DatosCita();
        datosCita.setEspecialidad(especialidad!=null ? especialidad.getNombre(): null);
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

    private int Req_Code=22;

    private void cargarNuevaCita(){
        //int usuario = this.getIntent().getIntExtra("user",0);
        String nusuario=this.getIntent().getStringExtra("npersona");


        Intent intent = new Intent(MainActivity.this, NuevaCitaActivity.class);
        //intent.putExtra("user",usuario);
        intent.putExtra("npersona",nusuario);

        startActivityForResult(intent, Req_Code); // declarar ... private int Req_Code=22;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Req_Code){
            if(resultCode==RESULT_OK){
                DatosCita dataOAR=data.getParcelableExtra("data");
                Toast.makeText(this,dataOAR.getEspecialidad().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getFecha().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getDoctor().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,dataOAR.getHorario().toString(),Toast.LENGTH_LONG).show();

            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
            }

        }


    }



    /**
     * Carga la pantalla de mi cuenta
     */
    private void cargarMiCuenta(){

    }

    /**
     * Carga la pantalla de nuestros locales
     */
    private void cargarNuestrosLocales(){

    }








}
