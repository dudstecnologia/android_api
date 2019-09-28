package br.com.dudstecnologia.androidapi;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.dudstecnologia.androidapi.entidades.Usuario;

public class AuthService {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public AuthService(Context context) {
        this.prefs = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }

    public void setAcesso(Usuario usuario) {
        editor.putInt("id", usuario.getId()).commit();
        editor.putString("name", usuario.getName()).commit();
        editor.putString("email", usuario.getEmail()).commit();
        editor.putString("token", usuario.getToken()).commit();
    }

    public Usuario getAcesso() {
        Usuario usuario = new Usuario();

        usuario.setId(prefs.getInt("id", 0));
        usuario.setName(prefs.getString("name", ""));
        usuario.setEmail(prefs.getString("email", ""));
        usuario.setToken(prefs.getString("token", ""));

        return usuario;
    }

    public void deleteAcesso() {
        editor.remove("id").commit();
        editor.remove("name").commit();
        editor.remove("email").commit();
        editor.remove("token").commit();
    }
}
