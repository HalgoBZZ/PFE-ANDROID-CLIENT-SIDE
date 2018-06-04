package com.halgo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TourneeAdapter extends  RecyclerView.Adapter<TourneeAdapter.MyViewHolder> {

private List<Tournee> tourneeList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name, secteur;

    public MyViewHolder(View view) {
        super(view);
        name= (TextView) view.findViewById(R.id.nameTour);
        secteur = (TextView) view.findViewById(R.id.secteur);
    }
}


    public TourneeAdapter(List<Tournee> tourneeList) {
        this.tourneeList = tourneeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tournee tournee = tourneeList.get(position);
        holder.name.setText(tournee.getName());
        holder.secteur.setText(tournee.getSecteur());
    }

    @Override
    public int getItemCount() {
        return tourneeList.size();
    }
}
