package aplicacion.android.wagen.wagen;

public class Usuario {
    public String Nombre,Apellidos,Correo,tipoU,Contraseña,ID;

    public Usuario(String nombre, String apellidos, String correo, String tipoU,String contraseña) {
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        Contraseña = contraseña;
        //ID=id;
        this.tipoU = tipoU;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getTipoU() {
        return tipoU;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public String getID() {
        return ID;
    }
}
