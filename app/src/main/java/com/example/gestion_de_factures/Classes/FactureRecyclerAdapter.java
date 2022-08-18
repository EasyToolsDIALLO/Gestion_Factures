package com.example.gestion_de_factures.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_de_factures.Databases.TabFActuresNonPayees;
import com.example.gestion_de_factures.R;

import java.util.List;

public class FactureRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Facture> factureList;
    OnListItemClick onListItemClick;

    public FactureRecyclerAdapter(List<Facture> factureList,OnListItemClick onListItemClick) {
        this.factureList = factureList;
        this.onListItemClick = onListItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_model,parent,false);
        return  new MyViewHolder(itemView,onListItemClick).LinkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.numb_Value.setText(String.valueOf(factureList.get(position).getNumb_fact()));
        holder.type_facture.setText(factureList.get(position).getType_facture());
        holder.prix_Value.setText(String.valueOf(factureList.get(position).getPrix()));
        holder.date.setText(factureList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return factureList.size();
    }

    public interface OnListItemClick{
        void OnListClick(int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView numb,numb_Value,prix,prix_Value,date,type_facture;
        Button payer;
        FactureRecyclerAdapter adapter;
        FactureRecyclerAdapter.OnListItemClick onListItemClick;
        public MyViewHolder(@NonNull View itemView, FactureRecyclerAdapter.OnListItemClick onListItemClick) {
            super(itemView);
            this.onListItemClick = onListItemClick;
            numb =  itemView.findViewById(R.id.txtnumb);
            numb_Value =  itemView.findViewById(R.id.txtNumeroFacture);
            type_facture =  itemView.findViewById(R.id.txtTypeFacture);
            prix =  itemView.findViewById(R.id.txtprix);
            prix_Value =  itemView.findViewById(R.id.txtPrixFacture);
            date =  itemView.findViewById(R.id.txtDateFacture);
           /* payer =  itemView.findViewById(R.id.btnPayer);
            payer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.factureList.remove(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());

                    Toast.makeText(view.getContext(),"Facture supprimee", Toast.LENGTH_SHORT).show();
                }
            });*/

            itemView.setOnClickListener(this);
        }

        public MyViewHolder LinkAdapter(FactureRecyclerAdapter adapter){
            this.adapter = adapter;
            return this;
        }

        @Override
        public void onClick(View view) {
            onListItemClick.OnListClick(getAdapterPosition());
        }
}

