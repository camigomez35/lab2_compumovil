package co.edu.udea.compumovil.gr12.lab2apprun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnFragmentInteractionListener;
import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnItemCarreraListener;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;

/**
 * Created by SA on 23/03/2016.
 */
public class Carreras extends Fragment implements View.OnClickListener, OnItemCarreraListener{
    public static final int ID = 4;
    RecyclerView rv;
    FloatingActionButton floatingActionButton;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;

    public static Carreras newInstance(){
        return new Carreras();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carreras, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.agregar);

        floatingActionButton.setOnClickListener(this);
        mLayoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(mLayoutManager);
        ArrayList<Carrera> carreras = CarreraDataManager.getInstance(getContext()).getCarreras();

        MyAdapter adapter = new MyAdapter(carreras, this);
        rv.setAdapter(adapter);
        rv.setOnClickListener(this);
        mListener = (OnFragmentInteractionListener) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.agregar:
                mListener.setFragment(RegistrarCarrera.ID,null,false);
                break;
            case R.id.my_recycler_view:
                Toast.makeText(getContext(), "carrera",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onItemClick(Carrera carrera, View view, int position) {
        Bundle datos = new Bundle();
        datos.putString("NOMBRE", carrera.getNombre());
        mListener.setFragment(InfoCarrera.ID, datos, true);
    }

    @Override
    public void onItemLongClick(Carrera carrera, View view, int position) {

    }
}
