package br.com.whatsapp.cursoandroid.horasestudo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private SQLiteDatabase banco;
    private ArrayAdapter<String> itensAda;
    private ArrayList<String> itens;
    private ListView listaMateria;
    private ArrayList<Integer> ids;
    private TextView textoExib;
    private int indTare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botao = (Button) findViewById(R.id.btnCarre);
        listaMateria = (ListView) findViewById(R.id.lstRes);
        textoExib = (TextView) findViewById(R.id.txtMainExi);




        recuperar();


        listaMateria.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle b = new Bundle();


                b.putString("materia", itens.get(position));
                b.putInt("id", ids.get(position));
                Intent i = new Intent(MainActivity.this, SomaHoraActivity.class);
                i.putExtras(b);

                startActivity(i);
                return true;
            }
        });

        listaMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String nome = itens.get(position);
                 banco = openOrCreateDatabase("teste", MODE_PRIVATE, null);
                 Cursor cursor = banco.rawQuery("SELECT hora FROM estudos WHERE materia = '"+nome+"'",null);
                cursor.moveToFirst();

                int hora = cursor.getColumnIndex("hora");

                String hMat = cursor.getString(hora);


                textoExib.setText(hMat);






            }
        });


    }

    public void recuperar() {
        try {
            banco = openOrCreateDatabase("teste", MODE_PRIVATE, null);


            Cursor cursor = banco.rawQuery("SELECT * FROM estudos ORDER BY id DESC", null);

            int indId = cursor.getColumnIndex("id");
            indTare = cursor.getColumnIndex("materia");
            int hora = cursor.getColumnIndex("hora");

            itens = new ArrayList<String>();

            ids = new ArrayList<Integer>();

            itensAda = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, itens);
            listaMateria.setAdapter(itensAda);

            cursor.moveToFirst();


            while (cursor != null) {

                itens.add(cursor.getString(indTare));
                ids.add(Integer.parseInt(cursor.getString(indId)));
                cursor.moveToNext();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cadastro(View view) {
        Intent i = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(i);
    }


}
