package co.edu.udea.compumovil.gr12.lab2apprun.config;

import android.content.Context;
import android.content.ContextWrapper;

import co.edu.udea.compumovil.gr12.lab2apprun.Main2Activity;

public class DataManager {
    protected DataBaseHelper helper;
    private Context context;
    public DataManager(Context context) {
        helper = new DataBaseHelper(context, DataBaseHelper.DB_NAME, DataBaseHelper.DB_ACTUAL_VERSION);
    }





}
