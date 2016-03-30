package co.edu.udea.compumovil.gr12.lab2apprun;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import co.edu.udea.compumovil.gr12.lab2apprun.listener.OnFragmentInteractionListener;
import co.edu.udea.compumovil.gr12.lab2apprun.model.Usuario;
import co.edu.udea.compumovil.gr12.lab2apprun.persistence.UsuarioDataManager;

public class Main2Activity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {
    public static String nombreUsuario = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }


            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            sesionIniciada(R.id.nav_perfil);
            // Add the fragment to the 'fragment_container' FrameLayout


        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.e("Prueba nombre", "..."+ nombreUsuario +"...");
            Usuario usuario = UsuarioDataManager.getInstance(this).getUsuarioById(nombreUsuario);
            if(usuario != null)
            {
                usuario.setSesion(0);
                UsuarioDataManager.getInstance(this).update(usuario);
                nombreUsuario = "";
                Toast.makeText(this, "Sesion terminada",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "No hay sesion iniciada",Toast.LENGTH_SHORT).show();
            }
        }
        sesionIniciada(id);
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        sesionIniciada(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Bundle parametros, int accion) {

    }

    @Override
    public void setFragment(int fragmentId, Bundle parametros, boolean addStack) {
        Fragment f = null;
        switch (fragmentId){
            case IniciarSesion.ID:
                f = IniciarSesion.newInstance();
                break;
            case Registrar.ID:
                f =  Registrar.newInstance();
                break;
            case Perfil.ID:
                nombreUsuario = parametros.getString("NOMBRE");
                f = Perfil.newInstance(parametros.getString("NOMBRE"));
                break;
            case Carreras.ID:
                f =Carreras.newInstance();
                break;
            case RegistrarCarrera.ID:
                f=RegistrarCarrera.newInstance();
                break;
            case Acercade.ID:
                f = Acercade.newInstance();
                break;
            case InfoCarrera.ID:
                f = InfoCarrera.newInstance();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f).commit();
    }

    public void sesionIniciada(int id)
    {
        Usuario usuario = UsuarioDataManager.getInstance(this).sesionInciada();
        if(usuario != null){
            if (id == R.id.nav_acercade) {
                setFragment(Acercade.ID, null, false);
            }else if (id == R.id.nav_carrera) {
                setFragment(Carreras.ID,null,false);
            }else if(id == R.id.nav_perfil){
                Bundle bundle = new Bundle();
                bundle.putString("NOMBRE",usuario.getNombre());
                setFragment(Perfil.ID,bundle,false);
            }
        }else if (id == R.id.nav_acercade) {
            setFragment(Acercade.ID, null, false);
        }else{
            setFragment(IniciarSesion.ID,null,false);
            Toast.makeText(this, "Por favor inicie sesion", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
