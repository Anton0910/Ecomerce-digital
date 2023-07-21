package devandroid.antonio.e_comerce.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.databinding.ActivityRecuperarsenhaBinding;
import devandroid.antonio.e_comerce.databinding.IbVoltarBinding;

public class RecuperarsenhaActivity extends AppCompatActivity {
    private ActivityRecuperarsenhaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarsenha);
        binding = ActivityRecuperarsenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configClicks();


    }


    private void configClicks(){
        binding.btnVoltar.ibVoltar.setOnClickListener(view -> {
            finish();
        });

    }
}