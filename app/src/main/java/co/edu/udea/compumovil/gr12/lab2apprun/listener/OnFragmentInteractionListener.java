package co.edu.udea.compumovil.gr12.lab2apprun.listener;

import android.os.Bundle;

/**
 * Created by SA on 21/03/2016.
 */
public interface OnFragmentInteractionListener {

    void onFragmentInteraction(Bundle parametros, int accion);

    void setFragment(int fragmentId, Bundle parametros, boolean addStack);
}
