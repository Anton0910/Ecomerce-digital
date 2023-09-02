package devandroid.antonio.e_comerce.model;

import android.media.Image;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import devandroid.antonio.e_comerce.databinding.FragmentEmpresaCategoriaBinding;
import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class Categoria {
    private String nome;
    private String id;
    private String Urlimagem;
    private boolean todas = false;

    public String getNome() {
        return nome;
    }

    public Categoria(){
        DatabaseReference categoriaref = FirebaseHelper.getDatabaseReference();
        this.setId(categoriaref.push().getKey());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlimagem() {
        return Urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        Urlimagem = urlimagem;
    }

    public boolean isTodas() {
        return todas;
    }

    public void setTodas(boolean todas) {
        this.todas = todas;
    }

    public void salvar() {
        DatabaseReference categoriaref = FirebaseHelper.getDatabaseReference().child("categorias").child(this.getId());
        categoriaref.setValue(this);

    }
}
