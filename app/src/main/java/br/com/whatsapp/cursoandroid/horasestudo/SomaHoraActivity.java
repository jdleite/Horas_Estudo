package br.com.whatsapp.cursoandroid.horasestudo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.DecimalFormat;


public class SomaHoraActivity extends AppCompatActivity {

    private TextView nomeMAteria;
    private Button mais;
    private Button menos;
    private TextView materia;
    private TextView hora;
    private Button btnGravar;
    private SQLiteDatabase banco;
    private int c;
    private int inteiro;
    private int resto;
    private int somaTudo;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soma_hora);


        banco = openOrCreateDatabase("estudo", MODE_PRIVATE, null);


        mais = findViewById(R.id.btnSoMa);
        menos = findViewById(R.id.btnSoMe);
        materia = findViewById(R.id.txtSomMa);
        hora = findViewById(R.id.txtSomaHora);
        btnGravar = findViewById(R.id.btnSomGra);

        getMateria();
        somar();
        subtrair();


        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gravar();

            }
        });


    }

    public void getMateria() {


        nomeMAteria = findViewById(R.id.txtSomMa);

        Intent i = getIntent();

        Bundle b = i.getExtras();


        String itens = b.getString("materia");
        id = b.getInt("id");


        nomeMAteria.setText(itens);


    }

    public void somar() {

        final DecimalFormat df = new DecimalFormat("00");


        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c += 15;
                inteiro = c / 60;
                resto = c % 60;
                somaTudo = (inteiro * 60) + resto;



                hora.setText(Integer.toString(inteiro) + ":" + df.format(resto));



            }
        });


    }

    public void subtrair() {

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c -= 15;
                inteiro = c / 60;
                resto = c % 60;

                somaTudo = (inteiro * 60) + resto;

                hora.setText(Integer.toString(inteiro) + ":" + Integer.toString(resto));


            }
        });

    }

    public void gravar() {


        banco.execSQL("UPDATE estudos SET hora = hora +'" + somaTudo + "'  WHERE id ='" + id + "' ");

        Intent i = new Intent(SomaHoraActivity.this, MainActivity.class);


        startActivity(i);


    }
}
