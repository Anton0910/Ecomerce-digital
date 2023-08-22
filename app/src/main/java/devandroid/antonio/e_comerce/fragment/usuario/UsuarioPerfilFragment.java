package devandroid.antonio.e_comerce.fragment.usuario;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.autentication.LoginActivity;
import devandroid.antonio.e_comerce.databinding.ActivityMainUsuarioBinding;
import devandroid.antonio.e_comerce.databinding.FragmentUsuarioPerfilBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;


public class UsuarioPerfilFragment extends Fragment {

    private FragmentUsuarioPerfilBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsuarioPerfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(view1 -> {
                startActivity(new Intent(getActivity(), LoginActivity.class));
        });
    }
}