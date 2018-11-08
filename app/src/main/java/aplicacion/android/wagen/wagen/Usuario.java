package aplicacion.android.wagen.wagen;

public class Usuario {
    public String Apellidos, Contraseña, Correo, ID, Nombre, tipoU;

    public Usuario(String nombre, String apellidos, String correo, String tipou, String contraseña, String id) {
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        tipoU = tipou;
        Contraseña = contraseña;
        ID = id;
    }

    public Usuario(){

    }
}
