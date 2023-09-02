package devandroid.antonio.e_comerce.fragment.loja;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
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
import devandroid.antonio.e_comerce.helper.FirebaseHelper;
import devandroid.antonio.e_comerce.model.Categoria;


public class EmpresaCategoriaFragment extends Fragment {


    private FragmentEmpresaCategoriaBinding binding;
    private AlertDialog alertDialog;

    private  DialogFormCategoriaBinding categoriaBinding;

    private Categoria categoria;


    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private String caminhoImagem = null;


    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK ){
                   //Recupera caminho da imagem
                    Uri imagemSelecionada = result.getData().getData();
                     caminhoImagem = imagemSelecionada.toString();

                     try{
                         Bitmap  bitmap;
                         if (Build.VERSION.SDK_INT<28){
                                bitmap =
                                     MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imagemSelecionada);
                         }else {
                             ImageDecoder.Source source =
                                     ImageDecoder.createSource(getActivity().getContentResolver()
                                             ,imagemSelecionada);
                              bitmap = ImageDecoder.decodeBitmap(source);
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


         categoriaBinding = DialogFormCategoriaBinding.inflate(LayoutInflater.from(getContext()));

        biulder.setView(categoriaBinding.getRoot());

        categoriaBinding.btnFechar.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        categoriaBinding.btnSalvar.setOnClickListener(v -> {
            if (categoriaBinding.edtCategoria.getText().toString().isEmpty()){
                categoriaBinding.edtCategoria.requestFocus();
                categoriaBinding.edtCategoria.setError("Digite algum produto!");

            }else if (caminhoImagem == null){
                ocultaTeclado();
                Toast.makeText(getContext(),"Selecione uma imagem para a categoria!",
                        Toast.LENGTH_SHORT).show();
            }else {
                if (categoria == null){
                    categoria = new Categoria();
                }
                categoria.setNome(categoriaBinding.edtCategoria.getText().toString());
                categoria.setTodas(categoriaBinding.cbTodos.isChecked());
                salvarImagemFirebase();

            }

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

    private void ocultaTeclado(){
        InputMethodManager inputMethodManager =
                (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(categoriaBinding.edtCategoria.getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void salvarImagemFirebase(){
        StorageReference storageReference = FirebaseHelper.getStorageReference()
                .child("imagens")
                .child("categorias")
                .child(categoria.getId() + "jpeg.");
        UploadTask uploadTask = storageReference.putFile(Uri.parse(caminhoImagem));
        uploadTask.addOnSuccessListener(taskSnapshot ->  storageReference.getDownloadUrl().addOnCompleteListener(task -> {

                String urlImagem = task.getResult().toString();
                categoria.setUrlimagem(urlImagem);
                categoria.salvar();
            Toast.makeText(getContext(), "PASSOU AQUI", Toast.LENGTH_SHORT).show();
                categoria = null;
                alertDialog.dismiss();
                })).addOnFailureListener(e->{
                    alertDialog.dismiss();
                    Toast.makeText(getContext(),"Erro ao fazeer upload da imagem.",
                            Toast.LENGTH_SHORT);
        });

    }
}