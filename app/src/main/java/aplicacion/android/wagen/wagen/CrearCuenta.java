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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearCuenta extends AppCompatActivity {
    private FirebaseAuth auth;
    Spinner Spin;
    Button boton;
    EditText Nombre,Apellidos,CorreoElectronico,TipoUsuario,Contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        Spin = findViewById(R.id.Spin);
        Nombre = findViewById(R.id.txtNombre);
        Apellidos = findViewById(R.id.txtApellidos);
        CorreoElectronico = findViewById(R.id.txtCorreo);
        Contraseña = findViewById(R.id.txtContraseña);
        boton = findViewById(R.id.btnCrearCuenta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SpinerTipoUsuario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarUsuario();
                finish();
            }
        });
    }


    private void RegistrarUsuario() {
        final String nombre = Nombre.getText().toString().trim();
        final String apellidos = Apellidos.getText().toString().trim();
        final String correo = CorreoElectronico.getText().toString().trim();
        final String tipousuario = Spin.getSelectedItem().toString().trim();
        final String contraseña = Contraseña.getText().toString().trim();

        if (nombre.isEmpty()) {
            Nombre.setError("Nombre Requerido");
            Nombre.requestFocus();
            return;
        }

        if (apellidos.isEmpty()) {
            Apellidos.setError("Apellido Requerido");
            Apellidos.requestFocus();
            return;
        }

        if (correo.isEmpty()) {
            CorreoElectronico.setError("Correo Electronico Requerido");
            CorreoElectronico.requestFocus();
            return;
        }

        if (contraseña.isEmpty()) {
            Contraseña.setError("Obligatoria Requerido");
            Contraseña.requestFocus();
            return;
        }

        if (tipousuario.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tipo de Usuario Requerido", Toast.LENGTH_SHORT).show();
            Spin.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario(nombre, apellidos, correo, tipousuario, contraseña);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
