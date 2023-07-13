package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ifmg.edu.bsi.progmovel.pokedex1.R;

public class EvolutionAdapter extends RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder> {

    public class EvolutionViewHolder extends RecyclerView.ViewHolder {
        TextView evoName;

        EvolutionViewHolder(View itemView){
            super(itemView);
            evoName = (TextView) itemView.findViewById(R.id.evoName);
        }
    }

    private ArrayList<String> evoNames;

    public EvolutionAdapter(ArrayList<String> listNames){
        this.evoNames = listNames;
    }

    @Override
    public EvolutionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evolucao_pokemon, parent, false);
        return new EvolutionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvolutionViewHolder holder, int position) {
        String evoName = evoNames.get(position);
        holder.evoName.setText(evoName);
    }

    @Override
    public int getItemCount() {
        return evoNames.size();
    }
}
