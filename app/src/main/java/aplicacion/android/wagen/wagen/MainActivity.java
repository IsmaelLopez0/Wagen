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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button BtnCrearC;
    Button iniciarSesion;
    private FirebaseAuth auth;
    EditText CorreoElectronico,Contraseña;
    private String TipoUsuario;
    private String IDU;
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

    private void abrirCrear(){
        Intent intent = new Intent(this, CrearCuenta.class);
        startActivity(intent);
    }

    private void abrir(){

        /*Intent intent = new Intent(getApplicationContext(), solicitud_acept_canc.class);
        startActivity(intent);*/
    }

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

        auth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    IDU=auth.getCurrentUser().getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(IDU);
                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            TipoUsuario=usuario.tipoU;
                            if(TipoUsuario=="Productor"){
                                Toast.makeText(getApplicationContext(),"Tipo de usuario: "+TipoUsuario,Toast.LENGTH_SHORT).show();
                            }else if(TipoUsuario=="Transportista"){
                                Toast.makeText(getApplicationContext(),"Tipo de usuario: "+TipoUsuario,Toast.LENGTH_SHORT).show();
                            }else if(TipoUsuario=="Trailero"){
                                Toast.makeText(getApplicationContext(),"Tipo de usuario: "+TipoUsuario,Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    mDatabase.addValueEventListener(postListener);
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
