package br.com.whatsapp.cursoandroid.horasestudo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private SQLiteDatabase banco;
    private Spinner spinner;
    private ArrayAdapter<String> intesAda;
    private ArrayList<String> itens;
    private ListView lista;
    private ArrayList<Integer> ids;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.btnCada);
        lista = (ListView) findViewById(R.id.lstRes);
        textView = (TextView) findViewById(R.id.edtMateria);





    }
}
