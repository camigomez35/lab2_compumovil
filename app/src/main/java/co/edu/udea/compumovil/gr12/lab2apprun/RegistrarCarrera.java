package co.edu.udea.compumovil.gr12.lab2apprun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;

/**
 * Created by SA on 23/03/2016.
 */
public class RegistrarCarrera extends Fragment implements View.OnClickListener{

    public static final int ID = 5;
    TextView tvNombre, tvDescripcion,tvDistancia,tvLugar,tvFecha,tvTelefono,tvEmail;
    Button btRegistar;
    public static RegistrarCarrera newInstance() {
        return new RegistrarCarrera();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_registrat_carrera, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNombre = (TextView) view.findViewById(R.id.tv_reg_nombre);
        tvDescripcion = (TextView) view.findViewById(R.id.tv_reg_descripcion);
        tvDistancia = (TextView) view.findViewById(R.id.tv_reg_distancia);
        tvLugar = (TextView) view.findViewById(R.id.tv_reg_lugar);
        tvFecha = (TextView) view.findViewById(R.id.tv_reg_fecha);
        tvTelefono = (TextView) view.findViewById(R.id.tv_reg_telefono);
        tvEmail = (TextView) view.findViewById(R.id.tv_reg_email);
        btRegistar = (Button) view.findViewById(R.id.bt_reg_registrar);
        btRegistar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_reg_registrar:
                Carrera carrera = new Carrera();
                carrera.setNombre(String.valueOf(tvNombre.getText()));
                carrera.setDescripcion(String.valueOf(tvDescripcion.getText()));
                carrera.setLugar(String.valueOf(tvLugar.getText()));
                carrera.setDistancia(Double.parseDouble(String.valueOf(tvDistancia.getText())));
                carrera.setEmail((String.valueOf(tvEmail.getText())));
                carrera.setTelefono(String.valueOf(tvTelefono.getText()));
                carrera.setFecha(String.valueOf(tvFecha.getText()));
                CarreraDataManager.getInstance(getContext()).insert(carrera);
        }
    }
}
