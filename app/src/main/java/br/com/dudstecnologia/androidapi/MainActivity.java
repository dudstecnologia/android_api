package br.com.dudstecnologia.androidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCriar = (TextView) findViewById(R.id.txtCriar);

        txtCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abreCadastro = new Intent(MainActivity.this, ActCadastro.class);
                startActivity(abreCadastro);
            }
        });
    }
}
