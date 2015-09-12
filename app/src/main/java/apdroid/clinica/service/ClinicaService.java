package apdroid.clinica.service;

import android.util.Log;

import java.util.ArrayList;

import apdroid.clinica.dao.EspecialidadDao;
import apdroid.clinica.entidades.DatosCita;
import apdroid.clinica.entidades.Especialidad;

/**
 * Created by ANTONIO on 09/09/2015.
 */
public class ClinicaService {

    private static ClinicaService singleton;

    private EspecialidadDao especialidadDao;

    private ArrayList<Especialidad> lstEspecialidadCache;

    private ClinicaService(){
        especialidadDao = new EspecialidadDao();

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


    public ArrayList<DatosCita> listarTodos(){
        ArrayList<DatosCita> lstCitas = new ArrayList<>();
//        lstCitas.add(new DatosCita("", "Odontologia", "Juan Perez", "", "23-09-2015"));
//        lstCitas.add(new DatosCita("", "Medicina General", "Mario Aguirre", "", "23-10-2015"));
//        lstCitas.add(new DatosCita("", "Odontologia", "Juan Perez", "", "23-08-2015"));
        return lstCitas;
    }


    public ArrayList<DatosCita> filtrarCitas(DatosCita datosCita){
        ArrayList<DatosCita> lstCitas = new ArrayList<>();
        lstCitas.add(new DatosCita("", "Odontologia", "Juan Perez", "", "23-09-2015"));
        lstCitas.add(new DatosCita("", "Medicina General", "Mario Aguirre", "", "23-10-2015"));
        lstCitas.add(new DatosCita("", "Odontologia", "Juan Perez", "", "23-08-2015"));
        Log.d("FiltrarCitas", datosCita.toString());
        return lstCitas;
    }
}
