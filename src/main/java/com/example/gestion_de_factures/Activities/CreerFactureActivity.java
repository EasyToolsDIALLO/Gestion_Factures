package com.example.gestion_de_factures.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.example.gestion_de_factures.Classes.Facture;
import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.R;

import java.util.Calendar;

public class CreerFactureActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText numero,prix;
    Spinner type;
    EditText date;
    Button enregistrer, annuler;
    TabFActuresNonPayees db_facture_nonpayee = new TabFActuresNonPayees(CreerFactureActivity.this);
    DatePickerDialog.OnDateSetListener setListener;
    Facture facture;
    String date_limite = "",type_facture= "Electricite" ;
    int numb,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_facture);

        date = (EditText) findViewById(R.id.edtDateFact);
        type = (Spinner) findViewById(R.id.SpinnerTypeFact);
        String[] types = getResources().getStringArray(R.array.type_factures);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,types);
        arrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(arrayAdapter);
        type.setOnItemSelectedListener(this);

        //There are two methods for fix date
        //First method
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(view1 -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreerFactureActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year, month,day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month += 1;
                String dat = dayOfMonth+"/"+month+"/"+year;
                date_limite = dat;
                date.setText(dat);
            }
        };


        enregistrer = (Button) findViewById(R.id.btnEnregistrer);
        annuler = (Button) findViewById(R.id.btnAnnuler);
        annuler.setOnClickListener(view -> {
            Intent intent = new Intent(CreerFactureActivity.this, HomeActivity.class);
            startActivity(intent);
        });


        enregistrer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                numero = (EditText) findViewById(R.id.edtNumeroFact);
                prix = (EditText) findViewById(R.id.edtPrixFact);

                numb = (int) Integer.parseInt(numero.getText().toString());
                price = (int) Integer.parseInt(prix.getText().toString());
                facture = db_facture_nonpayee.createFactureNonpayee(type_facture,date_limite,price,numb);
                Intent intent = new Intent(CreerFactureActivity.this, HomeActivity.class);
                Toast.makeText(CreerFactureActivity.this,type_facture,Toast.LENGTH_LONG).show();
                startActivity(intent);


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView.getId()==R.id.SpinnerTypeFact){
            type_facture = adapterView.getItemAtPosition(position).toString();
            date_limite= " ";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}