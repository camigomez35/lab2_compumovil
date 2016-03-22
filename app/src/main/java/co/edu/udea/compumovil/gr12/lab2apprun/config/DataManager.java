package co.edu.udea.compumovil.gr12.lab2apprun.config;

import android.content.Context;


public class DataManager {
    protected DataBaseHelper helper;
    public DataManager(Context context) {
        helper = new DataBaseHelper(context, DataBaseHelper.DB_NAME, DataBaseHelper.DB_ACTUAL_VERSION);
    }





}
