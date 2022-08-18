package com.example.gestion_de_factures.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gestion_de_factures.Activities.ModifierCompteActivity;
import com.example.gestion_de_factures.Classes.Intermediaire;
import com.example.gestion_de_factures.R;


public class ProfilFragment extends Fragment {

    TextView login,password;
    Button modifier;
    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        login = root.findViewById(R.id.txtLogin);
        password = root.findViewById(R.id.txtPassword);

        login.setText(Intermediaire.getCompte().getLogin());
        password.setText(Intermediaire.getCompte().getPassword());

        return root;
    }
}