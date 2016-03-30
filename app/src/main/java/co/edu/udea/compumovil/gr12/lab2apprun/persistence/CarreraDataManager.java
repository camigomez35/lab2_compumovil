package co.edu.udea.compumovil.gr12.lab2apprun.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr12.lab2apprun.config.DataManager;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;


public class CarreraDataManager extends DataManager {
    private static CarreraDataManager instance;

    public static final String TABLE_NAME = "carrera";

    public static final int
            COL_NOMBRE = 0,
            COL_DESCRIPCION = 1,
            COL_DISTANCIA = 2,
            COL_LUGAR = 3,
            COL_FECHA = 4,
            COL_TELEFONO= 5,
            COL_EMAIL = 6;

    public static final String[] COLUMNS = {
            "nombre",
            "descripcion",
            "distancia" ,
            "lugar" ,
            "fecha" ,
            "telefono" ,
            "email"
    };

    private CarreraDataManager(Context c) {
        super(c);
    }

    public static CarreraDataManager getInstance(Context c) {
        if (instance == null) {
            instance = new CarreraDataManager(c);
        }
        return instance;
    }

    public static void setInstance(CarreraDataManager instance) {
        CarreraDataManager.instance = instance;
    }

    private Carrera getCarreraFromCursor(Cursor cursor) {
        Carrera carrera = new Carrera();
        carrera.setNombre(cursor.getString(COL_NOMBRE));
        carrera.setDescripcion(cursor.getString(COL_DESCRIPCION));
        carrera.setDistancia(cursor.getDouble(COL_DISTANCIA));
        carrera.setEmail(cursor.getString(COL_EMAIL));
        carrera.setFecha(cursor.getString(COL_FECHA));
        carrera.setLugar(cursor.getString(COL_LUGAR));
        carrera.setTelefono(cursor.getString(COL_TELEFONO));
        return carrera;
    }

    private ContentValues getContentValues(Carrera carrera) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMNS[COL_DESCRIPCION], carrera.getDescripcion());
        cv.put(COLUMNS[COL_NOMBRE], carrera.getNombre());
        cv.put(COLUMNS[COL_DISTANCIA], carrera.getDistancia());
        cv.put(COLUMNS[COL_EMAIL], carrera.getEmail());
        cv.put(COLUMNS[COL_FECHA], carrera.getFecha());
        cv.put(COLUMNS[COL_LUGAR], carrera.getLugar());
        cv.put(COLUMNS[COL_TELEFONO], carrera.getTelefono());
        return cv;
    }

    public Carrera update(Carrera carrera) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(TABLE_NAME,
                getContentValues(carrera),
                COLUMNS[COL_NOMBRE] + "=?",
                new String[]{String.valueOf(carrera.getNombre())}
        );
        helper.close();
        return carrera;
    }

    public void remove(String nombre) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME,
                COLUMNS[COL_NOMBRE] + "= ?",
                new String[]{nombre}
        );
        helper.close();
    }

    public Carrera getCarreraById(String nombre) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                "nombre = ?", new String[]{nombre}, null, null, COLUMNS[COL_NOMBRE]);

        if (cursor.moveToNext()) {
            return getCarreraFromCursor(cursor);
        }

        cursor.close();
        helper.close();
        return null;
    }

    public ArrayList<Carrera> getCarreras() {
        ArrayList<Carrera> carreras = new ArrayList<>();
        int i = 0;
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                "", new String[]{}, null, null, COLUMNS[COL_NOMBRE]);

        while (cursor.moveToNext()) {
            Log.e("carrera", "" + i);
            carreras.add(getCarreraFromCursor(cursor));
            i++;
        }
        cursor.close();
        helper.close();
        return carreras;
    }

    public void insert(Carrera carrera)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(TABLE_NAME, COLUMNS[COL_NOMBRE] + ","
                + COLUMNS[COL_DISTANCIA] + ","
                + COLUMNS[COL_DESCRIPCION] + ","
                + COLUMNS[COL_LUGAR] + ","
                + COLUMNS[COL_FECHA] + ","
                + COLUMNS[COL_TELEFONO] + "," +
                COLUMNS[COL_EMAIL], getContentValues(carrera));
        db.close();
        helper.close();
    }

}
