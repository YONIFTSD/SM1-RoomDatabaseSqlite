package com.example.smroomdatabasesqlite.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smroomdatabasesqlite.R;
import com.example.smroomdatabasesqlite.roomdatabase.Continent;

import java.util.List;

public class ContinentListAdapter  extends RecyclerView.Adapter<ContinentListAdapter.ContinentViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Continent> continents;

    public ContinentListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setContinents(List<Continent> continents) {
        this.continents = continents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContinentListAdapter.ContinentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.componente_continente_row, parent, false);
        return new ContinentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContinentListAdapter.ContinentViewHolder holder, int position) {
        holder.setData(continents.get(position));
    }

    @Override
    public int getItemCount() {
        if(continents != null){
            return continents.size();
        }else{
            return 0;
        }
    }

    static class ContinentViewHolder extends RecyclerView.ViewHolder {

        private final TextView continent_name;

        ContinentViewHolder(@NonNull View itemView) {
            super(itemView);
            continent_name = itemView.findViewById(R.id.continent_name);
        }

        private void setData(Continent continent) {
            continent_name.setText(continent.getName());
        }
    }
}
