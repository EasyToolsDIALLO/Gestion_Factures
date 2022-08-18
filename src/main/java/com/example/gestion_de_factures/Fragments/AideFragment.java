package com.example.gestion_de_factures.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gestion_de_factures.Activities.HomeActivity;
import com.example.gestion_de_factures.R;


public class AideFragment extends Fragment {

    private Button btnLire, btnQuitter ;
    private VideoView video ;
    private MediaController mc;

    public AideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aide, container, false);

        video = view.findViewById(R.id.video);
        btnLire = view.findViewById(R.id.btnLire);
        btnQuitter = view.findViewById(R.id.btnQuitter);
        // btnQuitter.setEnabled(false);
        mc = new MediaController(getActivity());
        final  String path = "android.resource://" + getActivity().getPackageName()+ "/" + R.raw.aide_tuto;
        btnLire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.setVideoPath(path);
                video.setMediaController(mc);
                video.start();

                // btnQuitter.setEnabled(true);

            }
        });

        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}