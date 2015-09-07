package apdroid.clinica.entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AngeloPaulo on 03/septiembre/2015.
 */
public class DatosCita implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DatosCita> CREATOR = new Parcelable.Creator<DatosCita>() {
        @Override
        public DatosCita createFromParcel(Parcel in) {
            return new DatosCita(in);
        }

        @Override
        public DatosCita[] newArray(int size) {
            return new DatosCita[size];
        }
    };
    private String nombre;
    private String especialidad;
    private String doctor;
    private String horario;
    private String fecha;

    public DatosCita(String nombre, String especialidad, String doctor, String horario, String fecha) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.horario = horario;
        this.fecha = fecha;
    }

    protected DatosCita(Parcel in) {
        nombre = in.readString();
        especialidad = in.readString();
        doctor = in.readString();
        horario = in.readString();
        fecha = in.readString();
    }

    //<editor-fold desc="Getters and Setters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    //</editor-fold>

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(especialidad);
        dest.writeString(doctor);
        dest.writeString(horario);
        dest.writeString(fecha);
    }
}