package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion_de_factures.Classes.Compte;
import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.Databases.CompteUtilisateur;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtLogin, txtPassword;
    private Button btnconnect, btnsignup;
    private String login="", password="";
    Facture fact;
    CompteUtilisateur db_usercompte = new CompteUtilisateur(MainActivity.this);
    TabFActuresNonPayees table_nonpayee = new TabFActuresNonPayees(MainActivity.this);


    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnconnect =  findViewById(R.id.btnConnection);
        btnsignup =  findViewById(R.id.btnSignUp);

        //Recuperation des valeurs sur l'interface d'accueil
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean vrai= false;
                List<Compte> users = db_usercompte.getAllComptes();
                txtLogin = findViewById(R.id.EdtLogin);
                txtPassword = findViewById(R.id.EdtPassword);
                for(int i=0;i<users.size();i++){
                    if(txtLogin.getText().toString().equals(users.get(i).getLogin()) &&
                            txtPassword.getText().toString().equals(users.get(i).getPassword())){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        Intermediaire.setFactureList(table_nonpayee.getAllFacturesNonPayee());
                        Intermediaire.setCompte(new Compte(users.get(i).getLogin(),users.get(i).getPassword()));
                        Toast.makeText(getApplicationContext(), "BIENVENUE", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }

                }




                /*Intent intents = null;
                intents = getIntent();
                if(intents!=null){
                    login  = intents.getExtras().getString("login");
                    password = intents.getExtras().getString("password");}*/




            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreerCompteActivity.class);
                startActivity(intent);
            }
        });
    }

}