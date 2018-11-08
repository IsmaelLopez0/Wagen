package aplicacion.android.wagen.wagen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class solicitud_creacion extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button createRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_creacion);

        FirebaseApp.initializeApp(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        createRequest= findViewById(R.id.btnSolicitar);

        createRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

    }

    public void uploadData(){
        mDatabase.child("request").child("title").setValue("Título chingón");
    }


}
