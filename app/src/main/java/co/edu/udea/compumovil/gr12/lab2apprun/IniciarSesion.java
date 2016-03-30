package co.edu.udea.compumovil.gr12.lab2apprun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnFragmentInteractionListener;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

/**
 * Created by CamiGomez318 on 4/03/2016.
 */
public class IniciarSesion extends Fragment implements View.OnClickListener{
    public static final int ID = 1 ;
    Button bt_registrar,bt_sesion;
    EditText et_user, et_pass;
    private OnFragmentInteractionListener mListener;

    public static IniciarSesion newInstance(){
        return new IniciarSesion();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iniciar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_registrar = (Button)view.findViewById(R.id.bt_registrar);
        bt_registrar.setOnClickListener(this);
        bt_sesion = (Button)view.findViewById(R.id.sesion);
        bt_sesion.setOnClickListener(this);
        et_pass =(EditText) view.findViewById(R.id.password);
        et_user = (EditText) view.findViewById(R.id.user);
        mListener = (OnFragmentInteractionListener) getActivity();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_registrar:
                mListener.setFragment(Registrar.ID,null,false);
                break;
            case R.id.sesion:
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();

                if(pass.isEmpty()){
                    Toast.makeText(getContext(), R.string.falta_dato, Toast.LENGTH_LONG).show();
                    return;
                }
                if(user.isEmpty()){
                    Toast.makeText(getContext(), R.string.falta_dato, Toast.LENGTH_LONG).show();
                    return;
                }

                Usuario usuarioById = UsuarioDataManager.getInstance(getContext()).getUsuarioById(user);

                if(usuarioById!= null){
                    if(pass.equals(usuarioById.getContrasena()))
                    {
                        usuarioById.setSesion(1);
                        UsuarioDataManager.getInstance(getContext()).update(usuarioById);
                        Bundle bundle = new Bundle();
                        bundle.putString("NOMBRE",usuarioById.getNombre());
                        mListener.setFragment(Perfil.ID,bundle,false);
                    }
                    else{
                        Toast.makeText(getContext(), "Error en contraseña",Toast.LENGTH_LONG).show();
                        return;
                    }
                }else{
                    Toast.makeText(getContext(), "El usuario no existe",Toast.LENGTH_LONG).show();
                    return;
                }
        }
    }
}
