package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.Databases.TabFacturesPayees;
import com.example.gestion_de_factures.R;

import java.util.Calendar;

public class PayerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView numero,prix,type;

    TextView date;
    Button payer, annuler, supprimer;
    TabFActuresNonPayees db_facture_nonpayee = new TabFActuresNonPayees(PayerActivity.this);
    TabFacturesPayees db_facture_payee = new TabFacturesPayees(PayerActivity.this);
    DatePickerDialog.OnDateSetListener setListener;
    Facture facture;
    String typ,dat;
    int numb,price,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payer);
        Intent intent = getIntent();
        typ = intent.getExtras().getString("type");
        dat = intent.getExtras().getString("date");
        price = intent.getExtras().getInt("prix");
        numb = intent.getExtras().getInt("numero");
        id = intent.getExtras().getInt("id");

        numero =  findViewById(R.id.txtNumeroFact);
        type =    findViewById(R.id.txtTypeFact);
        prix =    findViewById(R.id.txtPrixFact);
        date =    findViewById(R.id.txtDateFact);

        numero.setText(String.valueOf(numb));
        type.setText(typ);
        prix.setText(String.valueOf(price));
        date.setText(dat);

        payer = (Button) findViewById(R.id.btnPayer);
        annuler = (Button) findViewById(R.id.btnAnnuler);
        supprimer = (Button) findViewById(R.id.btnSupprimer);

        payer.setOnClickListener(view -> {
            //Ajouter une facture à la base de donnée
            Facture facture = db_facture_payee.createFacturePayee(typ,dat,price,numb);
            facture.setIdFacture(id);
            db_facture_nonpayee.deleteFactureNonPayee(facture);
            Intermediaire.setFactureList_payee(db_facture_payee.getAllFacturesPayee());
            Intermediaire.setFactureList(db_facture_nonpayee.getAllFacturesNonPayee());
            Intent intento = new Intent(PayerActivity.this, HomeActivity.class);
            Toast.makeText(PayerActivity.this,"Facture payee",Toast.LENGTH_SHORT).show();
            startActivity(intento);
        });

        supprimer.setOnClickListener(view -> {
            Facture facture = new Facture(typ,dat,price,numb);
            facture.setIdFacture(id);
            db_facture_nonpayee.deleteFactureNonPayee(facture);
            Intermediaire.setFactureList(db_facture_nonpayee.getAllFacturesNonPayee());
            Intent sup = new Intent(PayerActivity.this, HomeActivity.class);
            Toast.makeText(PayerActivity.this,"Facture supprimee",Toast.LENGTH_SHORT).show();
            startActivity(sup);
        });

        annuler.setOnClickListener(view -> {
            Intent intents = new Intent(PayerActivity.this, HomeActivity.class);
            startActivity(intents);
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}