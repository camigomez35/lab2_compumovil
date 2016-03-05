package co.edu.udea.compumovil.gr12.lab2apprun;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_ir_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_ir_registro = (Button) findViewById(R.id.registrar);
        bt_ir_registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrar:
                Intent intent;
                intent = new Intent(this, Registrar.class);
                startActivity(intent);
                break;


        }
    }
}
