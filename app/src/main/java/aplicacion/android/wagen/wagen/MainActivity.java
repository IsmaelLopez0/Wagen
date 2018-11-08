package aplicacion.android.wagen.wagen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button BtnCrearC;
    Button iniciarSesion;
    private FirebaseAuth auth;
    EditText CorreoElectronico,Contraseña;
    private String TipoUsuario;
    private FirebaseUser IDU;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnCrearC = findViewById(R.id.btnCrear);
        CorreoElectronico = findViewById(R.id.Correo);
        Contraseña = findViewById(R.id.Contraseña);
        auth = FirebaseAuth.getInstance();
        BtnCrearC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCrear();
                finish();
            }
        });
        iniciarSesion= findViewById(R.id.btnInicia);
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        mDatabase=FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.addListenerForSingleValueEvent(postListener);
        if(auth.getCurrentUser() != null){
            //significa que el usuraio ya se logeo
        }else{
            IDU=auth.getCurrentUser();
        }
    }

    private void abrirCrear(){
        Intent intent = new Intent(this, CrearCuenta.class);
        startActivity(intent);
    }

    private void abrirProductor(){
        Intent intent = new Intent(getApplicationContext(),historial.class);
        startActivity(intent);
    }

    private void abrirTransportista(){
        Intent intent = new Intent(getApplicationContext(),historialTransportista.class);
        startActivity(intent);
    }


    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                TipoUsuario=usuario.tipoU;
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void login(){
        final String correo = CorreoElectronico.getText().toString().trim();
        final String contraseña = Contraseña.getText().toString().trim();


        if (correo.isEmpty()) {
            CorreoElectronico.setError("Correo Electronico Requerido");
            CorreoElectronico.requestFocus();
            return;
        }

        if (contraseña.isEmpty()) {
            Contraseña.setError("Contraseña Obligatoria");
            Contraseña.requestFocus();
            return;
        }
        IDU=auth.getCurrentUser();
        auth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        IDU=auth.getCurrentUser();
                        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(IDU.getUid());
                        query.addListenerForSingleValueEvent(postListener);
                        if(TipoUsuario!=null){
                            if(TipoUsuario.equals("Productor")){
                                abrirProductor();
                                finish();
                            }else if(TipoUsuario.equals("Transportista")){
                                abrirTransportista();
                                finish();
                            }else if(TipoUsuario.equals("Trailero")){
                                Toast.makeText(getApplicationContext(),"Interfaz Trailero",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            login();
                        }
                    /*Intent intent= new Intent(MainActivity.this,CrearCuenta.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    MainActivity.this.finish();*/
                }else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
