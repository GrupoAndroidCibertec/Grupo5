package apdroid.clinica.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AngeloPaulo on 08/septiembre/2015.
 */
public class DB_Manager {
    public static final String TABLE_NAME="Doctores";

    public static final String CN_ID="Id_Doc"; // Colum Name ID
    public static final String CN_NOMBRE="Nombre";
    public static final String CN_APELLIDO="Apellido";
    public static final String CN_ESPECIALIDAD="Especialidad";
    public static final String CN_HORARIO="Horario";

    // Crear la Tabla de Doctores
//    CREATE TABLE "Doctroes" ("Id_Doc" INTEGER PRIMARY KEY
//    AUTOINCREMENT  NOT NULL  UNIQUE , "Nombre"
//    TEXT, "Apellido" TEXT, "Especialidad" TEXT, "Horario" TEXT)

    public static final String CREATE_TABLE=" create table "+TABLE_NAME+ " ("
            +CN_ID + " integer primary key autoincrement,"
            +CN_NOMBRE+" text not null,"
            +CN_APELLIDO+" text not null,"
            +CN_ESPECIALIDAD + " text not null,"
            +CN_HORARIO + " text not null);";
//        public static final String CREATE_TABLE=" create table "+TABLE_NAME+ " ("
//            +CN_ID + " integer primary key autoincrement,"
//            +CN_NOMBRE+" text no null);";


    private DB_Helper helper;
    private SQLiteDatabase db;


    public DB_Manager(Context context ) {

        helper= new DB_Helper(context);
        db=helper.getReadableDatabase();
    }
}
