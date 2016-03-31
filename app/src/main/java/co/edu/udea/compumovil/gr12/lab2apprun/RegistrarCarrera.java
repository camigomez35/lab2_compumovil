package co.edu.udea.compumovil.gr12.lab2apprun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnFragmentInteractionListener;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.CarreraDataManager;

/**
 * Created by SA on 23/03/2016.
 */
public class RegistrarCarrera extends Fragment implements View.OnClickListener {

    public static final int ID = 5;
    private int SELECT_IMAGE = 23748;
    private int TAKE_PICTURE = 60903;
    TextView tvNombre, tvDescripcion, tvDistancia, tvLugar, tvTelefono, tvEmail;
    DatePicker tvFecha;
    Button btRegistar, btAddImage;
    ImageView ivImage;
    Uri imagen;
    private OnFragmentInteractionListener mListener;

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
        tvFecha = (DatePicker) view.findViewById(R.id.tv_reg_fecha);
        tvTelefono = (TextView) view.findViewById(R.id.tv_reg_telefono);
        tvEmail = (TextView) view.findViewById(R.id.tv_reg_email);
        btRegistar = (Button) view.findViewById(R.id.bt_reg_registrar);
        btRegistar.setOnClickListener(this);
        btAddImage = (Button) view.findViewById(R.id.bt_add_imagen);
        btAddImage.setOnClickListener(this);
        ivImage = (ImageView) view.findViewById(R.id.iv_imagen);
        mListener = (OnFragmentInteractionListener) getActivity();

    }

    @Override
    public void onClick(View v) {
        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        switch (v.getId()) {
            case R.id.bt_reg_registrar: {
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
                if(imagen == null){
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
                carrera.setImagen(getPath(imagen));

                String day = Integer.toString(tvFecha.getDayOfMonth());
                String month = Integer.toString(tvFecha.getMonth() + 1);
                String year = Integer.toString(tvFecha.getYear());

                carrera.setFecha(day + "/" + month + "/" + year);
                CarreraDataManager.getInstance(getContext()).insert(carrera);
                mListener.setFragment(Carreras.ID, null, false);
                break;
            }
            case R.id.bt_add_imagen: {
                dialogPhoto();
                break;
            }
        }

    }


    private void dialogPhoto() {
        try {
            final CharSequence[] items = {"Seleccionar de la galería", "Hacer una foto"};

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Seleccionar una foto");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent, SELECT_IMAGE);
                            break;
                        case 1:
                            startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
                            break;
                    }

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == SELECT_IMAGE)
                if (resultCode == Activity.RESULT_OK) {
                    imagen = data.getData();
                    Log.e("tomar img", "" + getPath(imagen));
                    ivImage.setVisibility(View.VISIBLE);
                    ivImage.setImageURI(imagen);
                }
            if (requestCode == TAKE_PICTURE)
                if (resultCode == Activity.RESULT_OK) {
                    imagen = data.getData();
                    Log.e("tomar foto", "" + getPath(imagen));
                }
        } catch (Exception e) {
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {android.provider.MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
