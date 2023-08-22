package devandroid.antonio.e_comerce.autentication;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import devandroid.antonio.e_comerce.R;

import devandroid.antonio.e_comerce.activity.loja.MainActivityEmpresa;
import devandroid.antonio.e_comerce.activity.usuario.MainActivityUsuario;

import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;
import devandroid.antonio.e_comerce.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    String email = result.getData().getStringExtra("email");
                    binding.editEmail.setText(email);
                }

            }
    );

    String email;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = binding.editEmail.getText().toString();
        password = binding.editSenha.getText().toString();
        validaDados();
        configClicks();
    }



    private void configClicks() {
        binding.btnrecuperarSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperarsenhaActivity.class));
        });
        binding.textView2.setOnClickListener(view -> {
            Intent intent = new Intent(this,CadastroActivity.class);
            resultLauncher.launch(intent);
        });

        binding.btnVoltar.ibVoltar.setOnClickListener(view -> finish());
    }

    private void login(String email, String senha){
        FirebaseHelper.getAuth().signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        recuperaUsuario(task.getResult().getUser().getUid());
                        finish();
                    }else{
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(this,
                                FirebaseHelper.validaErros(task.getException().getMessage()),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void recuperaUsuario(String id){
        DatabaseReference usuarioref = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(id);
        usuarioref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){// Usuario

                    startActivity(new Intent(getBaseContext(), MainActivityUsuario.class));
                }else{// Loja
                    startActivity(new Intent(getBaseContext(), MainActivityEmpresa.class));
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void validaDados(){
        binding.btnLogin.setOnClickListener(view -> {
            email = binding.editEmail.getText().toString().trim();
            password = binding.editSenha.getText().toString().trim();
            if (!email.isEmpty()){
                if (!password.isEmpty()){
                    binding.progressBar.setVisibility(View.VISIBLE);
                    login(email,password);
                }else {
                    binding.editSenha.requestFocus();
                    binding.editSenha.setError("Digite sua senha!!");
                }
            }else {
                binding.editEmail.requestFocus();
                binding.editEmail.setError("Informe seu email!");
            }


        });

    }


}




