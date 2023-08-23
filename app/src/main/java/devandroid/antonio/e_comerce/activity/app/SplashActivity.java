package devandroid.antonio.e_comerce.activity.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import devandroid.antonio.e_comerce.activity.loja.MainActivityEmpresa;
import devandroid.antonio.e_comerce.activity.usuario.MainActivityUsuario;
import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(getMainLooper()).postDelayed(this::verificaAcesso, 3000);

    }

    private void verificaAcesso(){

            startActivity(new Intent(this, MainActivityEmpresa.class));

    }

    private void recuperaAcesso() {
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(FirebaseHelper.getIdFirebase());
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){ // Usuário
                    startActivity(new Intent(getBaseContext(), MainActivityUsuario.class));
                }else { // Loja
                    startActivity(new Intent(getBaseContext(), MainActivityEmpresa.class));
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}