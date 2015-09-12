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

    public ArrayList<DatosCita> listPersona() {
        ArrayList<DatosCita> lstPersona = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DB_Helper.getMyDataBase().query("Persona", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    DatosCita persona = new DatosCita();
//                    persona.setNombre(cursor.isNull(cursor.getColumnIndex("Nombre")) ? "" : cursor.getString(cursor.getColumnIndex("Nombre")));
//                    persona.setApellido(cursor.isNull(cursor.getColumnIndex("Apellido")) ? "" : cursor.getString(cursor.getColumnIndex("Apellido")));
//                    persona.setEdad(cursor.isNull(cursor.getColumnIndex("Edad")) ? 0 : cursor.getInt(cursor.getColumnIndex("Edad")));
//                    persona.setDNI(cursor.isNull(cursor.getColumnIndex("DNI")) ? "" : cursor.getString(cursor.getColumnIndex("DNI")));
                    lstPersona.add(persona);
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
