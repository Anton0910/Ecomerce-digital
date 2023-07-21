package devandroid.antonio.e_comerce.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configClicks();
    }

    private void configClicks() {
        binding.btnrecuperarSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperarsenhaActivity.class));
        });
        binding.textView2.setOnClickListener(view -> {
            startActivity(new Intent(this, CadastroActivity.class));
        });
    }

    //VEROFOCA SE O USUARIO FEZ LOGIN
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }




}
