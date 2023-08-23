package devandroid.antonio.e_comerce.fragment.loja;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import devandroid.antonio.e_comerce.R;
import devandroid.antonio.e_comerce.autentication.LoginActivity;
import devandroid.antonio.e_comerce.databinding.DialogFormCategoriaBinding;
import devandroid.antonio.e_comerce.databinding.FragmentEmpresaCategoriaBinding;
import devandroid.antonio.e_comerce.databinding.FragmentUsuarioPerfilBinding;


public class EmpresaCategoriaFragment extends Fragment {


    private FragmentEmpresaCategoriaBinding binding;
    private AlertDialog alertDialog;

    private  DialogFormCategoriaBinding categoriaBinding;


    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private String caminhoImagem = null;

    private  Bitmap bitmap;
    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK ){
                   //Recupera caminho da imagem
                    Uri imagemSelecionada = result.getData().getData();
                     caminhoImagem = imagemSelecionada.toString();

                     try{
                         if (Build.VERSION.SDK_INT<28){
                             bitmap =
                                     MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imagemSelecionada);
                         }else {
                             ImageDecoder.Source source =
                                     ImageDecoder.createSource(getActivity().getContentResolver()
                                             ,imagemSelecionada);
                         }
                         categoriaBinding.imagemCategoria.setImageBitmap(bitmap);
                     }catch (Exception e){
                         e.printStackTrace();
                     }

                }

            }
    );

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


         categoriaBinding =
                DialogFormCategoriaBinding.inflate(LayoutInflater.from(getContext()));

        biulder.setView(categoriaBinding.getRoot());

        categoriaBinding.btnFechar.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        categoriaBinding.btnSalvar.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        categoriaBinding.imagemCategoria.setOnClickListener(view -> {
            verificaPermissaoGaleria();
        });



        alertDialog = biulder.create();
        alertDialog.show();
    }

    private void verificaPermissaoGaleria(){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                abrirGaleria();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getContext(), "Permissão Negada",
                        Toast.LENGTH_SHORT).show();
            }


        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedTitle("Permissões")
                .setDeniedMessage("Se voce rejeitar a permissão,você não podera usar esse " +
                        "serviço\n\nPor favor, ative a permissão em [Configurações] > [Permissões]")
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(Manifest.permission.READ_MEDIA_IMAGES)
                .check();

    }



    private void abrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);
    }
}