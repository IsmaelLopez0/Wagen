package aplicacion.android.wagen.wagen;

public class Usuario {
    public String Apellidos, Contraseña, Correo, ID, Nombre, tipoU;

    public Usuario(String nombre, String apellidos, String correo, String tipou, String contraseña) {
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        tipoU = tipou;
        Contraseña = contraseña;
    }

    public Usuario(){

    }
}
