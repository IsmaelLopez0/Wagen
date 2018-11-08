package aplicacion.android.wagen.wagen;

public class Usuario {
    public String Nombre,Apellidos,Correo,tipoU,Contraseña;

    public Usuario(String nombre, String apellidos, String correo, String tipoU, String contraseña) {
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        this.tipoU = tipoU;
        Contraseña = contraseña;
    }
}
