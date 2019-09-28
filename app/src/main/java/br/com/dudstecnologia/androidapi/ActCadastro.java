package br.com.dudstecnologia.androidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ActCadastro extends AppCompatActivity {

    EditText editNomeCad, editEmailCad, editSenhaCad, editConfirmaCad;
    Button btnVoltar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro);

        editNomeCad = (EditText) findViewById(R.id.editNomeCad);
        editEmailCad = (EditText) findViewById(R.id.editEmailCad);
        editSenhaCad = (EditText) findViewById(R.id.editSenhaCad);
        editConfirmaCad = (EditText) findViewById(R.id.editConfirmaCad);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editNomeCad.getText().toString();
                String email = editEmailCad.getText().toString();
                String senha = editSenhaCad.getText().toString();
                String confirma = editConfirmaCad.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirma.isEmpty()) {
                    Log.d("ANDROID_API", "Todos os campos são obrigatórios");
                //} else if(!confirma.contains(senha)) {
                //    Log.d("ANDROID API", "Senhas não conferem");
                } else {

//                    JsonObject dados = new JsonObject();
//                    dados.addProperty("name", nome);
//                    dados.addProperty("email", email);
//                    dados.addProperty("password", senha);
//                    dados.addProperty("password_confirmation", confirma);
//
//                    registro(dados);
                    registro(nome, email, senha, confirma);
                }
            }
        });
    }

//    private void registro(JsonObject dados)
    private void registro(String nome, String email, String senha, String confirma)
    {
        Ion.with(ActCadastro.this)
            .load("http://192.168.1.20:8000/api/register")
            .setHeader("Accept", "application/json")
            //.setJsonObjectBody(dados)
            .setBodyParameter("name", nome)
            .setBodyParameter("email", email)
            .setBodyParameter("password", senha)
            .setBodyParameter("password_confirmation", confirma)
            .asJsonObject()
            .withResponse()
            .setCallback(new FutureCallback<Response<JsonObject>>() {
                @Override
                public void onCompleted(Exception e, Response<JsonObject> result) {

                    // Log.d("ANDROID_API", "" + result.get("message").getAsString());

                    try {
                        int STATUS = result.getHeaders().code();
                        JsonObject retorno = result.getResult();

                        if(STATUS == 201) {
                            Log.d("ANDROID_API", "STATUS 1 " + STATUS);
                            // Log.d("ANDROID_API", result.getResult());
                        } else {
                            // Log.d("ANDROID_API", "STATUS 2 " + STATUS);
                            // Log.d("ANDROID_API", result.getResult());

                            for (Map.Entry<String, JsonElement> error : retorno.get("errors").getAsJsonObject().entrySet()) {

                                JsonArray erroArray = error.getValue().getAsJsonArray();
                                // Log.d("ANDROID_API", entryObj.toString());
                                // Log.d("ANDROID_API", entryObj.get(0).getAsString());
                                // Log.d("ANDROID_API", "CHAVE: " + entry.getKey() + " VALOR: " + entryObj.get(0).getAsString());
                                if(error.getKey().equals("name")) {
                                    editEmailCad.setError(erroArray.get(0).getAsString());
                                }
                                if(error.getKey().equals("email")) {
                                    editEmailCad.setError(erroArray.get(0).getAsString());
                                }
                                if(error.getKey().equals("password")) {
                                    editSenhaCad.setError(erroArray.get(0).getAsString());
                                }
                            }
                        }

                    } catch (Exception erro) {
                        Log.d("ANDROID_API", erro.getMessage());
                    }
                }
            });

        /*
        Ion.with(ActCadastro.this)
            .load("http://192.168.1.20:8000/api/register")
            .setHeader("Accept", "application/json")
            //.setJsonObjectBody(dados)
            .setBodyParameter("name", nome)
            .setBodyParameter("email", email)
            .setBodyParameter("password", senha)
            .setBodyParameter("password_confirmation", confirma)
            .asString()
            .withResponse()
            .setCallback(new FutureCallback<Response<String>>() {
                @Override
                public void onCompleted(Exception e, Response<String> result) {

                    // Log.d("ANDROID_API", "CODE: " + result.getHeaders().code());
                    // Log.d("ANDROID_API", result.getResult());

                    try {
                        int STATUS = result.getHeaders().code();

                        JSONObject jsonObject = new JSONObject(result.getResult());

                        if(STATUS == 201) {
                            Log.d("ANDROID_API", "STATUS 1 " + STATUS);
                            Log.d("ANDROID_API", result.getResult());
                        } else {
                            // Log.d("ANDROID_API", "STATUS 2 " + STATUS);
                            // Log.d("ANDROID_API", result.getResult());
                            Iterator<String> keys = jsonObject.keys();

                            while(keys.hasNext()) {
                                Log.d("ANDROID_API", "VALUE: " + jsonObject.get(keys.next()).toString());
                            }
                        }

                    } catch (Exception erro) {
                        Log.d("ANDROID_API", erro.getMessage());
                    }
                }
            });

         */
    }
}
