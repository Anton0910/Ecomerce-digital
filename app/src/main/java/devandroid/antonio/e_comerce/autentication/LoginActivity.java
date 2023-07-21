package devandroid.antonio.e_comerce.autentication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.databinding.ActivityMainBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    String email;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configClicks();
        email = binding.editEmail.getText().toString();
        password = binding.editSenha.getText().toString();
    }

    private void configClicks() {
        binding.btnrecuperarSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperarsenhaActivity.class));
        });
        binding.textView2.setOnClickListener(view -> {
            startActivity(new Intent(this, CadastroActivity.class));
        });
    }








}
