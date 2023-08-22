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
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);

        binding = ActivityMainUsuarioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

         navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.BottomNavigationView ,navController);



    }
}