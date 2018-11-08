package aplicacion.android.wagen.wagen;

public class Solcitudes {
    String Titulo,LugarO,LugarD,TipoRemolque,Tarifa,TipoTarifa,Descripcion,idProcutor,idSolicitud;

    public Solcitudes(String titulo, String lugarO, String lugarD, String tipoRemolque, String tarifa, String tipoTarifa, String descripcion, String idProcutor, String idSolicitud) {
        Titulo = titulo;
        LugarO = lugarO;
        LugarD = lugarD;
        TipoRemolque = tipoRemolque;
        Tarifa = tarifa;
        TipoTarifa = tipoTarifa;
        Descripcion = descripcion;
        this.idProcutor = idProcutor;
        this.idSolicitud = idSolicitud;
    }
}
