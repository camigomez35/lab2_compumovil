package co.edu.udea.compumovil.gr12.lab2apprun;


import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnItemCarreraListener;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;

/**
 * Created by SA on 23/03/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Carrera> mDataset;
    private OnItemCarreraListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Carrera> myDataset, OnItemCarreraListener listener) {
        mDataset = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_carrera, parent, false);
        return new ViewHolder(v);
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(mDataset.get(position) != null) {
            holder.tvNombre.setText(mDataset.get(position).getNombre());
            holder.tvDistancia.setText(String.valueOf(mDataset.get(position).getDistancia()));
            holder.tvFecha.setText(mDataset.get(position).getFecha());
            holder.tvLugar.setText(mDataset.get(position).getLugar());
            Log.e("imagen", mDataset.get(position).getImagen());
            holder.ivImagen.setImageURI(Uri.parse(mDataset.get(position).getImagen()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(mDataset.get(position), holder.itemView, position);
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public String getItem(int position) {
        return mDataset.get(position).getNombre();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDistancia,tvLugar, tvFecha;
        ImageView ivImagen;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNombre = (TextView) itemView.findViewById(R.id.tv_run_nombre);
            ivImagen = (ImageView) itemView.findViewById(R.id.iv_foto);
            tvDistancia = (TextView) itemView.findViewById(R.id.tv_run_distancia);
            tvLugar= (TextView) itemView.findViewById(R.id.tv_run_lugar);
            tvFecha = (TextView) itemView.findViewById(R.id.tv_run_fecha);
        }
    }

}
