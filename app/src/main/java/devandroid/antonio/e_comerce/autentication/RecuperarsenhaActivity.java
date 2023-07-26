package devandroid.antonio.e_comerce.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.databinding.ActivityRecuperarsenhaBinding;
import devandroid.antonio.e_comerce.databinding.IbVoltarBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class RecuperarsenhaActivity extends AppCompatActivity {
    private ActivityRecuperarsenhaBinding binding;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarsenha);
        binding = ActivityRecuperarsenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configClicks();
        validaDados();

    }


    private void configClicks(){
        binding.btnVoltar.ibVoltar.setOnClickListener(view -> {
            finish();
        });

    }

    public void validaDados(){
        binding.btnLogin.setOnClickListener(view -> {
            email = binding.editEmail.getText().toString().trim();

            if (!email.isEmpty()){
                    recuperaConta(email);
            }else {
                binding.editEmail.requestFocus();
                binding.editEmail.setError("Informe seu email!");
            }


        });

    }


    private void recuperaConta( String email){
        FirebaseHelper.getAuth().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){

                        Toast.makeText(this, "Email de recuperação enviado.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(this,
                                FirebaseHelper.validaErros(task.getException().getMessage()),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}