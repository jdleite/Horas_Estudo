package br.com.whatsapp.cursoandroid.horasestudo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroActivity extends AppCompatActivity {

    private Button botao;
    private EditText edtText;
    private SQLiteDatabase banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botao =  findViewById(R.id.btnCada);
        edtText =  findViewById(R.id.edtMateria);

        bancoAce();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String materia = edtText.getText().toString();

                salvar(materia.toUpperCase());


            }
        });


    }

    public void salvar(String materia) {
        try {

            if (materia.equals("")) {
                Toast.makeText(getApplicationContext(), "Nada digitado", Toast.LENGTH_SHORT).show();
            } else {
                banco.execSQL("INSERT INTO estudos(materia,hora) VALUES ('" + materia + "',0)");

                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(i);


            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public void bancoAce() {

        banco = openOrCreateDatabase("estudo", MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS estudos(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "materia VARCHAR," +
                "hora INTEGER)");


    }


}
