package com.untad.ferin.spkwpprodukjual;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.untad.ferin.spkwpprodukjual.data.Result;

import java.util.List;

public class DaftarRankingAdapter extends RecyclerView.Adapter<DaftarRankingAdapter.ViewHolder>{

    private List<Result> results;

    public DaftarRankingAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public DaftarRankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.daftar_ranking, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarRankingAdapter.ViewHolder holder, int position) {
        final Result item = results.get(position);
        holder.txtNama.setText(position + ". Nama : " + item.data.nama);
        holder.txtValue.setText("Vektor V : " + item.value);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNama, txtValue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtNama = itemView.findViewById(R.id.txt_rank_name);
            this.txtValue = itemView.findViewById(R.id.txt_rank_value);
        }
    }
}
