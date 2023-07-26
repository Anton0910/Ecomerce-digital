package devandroid.antonio.e_comerce.activity.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.autentication.LoginActivity;

import devandroid.antonio.e_comerce.databinding.ActivityMainUsuarioBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class MainActivityUsuario extends AppCompatActivity {

    private ActivityMainUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);
        binding = ActivityMainUsuarioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.BottomNavigationView ,navController);
        binding.buttonLogin.setOnClickListener(view1 -> {
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