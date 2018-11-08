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

    public String getTitulo() {
        return Titulo;
    }

    public String getLugarO() {
        return LugarO;
    }

    public String getLugarD() {
        return LugarD;
    }

    public String getTipoRemolque() {
        return TipoRemolque;
    }

    public String getTarifa() {
        return Tarifa;
    }

    public String getTipoTarifa() {
        return TipoTarifa;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getIdProcutor() {
        return idProcutor;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }
}
