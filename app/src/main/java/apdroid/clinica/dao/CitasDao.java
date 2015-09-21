package apdroid.clinica.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import apdroid.clinica.entidades.DatosCita;

/**
 * Created by ANTONIO on 12/09/2015.
 */
public class CitasDao {

    private static CitasDao singleton = null;

    private final String sql_listAll = "select id_cita, id_doctor, id_paciente, (select nombre_espec from especialidad where id_especialidad = d.id_especialidad) nom_especialidad, d.nombre || ' ' || d.apellido nom_doctor, fecha, hora , estado , detalleConsulta , nombrelocal from cita c inner join doctor d on c.id_doctor=d.id_doc inner join local l on l.idlocal = d.idlocal ";

    private final String orderBy = "order by date(fecha) desc";

    private final String updateAnulacion = "update cita   set   estado = 'ANULADA' WHERE  ID_CITA = ?"  ;


    private CitasDao(){

    }

    public static CitasDao getSingleton(){
        if(singleton == null){
            singleton = new CitasDao();
        }
        return singleton;
    }

    private ArrayList<DatosCita> ejecutarQuery(String query, String[] args){
        Cursor cursor = null;
        DatosCita cita = null;
        ArrayList<DatosCita> lstPersona = new ArrayList<>();
        try {
            cursor = DB_Helper.getMyDataBase().rawQuery(query, args);

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
                    cita.setEstado(cursor.isNull(cursor.getColumnIndex("estado")) ? "" : cursor.getString(cursor.getColumnIndex("estado")));
                    cita.setDetalleConsulta(cursor.isNull(cursor.getColumnIndex("detalleConsulta")) ? "" : cursor.getString(cursor.getColumnIndex("detalleConsulta")));
                    cita.setLocal(cursor.isNull(cursor.getColumnIndex("nombrelocal")) ? "" : cursor.getString(cursor.getColumnIndex("nombrelocal")));


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

    public ArrayList<DatosCita> buscarCitas(DatosCita datosCita) {
        ArrayList<DatosCita> lstPersona = new ArrayList<>();
        String finalQuery = null;
        StringBuilder whereQuery = new StringBuilder();
        List<String> params = new ArrayList<>();

        if(datosCita != null){

            whereQuery.append("where 1 = 1 ");
            if( datosCita.getIdEspecialidad() != null && datosCita.getIdEspecialidad() != -1){
                whereQuery.append("and d.id_especialidad = ? ");
                params.add(String.valueOf(datosCita.getIdEspecialidad()));
            }

            if(datosCita.getFecha()!=null && !"".equals(datosCita.getFecha())){
                Date date = null;
                String fecha = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = sdf.parse(datosCita.getFecha());
                    fecha = sdfDB.format(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if ( fecha!=null ){
                    whereQuery.append("and c.fecha = ? ");
                    params.add(fecha);
                }

            }
        }

        finalQuery = sql_listAll + whereQuery + orderBy;
        Log.d("", finalQuery);
        Log.d("", params.toString());
        lstPersona = ejecutarQuery(finalQuery, params.size() > 0 ? params.toArray(new String[]{}) : null);


        return lstPersona;
    }

    public boolean actualizarCita(DatosCita datosCita){
        try {
            ContentValues cv = new ContentValues();
            cv.put("estado", "ANULADA");

            DB_Helper.getMyDataBase().update("cita", cv, "id_cita = ?", new String[]{String.valueOf(datosCita.getIdCita())});
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true ;
    }


}
