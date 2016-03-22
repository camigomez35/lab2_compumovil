package co.edu.udea.compumovil.gr12.lab2apprun.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import co.edu.udea.compumovil.gr12.lab2apprun.config.DataManager;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Carrera;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;


public class UsuarioDataManager extends DataManager {
    private static UsuarioDataManager instance;

    public static final String TABLE_NAME = "usuario";
    public static final String TABLE_UXC = "usuarioporcarrera";

    public static final int
            COL_NOMBRE = 0,
            COL_CONTRASENA = 1,
            COL_CORREO = 2,
            COL_NOMBRE_USUARIO = 0,
            COL_NOMBRE_CARRERA = 1;

    public static final String[] COLUMNS = {
            "nombre",
            "contrasena",
            "correo"
    };

    public static final String[] COLUMNSUXC = {
            "nombre_usuario",
            "nombre_carrera"
    };

    private UsuarioDataManager(Context c) {
        super(c);
    }

    public static UsuarioDataManager getInstance(Context c) {
        if (instance == null) {
            instance = new UsuarioDataManager(c);
        }
        return instance;
    }

    public static void setInstance(UsuarioDataManager instance) {
        UsuarioDataManager.instance = instance;
    }

    private Usuario getComidaFromCursor(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setContrasena(cursor.getString(COL_CONTRASENA));
        usuario.setNombre(cursor.getString(COL_NOMBRE));
        usuario.setCorreo(cursor.getString(COL_CORREO));
        return usuario;
    }

    private ContentValues getContentValues(Usuario usuario) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMNS[COL_CONTRASENA], usuario.getContrasena());
        cv.put(COLUMNS[COL_NOMBRE], usuario.getNombre());
        cv.put(COLUMNS[COL_CORREO], usuario.getCorreo());
        return cv;
    }

    public Usuario update(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(TABLE_NAME,
                getContentValues(usuario),
                COLUMNS[COL_NOMBRE] + "=?",
                new String[]{String.valueOf(usuario.getNombre())}
        );
        helper.close();
        return usuario;
    }

    public void remove(String nombre) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME,
                COLUMNS[COL_NOMBRE] + "= ?",
                new String[]{nombre}
        );
        helper.close();
    }

    public Usuario getUsuarioById(String nombre) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS,
                "nombre = ?", new String[]{nombre}, null, null, COLUMNS[COL_NOMBRE]);

        if (cursor.moveToNext()) {
            return getComidaFromCursor(cursor);
        }

        cursor.close();
        helper.close();
        return null;
    }

    private ContentValues getContentValues(Usuario usuario, Carrera carrera) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMNSUXC[COL_NOMBRE_CARRERA], carrera.getNombre());
        cv.put(COLUMNSUXC[COL_NOMBRE_USUARIO], usuario.getNombre());
        return cv;
    }

    private void guardarInscripcion(Usuario usuario, Carrera carrera)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(TABLE_UXC, null, getContentValues(usuario, carrera));
        db.close();
    }


}
