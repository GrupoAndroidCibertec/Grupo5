package apdroid.clinica.dao;

import android.database.Cursor;

import java.util.ArrayList;

import apdroid.clinica.entidades.Doctor;
import apdroid.clinica.entidades.Especialidad;

/**
 * Created by AngeloPaulo on 24/septiembre/2015.
 */
public class DoctorDao {

    private static DoctorDao singleton;



    private DoctorDao(){
    }

    public static DoctorDao getSingleton(){
        if(singleton == null){
            singleton = new DoctorDao();
        }

        return singleton;
    }

    public ArrayList<Doctor> listarDoctores() {
        ArrayList<Doctor> lstDoctor = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DB_Helper.getMyDataBase().query("Doctor", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Doctor doctor = new Doctor();

                    doctor.setIddoc(cursor.isNull(cursor.getColumnIndex("id_doc")) ? -1 : cursor.getInt(cursor.getColumnIndex("id_doc")));
                    doctor.setNombre(cursor.isNull(cursor.getColumnIndex("nombre")) ? "" : cursor.getString(cursor.getColumnIndex("nombre")));
                    doctor.setNombre(cursor.isNull(cursor.getColumnIndex("apellido")) ? "" : cursor.getString(cursor.getColumnIndex("apellido")));
                    doctor.setIdespec(cursor.isNull(cursor.getColumnIndex("id_especialidad")) ? -1 : cursor.getInt(cursor.getColumnIndex("id_especialidad")));
                    doctor.setNombre(cursor.isNull(cursor.getColumnIndex("horario")) ? "" : cursor.getString(cursor.getColumnIndex("horario")));
                    doctor.setIdespec(cursor.isNull(cursor.getColumnIndex("idlocal")) ? -1 : cursor.getInt(cursor.getColumnIndex("idlocal")));

                    lstDoctor.add(doctor);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstDoctor;
    }

}
