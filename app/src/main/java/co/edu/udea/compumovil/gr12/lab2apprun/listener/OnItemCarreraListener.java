package co.edu.udea.compumovil.gr12.lab2apprun.listener;

import android.view.View;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;

/**
 * Created by CamiGomez318 on 29/03/2016.
 */
public interface OnItemCarreraListener {

    void onItemClick(Carrera carrera, View view, int position);

    void onItemLongClick(Carrera carrera, View view, int position);
}
