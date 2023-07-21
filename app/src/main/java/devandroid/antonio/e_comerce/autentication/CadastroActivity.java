package devandroid.antonio.e_comerce.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityCadastroBinding;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configClick();
    }


    public void configClick(){
        binding.btnVoltar.ibVoltar.setOnClickListener(view -> {
            finish();
        });

        binding.textView2.setOnClickListener(view -> finish());
    }
}