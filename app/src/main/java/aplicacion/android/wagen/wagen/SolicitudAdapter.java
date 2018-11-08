package aplicacion.android.wagen.wagen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aplicacion.android.wagen.wagen.R;
import aplicacion.android.wagen.wagen.Solcitudes;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>{
    private Context mCtx;
    private List<Solcitudes> SolicitudList;

    public SolicitudAdapter(Context mCtx, List<Solcitudes> solicitudlist) {
        this.mCtx = mCtx;
        this.SolicitudList = solicitudlist;
    }

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview,viewGroup, false);
        return new SolicitudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudViewHolder solicitudViewHolder, int i) {
        Solcitudes solcitudes = SolicitudList.get(i);
        solicitudViewHolder.textTiulo.setText(solcitudes.getTitulo());
        solicitudViewHolder.textDescripcion.setText(solcitudes.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return SolicitudList.size();
    }

    class SolicitudViewHolder extends RecyclerView.ViewHolder {
        private TextView textTiulo, textDescripcion;

        public SolicitudViewHolder(@NonNull View itemView) {
            super(itemView);
            textTiulo = itemView.findViewById(R.id.text_Titulo);
            textDescripcion = itemView.findViewById(R.id.text_Descripcion);

        }
    }
}
