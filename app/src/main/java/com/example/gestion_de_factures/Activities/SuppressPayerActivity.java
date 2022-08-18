package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.Databases.TabFacturesPayees;
import com.example.gestion_de_factures.R;

public class SuppressPayerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button supprimer,annuler;
    TextView nume,date,type,prix;
    TabFacturesPayees db_facture_payee = new TabFacturesPayees(SuppressPayerActivity.this);
    String typ,dat;
    int numb,price,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppress_payer);
        Intent intent = getIntent();
        typ = intent.getExtras().getString("type");
        dat = intent.getExtras().getString("date");
        price = intent.getExtras().getInt("prix");
        numb = intent.getExtras().getInt("numero");
        id = intent.getExtras().getInt("id");

        nume = (TextView) findViewById(R.id.txtNumeroFact);
        date = (TextView) findViewById(R.id.txtDateFact);
        type = (TextView) findViewById(R.id.txtTypeFact);
        prix = (TextView) findViewById(R.id.txtPrixFact);

        nume.setText(String.valueOf(numb));
        type.setText(typ);
        prix.setText(String.valueOf(price));
        date.setText(dat);

        annuler = (Button) findViewById(R.id.btnAnnuler);
        supprimer = (Button) findViewById(R.id.btnSuppression);

        annuler.setOnClickListener(view -> {
            Intent intes = new Intent(SuppressPayerActivity.this,HomeActivity.class);
            startActivity(intes);
        });

        supprimer.setOnClickListener(view -> {
            Facture facture = new Facture(typ,dat,price,numb);
            facture.setIdFacture(id);
            db_facture_payee.deleteFacturePayee(facture);
            Intermediaire.setFactureList(db_facture_payee.getAllFacturesPayee());
            Intent inteso = new Intent(SuppressPayerActivity.this,HomeActivity.class);
            Toast.makeText(SuppressPayerActivity.this,"Facture supprimee",Toast.LENGTH_SHORT).show();
            startActivity(inteso);
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}