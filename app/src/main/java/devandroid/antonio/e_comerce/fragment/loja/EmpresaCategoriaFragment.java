package devandroid.antonio.e_comerce.fragment.loja;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.autentication.LoginActivity;
import devandroid.antonio.e_comerce.databinding.FragmentEmpresaCategoriaBinding;
import devandroid.antonio.e_comerce.databinding.FragmentUsuarioPerfilBinding;


public class EmpresaCategoriaFragment extends Fragment {


    private FragmentEmpresaCategoriaBinding binding;
    private AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmpresaCategoriaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configClicks();

    }

    private void configClicks(){
        binding.btnAddCategoria.setOnClickListener(view -> {
            showDialog();
        });
    }
    private void showDialog(){
        AlertDialog.Builder biulder = new AlertDialog.Builder(getContext(),
                R.style.CustomAlertDialog);

        View view = getLayoutInflater().inflate(R.layout.dialog_form_categoria,null);

        biulder.setView(view);

        alertDialog = biulder.create();
        alertDialog.show();
    }
}