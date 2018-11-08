package aplicacion.android.wagen.wagen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class solicitud_creacion extends AppCompatActivity {
    private EditText Titulo,LugarO,LugarD,Tarifa,Descripcion;
    private String idProcutor,idSolicitud;
    private Spinner TipoRemolque,TipoTarifa;
    private DatabaseReference mDatabase;
    private Button createRequest;
    private FirebaseAuth auth;
    DatabaseReference databaseSolicitud;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_creacion);
        Titulo = findViewById(R.id.txtTitulo);
        LugarO = findViewById(R.id.txtLugOrig);
        LugarD = findViewById(R.id.txtLugDest);
        Tarifa = findViewById(R.id.txtTarifa);
        TipoRemolque = findViewById(R.id.listTipoRemolque);
        TipoTarifa = findViewById(R.id.listTipoTarifa);
        Descripcion = findViewById(R.id.txtDescripcion);
        auth = FirebaseAuth.getInstance();
        databaseSolicitud = FirebaseDatabase.getInstance().getReference("Solicitudes");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SpinerTipoRemolque, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TipoRemolque.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.SpinerTipoTarifa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TipoTarifa.setAdapter(adapter);
        FirebaseApp.initializeApp(this);

        createRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarSolicitud();
                Intent intent = new Intent(getApplicationContext(),historial.class);
                startActivity(intent);
                finish();
            }
        });

    }



    private void RegistrarSolicitud() {
        final String titulo = Titulo.getText().toString().trim();
        final String lugarO = LugarO.getText().toString().trim();
        final String lugarD = LugarD.getText().toString().trim();
        final String tarifa = Tarifa.getText().toString().trim();
        final String tiporemolque = TipoRemolque.getSelectedItem().toString().trim();
        final String tipotarifa = TipoTarifa.getSelectedItem().toString().trim();
        final String descripcion = Descripcion.getText().toString().trim();

        if (titulo.isEmpty()) {
            Titulo.setError("Titulo Requerido");
            Titulo.requestFocus();
            return;
        }

        if (lugarO.isEmpty()) {
            LugarO.setError("Lugar de Origen Requerido");
            LugarO.requestFocus();
            return;
        }

        if (lugarD.isEmpty()) {
            LugarD.setError("Lugar de Destino Requerido");
            LugarD.requestFocus();
            return;
        }

        if (tarifa.isEmpty()) {
            Tarifa.setError("Lugar de Destino Requerido");
            Tarifa.requestFocus();
            return;
        }

        if (descripcion.isEmpty()) {
            Descripcion.setError("Lugar de Destino Requerido");
            Descripcion.requestFocus();
            return;
        }

        if (tiporemolque=="Selecciona Tipo") {
            TipoRemolque.requestFocus();
            return;
        }

        if (tipotarifa=="Selecciona Tipo") {
            TipoTarifa.requestFocus();
            return;
        }




         idProcutor=FirebaseAuth.getInstance().getCurrentUser().getUid();
        idSolicitud=databaseSolicitud.push().getKey();
        Solcitudes solicitud = new Solcitudes(titulo,lugarO,lugarD,tiporemolque,tarifa,tipotarifa,descripcion,"null","null");
        databaseSolicitud.child(idSolicitud).setValue(solicitud);
        Toast.makeText(getApplicationContext(),"Solicitud Realizada Corrrectamente",Toast.LENGTH_SHORT).show();
    }

}
