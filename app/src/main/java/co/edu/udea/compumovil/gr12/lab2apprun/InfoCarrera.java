package co.edu.udea.compumovil.gr12.lab2apprun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

/**
 * Created by CamiGomez318 on 29/03/2016.
 */
public class InfoCarrera extends Fragment {
    public static final int ID = 7;

    public TextView nombreInfo, descripccionInfo, distanciaInfo, lugarInfo, fechaInfo, telefonoInfo, emailInfo;

    public static InfoCarrera newInstance() {return new InfoCarrera();}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_carrera, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombreInfo = (TextView)view.findViewById(R.id.nombreInfo);
        descripccionInfo = (TextView)view.findViewById(R.id.descripcionInfo);
        distanciaInfo = (TextView)view.findViewById(R.id.distanciaInfo);
        lugarInfo = (TextView)view.findViewById(R.id.lugarInfo);
        fechaInfo = (TextView)view.findViewById(R.id.fechaInfo);
        emailInfo = (TextView)view.findViewById(R.id.emailInfo);
        telefonoInfo = (TextView)view.findViewById(R.id.telefonoInfo);

        //No se que mandar como parametro!!!!
        Carrera carrera = CarreraDataManager.getInstance(getContext()).getCarreraById("copa");

        nombreInfo.setText(carrera.getNombre());
        descripccionInfo.setText(carrera.getDescripcion());
        distanciaInfo.setText(String.valueOf(carrera.getDistancia()));
        lugarInfo.setText(carrera.getLugar());
        fechaInfo.setText(carrera.getFecha());
        emailInfo.setText(carrera.getEmail());
        telefonoInfo.setText(carrera.getTelefono());

    }

}
