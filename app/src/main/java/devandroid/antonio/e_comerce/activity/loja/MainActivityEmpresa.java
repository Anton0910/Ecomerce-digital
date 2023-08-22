package devandroid.antonio.e_comerce.activity.loja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.databinding.ActivityLoginBinding;
import devandroid.antonio.e_comerce.databinding.ActivityMainEmpresaBinding;

public class MainActivityEmpresa extends AppCompatActivity {
    private ActivityMainEmpresaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empresa);

        binding = ActivityMainEmpresaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_empresa);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.BottomNavigationView ,navController);
    }
}