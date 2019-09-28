package br.com.dudstecnologia.androidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);

        AuthService authService = new AuthService(ActPrincipal.this);

        setTitle(authService.getAcesso().getName());
    }
}
