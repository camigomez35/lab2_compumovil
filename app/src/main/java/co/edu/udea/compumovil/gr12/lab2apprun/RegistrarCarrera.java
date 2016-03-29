package co.edu.udea.compumovil.gr12.lab2apprun;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;

/**
 * Created by SA on 23/03/2016.
 */
public class RegistrarCarrera extends Fragment implements View.OnClickListener{

    public static final int ID = 5;
    TextView tvNombre, tvDescripcion,tvDistancia,tvLugar,tvTelefono,tvEmail;
    DatePicker tvFecha;
    Button btRegistar;
    private OnFragmentInteractionListener mListener;

    public static RegistrarCarrera newInstance(){
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
        tvFecha = (DatePicker) view.findViewById(R.id.tv_reg_fecha);
        tvTelefono = (TextView) view.findViewById(R.id.tv_reg_telefono);
        tvEmail = (TextView) view.findViewById(R.id.tv_reg_email);
        btRegistar = (Button) view.findViewById(R.id.bt_reg_registrar);
        btRegistar.setOnClickListener(this);
        mListener = (OnFragmentInteractionListener) getActivity();

    }

    @Override
    public void onClick(View v) {
        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        switch (v.getId()) {
            case R.id.bt_reg_registrar:
                if (tvNombre.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                if (tvDescripcion.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                if (tvDistancia.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                if (tvLugar.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                if (tvTelefono.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                if (tvEmail.getText().toString().isEmpty()) {
                    toast = Toast.makeText(context, R.string.falta_dato, duration);
                    toast.show();
                    return;
                }
                Carrera carrera = new Carrera();
                carrera.setNombre(String.valueOf(tvNombre.getText()));
                carrera.setDescripcion(String.valueOf(tvDescripcion.getText()));
                carrera.setLugar(String.valueOf(tvLugar.getText()));
                carrera.setDistancia(Double.parseDouble(String.valueOf(tvDistancia.getText())));
                carrera.setEmail((String.valueOf(tvEmail.getText())));
                carrera.setTelefono(String.valueOf(tvTelefono.getText()));

                String day = Integer.toString(tvFecha.getDayOfMonth());
                String month = Integer.toString(tvFecha.getMonth() + 1);
                String year = Integer.toString(tvFecha.getYear());

                carrera.setFecha(day + "/" + month + "/" + year);
                CarreraDataManager.getInstance(getContext()).insert(carrera);
                break;
        }
        mListener.setFragment(Carreras.ID, null, false);
    }
}
