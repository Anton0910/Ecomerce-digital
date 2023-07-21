package devandroid.antonio.e_comerce.autentication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityCadastroBinding;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;
import devandroid.antonio.e_comerce.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    private String email;
    private String password;
    private String confirmPassword;
    private String nome;

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

        binding.btnLogin.setOnClickListener(view -> {
            validaDados();

        });
    }

    public void validaDados(){
        binding.btnLogin.setOnClickListener(view -> {
            email = binding.editEmail.getText().toString();
            password = binding.editSenha.getText().toString().trim();
            confirmPassword = binding.editConfirmSenha.getText().toString().trim();
            nome = binding.editNome.getText().toString().trim();
            if (!nome.isEmpty()){
                if (!email.isEmpty()){
                    if (!password.isEmpty()){
                        if (!confirmPassword.isEmpty()){

                            if (password.equals(confirmPassword)){
                                Usuario usuario = new Usuario();
                                usuario.setNome(nome);
                                usuario.setEmail(email);
                                usuario.setSenha(password);
                                criarConta(usuario);

                            }else {
                                binding.editConfirmSenha.requestFocus();
                                binding.editConfirmSenha.setError("Senhas diferentes!");
                            }

                        }else {
                            binding.editConfirmSenha.requestFocus();
                            binding.editConfirmSenha.setError("Confime sua senha!");
                        }
                    }else {
                        binding.editSenha.requestFocus();
                        binding.editSenha.setError("Digite sua senha!!");
                    }
                }else {
                    binding.editEmail.requestFocus();
                    binding.editEmail.setError("Informe seu email!");
                }
            }else {
                binding.editNome.requestFocus();
                binding.editNome.setError("Informe seu nome!");
            }


        });
    }



    public void criarConta(Usuario usuario){
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(task ->  {
                        if (task.isSuccessful()) {
                          String id = task.getResult().getUser().getUid();
                          usuario.setId(id);
                          usuario.salvar();

                        } else {
                           Toast.makeText(this,
                                   FirebaseHelper.validaErros(task.getException().getMessage()),
                                   Toast.LENGTH_SHORT).show();
                        }
                });

    }
}