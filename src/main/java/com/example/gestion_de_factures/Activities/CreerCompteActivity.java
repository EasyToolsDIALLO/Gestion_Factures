package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion_de_factures.Classes.Compte;
import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Databases.CompteUtilisateur;
import com.example.gestion_de_factures.Databases.DBOpenHelper;
import com.example.gestion_de_factures.R;

import java.util.List;

public class CreerCompteActivity extends AppCompatActivity {

    private EditText login, password, confirm_password;
    private Button enregistrer, annuler;
    private List<Facture> factureList;
    Compte compte;
    CompteUtilisateur db_usercompte = new CompteUtilisateur(CreerCompteActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        enregistrer = findViewById(R.id.creercompte);
        annuler = findViewById(R.id.annulercreercompte);
        enregistrer.setOnClickListener(view -> {
            login = findViewById(R.id.edtlogincreate);
            password = findViewById(R.id.edtnewpassword);
            confirm_password = findViewById(R.id.edtconfirmpassword);
            String message = "Compte creer avec succes";
            if(password.getText().toString().equals(confirm_password.getText().toString())){
                Intent intent = new Intent(CreerCompteActivity.this,MainActivity.class);
                /*intent.putExtra("login",login.getText().toString());
                intent.putExtra("password",password.getText().toString());*/
                //database.insertCompte(new Compte(login.getText().toString(),password.getText().toString()));
                compte = db_usercompte.createCompte(login.getText().toString(),password.getText().toString());
                /*factureList.add(new Facture("Eau","06/01/22","1530" ,"36"));
                factureList.add(new Facture("Restauration","02/01/22","2500" ,"1"));*/
                startActivity(intent);
                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(),"Veuillez verifier les mot de passe", Toast.LENGTH_SHORT).show();

        });

        annuler.setOnClickListener(view -> {
            Intent intent = new Intent(CreerCompteActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }
}