package com.untad.ferin.spkwpprodukjual;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.FirebaseDatabase;
import com.untad.ferin.spkwpprodukjual.data.Alternative;

public class DaftarAlternatifAdapter extends FirebaseRecyclerAdapter<Alternative, DaftarAlternatifAdapter.DaftarAlternatifViewholder>{

    public DaftarAlternatifAdapter(
            @NonNull FirebaseRecyclerOptions<Alternative> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull DaftarAlternatifViewholder holder,
                     int position, @NonNull Alternative model)
    {
        Log.d("DAA", model.getModal());
        holder.txt_nama.setText(model.getNama());
        holder.daftar_Alternatif_Modal.setText(model.getModal());
        holder.daftar_Alternatif_Etalase.setText(String.valueOf(model.getEtalase()));
        holder.daftar_Alternatif_Umur.setText(model.getUmur_simpan());
        holder.daftar_Alternatif_Laba.setText(String.valueOf(model.getLaba()));
        holder.daftar_Alternatif_Tren.setText(String.valueOf(model.getTren()));
        holder.btn_edit.setOnClickListener(view -> {
            String key = getRef(holder.getAdapterPosition()).getKey().toString();
            new MaterialAlertDialogBuilder(holder.itemView.getContext())
                    .setMessage("Apakah Anda Yakin Ingin Mengubah")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(holder.itemView.getContext(), ActivityEditAlternatif.class);
                            intent.putExtra(ActivityEditAlternatif.DATA_KEY, key);
                            intent.putExtra(ActivityEditAlternatif.DATA_NAMA, model.nama);
                            intent.putExtra(ActivityEditAlternatif.DATA_MODAL, model.modal);
                            intent.putExtra(ActivityEditAlternatif.DATA_ETALASE, model.etalase);
                            intent.putExtra(ActivityEditAlternatif.DATA_UMUR, model.umur_simpan);
                            intent.putExtra(ActivityEditAlternatif.DATA_LABA, model.laba);
                            intent.putExtra(ActivityEditAlternatif.DATA_TREN, model.tren);
                            holder.itemView.getContext().startActivity(intent);
                        }
                    }).setNegativeButton("Tidak", null)
                    .show();
        });
        holder.btn_hapus.setOnClickListener(view -> {
            String key = getRef(holder.getAdapterPosition()).getKey().toString();
            new MaterialAlertDialogBuilder(holder.itemView.getContext())
                    .setMessage("Apakah Anda Yakin Ingin Menghapus")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("alternatif").child(key).removeValue();
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                    }).setNegativeButton("Tidak", null)
                    .show();
        });
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public DaftarAlternatifViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daftar_alternatif, parent, false);
        return new DaftarAlternatifViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    static class DaftarAlternatifViewholder extends RecyclerView.ViewHolder {
        TextView txt_nama, daftar_Alternatif_Modal, daftar_Alternatif_Etalase, daftar_Alternatif_Umur, daftar_Alternatif_Laba, daftar_Alternatif_Tren;
        Button btn_edit, btn_hapus;
        public DaftarAlternatifViewholder(@NonNull View itemView)
        {
            super(itemView);
            txt_nama = itemView.findViewById(R.id.daftar_Alternatif_Nama);
            daftar_Alternatif_Modal = itemView.findViewById(R.id.daftar_Alternatif_Modal);
            daftar_Alternatif_Etalase = itemView.findViewById(R.id.daftar_Alternatif_Etalase);
            daftar_Alternatif_Umur = itemView.findViewById(R.id.daftar_Alternatif_Umur);
            daftar_Alternatif_Laba = itemView.findViewById(R.id.daftar_Alternatif_Laba);
            daftar_Alternatif_Tren = itemView.findViewById(R.id.daftar_Alternatif_Tren);
            btn_edit = itemView.findViewById(R.id.btn_edit_alternatif);
            btn_hapus = itemView.findViewById(R.id.btn_hapus_alternatif);
        }
    }

}
