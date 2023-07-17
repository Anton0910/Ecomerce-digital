package devandroid.antonio.e_comerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import devandroid.antonio.e_comerce.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.textInfo.setText("Texto alterado");
    }
}