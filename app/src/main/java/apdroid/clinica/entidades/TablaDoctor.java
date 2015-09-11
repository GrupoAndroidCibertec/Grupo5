package apdroid.clinica.entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AngeloPaulo on 10/septiembre/2015.
 */
public class TablaDoctor implements Parcelable {

    private int idDoc;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String horario;

    public TablaDoctor(int idDoc) {
        this.idDoc = idDoc;
    }

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    protected TablaDoctor(Parcel in) {
        idDoc = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        especialidad = in.readString();
        horario = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idDoc);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(especialidad);
        dest.writeString(horario);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TablaDoctor> CREATOR = new Parcelable.Creator<TablaDoctor>() {
        @Override
        public TablaDoctor createFromParcel(Parcel in) {
            return new TablaDoctor(in);
        }

        @Override
        public TablaDoctor[] newArray(int size) {
            return new TablaDoctor[size];
        }
    };
}