package apdroid.clinica.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import apdroid.clinica.DB_Helper;
import apdroid.clinica.entidades.DatosCita;

/**
 * Created by ANTONIO on 12/09/2015.
 */
public class CitasDao {


    private final String sql_listAll = "select id_cita, id_doctor, id_paciente, (select nombre_espec from especialidad where id_especialidad = d.id_especialidad) nom_especialidad, d.nombre || ' ' || d.apellido nom_doctor, fecha, hora from cita c inner join doctor d on c.id_doctor=d.id_doc order by date(fecha) desc";


    public ArrayList<DatosCita> listCitas() {
        ArrayList<DatosCita> lstPersona = new ArrayList<>();
        Cursor cursor = null;
        DatosCita cita = null;

        try {
            cursor = DB_Helper.getMyDataBase().rawQuery(sql_listAll, null);

            if (cursor.moveToFirst()) {
                do {
                    cita = new DatosCita();
                    cita.setIdCita( cursor.isNull(cursor.getColumnIndex("id_cita")) ? 0 : cursor.getInt(cursor.getColumnIndex("id_cita")));
                    cita.setIdDoctor(cursor.isNull(cursor.getColumnIndex("id_doctor")) ? 0 : cursor.getInt(cursor.getColumnIndex("id_doctor")));
                    cita.setIdPaciente(cursor.isNull(cursor.getColumnIndex("id_paciente")) ? 0 : cursor.getInt(cursor.getColumnIndex("id_paciente")));

                    cita.setEspecialidad(cursor.isNull(cursor.getColumnIndex("nom_especialidad")) ? "" : cursor.getString(cursor.getColumnIndex("nom_especialidad")));
                    cita.setDoctor(cursor.isNull(cursor.getColumnIndex("nom_doctor")) ? "" : cursor.getString(cursor.getColumnIndex("nom_doctor")));
                    cita.setFecha(cursor.isNull(cursor.getColumnIndex("fecha")) ? "" : cursor.getString(cursor.getColumnIndex("fecha")));
                    cita.setHora(cursor.isNull(cursor.getColumnIndex("hora")) ? "" : cursor.getString(cursor.getColumnIndex("hora")));

                    lstPersona.add(cita);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstPersona;
    }

    public void addPersona(DatosCita persona) {
        try {
            ContentValues cv = new ContentValues();
//            cv.put("Nombre", persona.getNombre());
//            cv.put("Apellido", persona.getApellido());
//            cv.put("Edad", persona.getEdad());
//            cv.put("DNI", persona.getDNI());
            DB_Helper.getMyDataBase().insert("Persona", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePersona(DatosCita persona) {
        try {
            ContentValues cv = new ContentValues();
//            cv.put("Nombre", persona.getNombre());
//            cv.put("Apellido", persona.getApellido());
//            cv.put("Edad", persona.getEdad());
//            cv.put("DNI", persona.getDNI());
            DB_Helper.getMyDataBase().update("Persona", cv, "IdPersona = ?", new String[]{String.valueOf(persona.getIdCita())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deletePersona(DatosCita persona) {
        try {
            DB_Helper.getMyDataBase().delete("Persona", "IdPersona = ?", new String[]{String.valueOf(persona.getIdCita())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
