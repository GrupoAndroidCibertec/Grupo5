package apdroid.clinica.service;

import android.util.Log;

import java.util.ArrayList;

import apdroid.clinica.dao.CitasDao;
import apdroid.clinica.dao.EspecialidadDao;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Especialidad;

/**
 * Created by ANTONIO on 09/09/2015.
 */
public class ClinicaService {

    private static ClinicaService singleton;

    private EspecialidadDao especialidadDao;
    private CitasDao citasDao;

    private ArrayList<Especialidad> lstEspecialidadCache;

    private ClinicaService(){
        especialidadDao = EspecialidadDao.getSingleton();
        citasDao = CitasDao.getSingleton();

    }

    public static ClinicaService getSingleton(){
        if(singleton == null){
            singleton = new ClinicaService();
        }

        return singleton;
    }


    public ArrayList<Especialidad> listarEspecialidades(){
        if( lstEspecialidadCache == null){
            lstEspecialidadCache = especialidadDao.listarEspecialidades();
        }
        return lstEspecialidadCache;
    }


    public ArrayList<DatosCita> filtrarCitas(DatosCita datosCita){
        ArrayList<DatosCita> lstCitas = null;
        Log.d("FiltrarCitas", String.valueOf(datosCita.getIdEspecialidad()));
        lstCitas = citasDao.buscarCitas(datosCita);


        return lstCitas;
    }

    public boolean actualizarCita(DatosCita datosCita){
        citasDao.actualizarCita(datosCita)  ;
        return true ;

    }
}
