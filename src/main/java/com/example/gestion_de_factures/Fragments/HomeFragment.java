package com.example.gestion_de_factures.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_de_factures.Activities.HomeActivity;
import com.example.gestion_de_factures.Activities.PaiementFactureActivity;
import com.example.gestion_de_factures.Activities.PayerActivity;
import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Classes.FactureRecyclerAdapter;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.R;
import com.example.gestion_de_factures.databinding.FragmentHomeBinding;

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment implements FactureRecyclerAdapter.OnListItemClick{

    private FragmentHomeBinding binding;

    List<Facture> factureList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       /* factureList = new LinkedList<>();

        factureList.add(new Facture("type","01/01/22",2500,1));*/
        factureList = Intermediaire.getFactureList();

        RecyclerView recyclerView  = root.findViewById(R.id.recyclerHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FactureRecyclerAdapter adapter  = new FactureRecyclerAdapter(factureList,this);
        recyclerView.setAdapter(adapter);


        adapter.notifyItemInserted(factureList.size()-1);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnListClick(int position) {
        factureList.get(position);
        Intent intent = new Intent(getContext(), PayerActivity.class);
        intent.putExtra("type",factureList.get(position).getType_facture());
        intent.putExtra("date",factureList.get(position).getDate());
        intent.putExtra("prix",factureList.get(position).getPrix());
        intent.putExtra("numero",factureList.get(position).getNumb_fact());
        intent.putExtra("id",factureList.get(position).getIdFacture());

        startActivity(intent);
    }
}