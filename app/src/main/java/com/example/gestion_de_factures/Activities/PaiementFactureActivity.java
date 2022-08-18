package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.FactureRecyclerAdapter;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.R;

import java.util.LinkedList;
import java.util.List;

public class PaiementFactureActivity extends AppCompatActivity implements FactureRecyclerAdapter.OnListItemClick{

    TabFActuresNonPayees db_facture_nonpayee = new TabFActuresNonPayees(PaiementFactureActivity.this);
    List<Facture> factureList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_facture);
        factureList = db_facture_nonpayee.getAllFacturesNonPayee();

        Intermediaire.setFactureList(db_facture_nonpayee.getAllFacturesNonPayee());

       /* factureList = new LinkedList<>();
        factureList.add(new Facture("Electricite","12/02/22",2500 ,1678));*/

        RecyclerView recyclerView  = findViewById(R.id.recyclerPaiement);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FactureRecyclerAdapter adapter  = new FactureRecyclerAdapter(factureList,this);
        recyclerView.setAdapter(adapter);


        adapter.notifyItemInserted(factureList.size()-1);
    }

    @Override
    public void OnListClick(int position) {

    }
}