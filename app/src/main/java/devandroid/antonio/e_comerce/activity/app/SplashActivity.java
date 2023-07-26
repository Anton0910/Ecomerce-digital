package devandroid.antonio.e_comerce.activity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import devandroid.antonio.e_comerce.activity.usuario.MainActivityUsuario;
import devandroid.antonio.e_comerce.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(getMainLooper()).postDelayed(()->{
            finish();
            startActivity(new Intent(this, MainActivityUsuario.class));
        },3000);

    }



}