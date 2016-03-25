package co.edu.udea.compumovil.gr12.lab2apprun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.UserDataHandler;

import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

/**
 * Created by CamiGomez318 on 4/03/2016.
 */
public class Registrar extends Fragment implements View.OnClickListener{
    public static final int ID = 2;
    Button bt_registrar;
    EditText et_nombre, et_email,et_contrasena;
    private OnFragmentInteractionListener mListener;

    public static Registrar newInstance() {
        return new Registrar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registrar, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registrar:
                String nombre = String.valueOf(et_nombre.getText());
                String contrasena = String.valueOf(et_contrasena.getText());
                String email = String.valueOf(et_email.getText());

                if(nombre.isEmpty() || contrasena.isEmpty() || email.isEmpty()){
                    Toast.makeText(getContext(), R.string.falta_dato, Toast.LENGTH_LONG).show();
                    return;
                }

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setContrasena(contrasena);
                usuario.setCorreo(email);
                UsuarioDataManager.getInstance(v.getContext()).insert(usuario);

                mListener.setFragment(IniciarSesion.ID, null, false);
                break;
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_registrar = (Button)view.findViewById(R.id.registrar);
        bt_registrar.setOnClickListener(this);
        et_nombre = (EditText) view.findViewById(R.id.et_nombre);
        et_contrasena = (EditText) view.findViewById(R.id.et_contrasena);
        et_email = (EditText) view.findViewById(R.id.et_email);
    }
}
