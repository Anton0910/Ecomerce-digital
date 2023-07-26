package devandroid.antonio.e_comerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devandroid.antonio.e_comerce.autentication.LoginActivity;
import devandroid.antonio.e_comerce.databinding.ActivityMainBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnLogin.setOnClickListener(view1 -> {
           if(FirebaseHelper.getAutenticado()){
               FirebaseHelper.getAuth().signOut();
               Toast.makeText(this,"Usuário já autenticado!",Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(this,"Usuário não autenticado!",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(this, LoginActivity.class));
           }
        });

    }
}