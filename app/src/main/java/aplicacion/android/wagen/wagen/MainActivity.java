package aplicacion.android.wagen.wagen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BtnCrearC;
    Button iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnCrearC = findViewById(R.id.btnCrear);

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
                abrir();
            }
        });

    }

    private void abrirCrear(){
        Intent intent = new Intent(this, CrearCuenta.class);
        startActivity(intent);
    }

    private void abrir(){
        Intent intent = new Intent(getApplicationContext(), solicitud_acept_canc.class);
        startActivity(intent);
    }
}
