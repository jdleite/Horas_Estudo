package br.com.whatsapp.cursoandroid.horasestudo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {

    private Button botao;
    private EditText edtText;
    private SQLiteDatabase banco;
    private ListView listaMateria;
    private ArrayAdapter<String> itensAda;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botao = (Button) findViewById(R.id.btnCada);
        edtText = (EditText) findViewById(R.id.edtMateria);
        listaMateria = (ListView) findViewById(R.id.lstRes);

        banco = openOrCreateDatabase("horaEstudo", MODE_PRIVATE, null);

        banco.execSQL("CREATE TABLE IF NOT EXISTS estudos(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "materia VARCHAR)");

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String materia = edtText.getText().toString();

                salvar(materia);


            }
        });

        recuperar();


    }

    public void salvar(String materia) {
        try {

            if (materia.equals("")) {
                Toast.makeText(getApplicationContext(), "Nada digitado", Toast.LENGTH_SHORT).show();
            } else {
                banco.execSQL("INSERT INTO estudo(materias) VALUES ('" + materia + "')");

                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(i);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void recuperar(){
        try {

            Cursor cursor = banco.rawQuery("SELECT * FROM estudo ORDER BY id DESC",null);

            int  indId = cursor.getColumnIndex("id");
            int indTare = cursor.getColumnIndex("materia");

            itens = new ArrayList<String>();

            ids = new ArrayList<Integer>();

            itensAda = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,itens);
            listaMateria.setAdapter(itensAda);

            cursor.moveToFirst();

            while (cursor != null){

                Log.i("Resultado - ", "Tarefa" + cursor.getString(indTare));
                itens.add(cursor.getString(indTare));
                ids.add(Integer.parseInt(cursor.getString(indId)));

                cursor.moveToNext();

            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
