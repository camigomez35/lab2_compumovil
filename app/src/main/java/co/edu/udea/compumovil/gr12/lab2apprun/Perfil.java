package co.edu.udea.compumovil.gr12.lab2apprun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

/**
 * Created by SA on 22/03/2016.
 */
public class Perfil extends Fragment implements View.OnClickListener{
    public static final int ID = 3;
    static String nombreUsuario;
    TextView tv_nombre, tv_correo;

    public static Perfil newInstance(String nombre) {
        nombreUsuario = nombre;
        return new Perfil();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_nombre = (TextView) view.findViewById(R.id.tv_nombre);
        tv_correo = (TextView) view.findViewById(R.id.tv_correo);
        Usuario usuario = UsuarioDataManager.getInstance(getContext()).getUsuarioById(nombreUsuario);
        tv_correo.setText(usuario.getCorreo());
        tv_nombre.setText(usuario.getNombre());
    }

    @Override
    public void onClick(View v) {

    }
}
