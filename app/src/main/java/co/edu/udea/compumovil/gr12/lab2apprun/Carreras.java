package co.edu.udea.compumovil.gr12.lab2apprun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

/**
 * Created by SA on 23/03/2016.
 */
public class Carreras extends Fragment implements View.OnClickListener{

    public static final int ID = 4;
    RecyclerView rv;
    FloatingActionButton floatingActionButton;
    private RecyclerView.LayoutManager mLayoutManager
            ;
    private OnFragmentInteractionListener mListener;

    public static Carreras newInstance() {
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
        MyAdapter adapter = new MyAdapter(carreras);
        rv.setAdapter(adapter);
        mListener = (OnFragmentInteractionListener) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.agregar:
                mListener.setFragment(RegistrarCarrera.ID,null,false);
                break;
            case R.id.my_recycler_view:

        }
    }
}
