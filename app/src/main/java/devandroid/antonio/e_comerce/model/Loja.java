package devandroid.antonio.e_comerce.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import devandroid.antonio.e_comerce.helper.FirebaseHelper;

public class Loja {

    private String nome;
    private String id;
    private String email;
    private String senha;
    private String publicKey;
    private String accessToken;
    private int parcelas;

    public Loja() {
    }

    public String getNome() {
        return nome;
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

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public void salvar() {
        DatabaseReference lojaref = FirebaseHelper.getDatabaseReference()
                .child("loja");
        lojaref.setValue(this);
    }
}
