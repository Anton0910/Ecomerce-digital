package devandroid.antonio.e_comerce.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class Usuario {
    private String nome;
    private String id;
    private String email;
    private String senha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario(){
        DatabaseReference usuarioRef = FirebaseHelper.getDatabaseReference();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void salvar() {
        DatabaseReference usuarioref = FirebaseHelper.getDatabaseReference()
                        .child("usuarios")
                        .child(this.getId());
        usuarioref.setValue(this);
    }
}
