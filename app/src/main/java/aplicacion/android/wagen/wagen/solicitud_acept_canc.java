package aplicacion.android.wagen.wagen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class solicitud_acept_canc extends AppCompatActivity {
    EditText origen;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_acept_canc);

        origen= findViewById(R.id.txtLugOrig);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("DupqY66cdLcbmFsw9ayMxcTkVNv2");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                //displayData(usuario);
                origen.setText(usuario.Apellidos);
                System.out.println("Apellidos:"+usuario.Apellidos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(postListener);
    }

    public void displayData(Usuario usuario){

    }
}
