package com.example.gestion_de_factures.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestion_de_factures.Activities.PayerActivity;
import com.example.gestion_de_factures.Activities.SuppressPayerActivity;
import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.FactureRecyclerAdapter;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.R;

import java.util.List;

public class PayeeFragment extends Fragment implements FactureRecyclerAdapter.OnListItemClick{
    List<Facture> factureList;
    public PayeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_payee, container, false);
        factureList = Intermediaire.getFactureList_payee();

        RecyclerView recyclerView  = root.findViewById(R.id.recyclerPayee);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FactureRecyclerAdapter adapter  = new FactureRecyclerAdapter(factureList,this);
        recyclerView.setAdapter(adapter);

        adapter.notifyItemInserted(factureList.size()-1);

        return root;
    }

    @Override
    public void OnListClick(int position) {
        factureList.get(position);
        Intent intent = new Intent(getContext(), SuppressPayerActivity.class);
        intent.putExtra("type",factureList.get(position).getType_facture());
        intent.putExtra("date",factureList.get(position).getDate());
        intent.putExtra("prix",(int)factureList.get(position).getPrix());
        intent.putExtra("numero",(int)factureList.get(position).getNumb_fact());
        intent.putExtra("id",(int)factureList.get(position).getIdFacture());

        startActivity(intent);
    }
}