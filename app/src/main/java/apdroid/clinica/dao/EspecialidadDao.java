package apdroid.clinica.dao;

import android.database.Cursor;

import java.util.ArrayList;

import apdroid.clinica.DB_Helper;
import apdroid.clinica.entidades.Especialidad;

/**
 * Created by ANTONIO on 12/09/2015.
 */
public class EspecialidadDao {

    public ArrayList<Especialidad> listarEspecialidades() {
        ArrayList<Especialidad> lstEspecialidad = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DB_Helper.getMyDataBase().query("Especialidad", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Especialidad especialidad = new Especialidad();

                    especialidad.setIdEspecialidad( cursor.isNull(cursor.getColumnIndex("id_especialidad")) ? -1 : cursor.getInt(cursor.getColumnIndex("id_especialidad")) );
                    especialidad.setNombre(cursor.isNull(cursor.getColumnIndex("nombre_espec")) ? "" : cursor.getString(cursor.getColumnIndex("nombre_espec")));

                    lstEspecialidad.add(especialidad);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstEspecialidad;
    }

}