package com.example.brenda.boutique;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText Id_Ropa, Marca, Descripcion, Precio, Talla, Color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Id_Ropa = (EditText) findViewById(R.id.Id_Ropa);
        Marca = (EditText) findViewById(R.id.Marca);
        Descripcion = (EditText) findViewById(R.id.Descripcion);
        Precio = (EditText) findViewById(R.id.Precio);
        Talla = (EditText) findViewById(R.id.Talla);
        Color = (EditText) findViewById(R.id.Color);

    }

    public void alta (View V) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper (this, "Ropa", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String e_Id_Ropa = Id_Ropa.getText().toString();
        String e_Marca = Marca.getText().toString();
        String e_Descripcion =Descripcion.getText().toString();
        String e_Precio = Precio.getText().toString();
        String e_Talla = Talla.getText().toString();
        String e_Color = Color.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("Id_Ropa", e_Id_Ropa);
        registro.put("Marca", e_Marca);
        registro.put("Descripcion", e_Descripcion);
        registro.put("Precio", e_Precio);
        registro.put("Talla", e_Talla);
        registro.put("Color", e_Color);

        bd.insert("Ropa", null, registro);
        bd.close();

        Id_Ropa.setText("");
        Marca.setText("");
        Descripcion.setText("");
        Precio.setText("");
        Talla.setText("");
        Color.setText("");

        Toast.makeText(this, "se agrego una nueva Ropa", Toast.LENGTH_SHORT).show();
        Id_Ropa.requestFocus();


    }

    public void consulta (View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Ropa", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id_vet = Id_Ropa.getText().toString();
        Cursor fila = bd.rawQuery("select Marca, Descripcion, Precio, Talla, Color from Ropa where Id_Ropa=" + id_vet, null);
        if (fila.moveToFirst()) {
            Marca.setText(fila.getString(0));
            Descripcion.setText(fila.getString(1));
            Precio.setText(fila.getString(2));
            Talla.setText(fila.getString(3));
            Color.setText(fila.getString(4));
        } else {
            Toast.makeText(this, "No existe la Ropa",Toast.LENGTH_SHORT).show();
            Id_Ropa.requestFocus();
        }
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Ropa", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id_vet = Id_Ropa.getText().toString();
        int cant = bd.delete("Ropa","Id_Ropa=" + id_vet, null);
        bd.close();

        Id_Ropa.setText("");
        Marca.setText("");
        Descripcion.setText("");
        Precio.setText("");
        Talla.setText("");
        Color.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borro la Ropa",Toast.LENGTH_SHORT).show();
            Id_Ropa.requestFocus();
        } else {
            Toast.makeText(this, "No existe esta Ropa",Toast.LENGTH_SHORT).show();
            Id_Ropa.requestFocus();
        }
    }
    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Ropa", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String e_Id_Ropa = Id_Ropa.getText().toString();
        String e_Marca = Marca.getText().toString();
        String e_Descripcion = Descripcion.getText().toString();
        String e_Precio = Precio.getText().toString();
        String e_Talla = Talla.getText().toString();
        String e_Color = Color.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("Id_Ropa", e_Id_Ropa);
        registro.put("Marca", e_Marca);
        registro.put("Descripcion", e_Descripcion);
        registro.put("Precio", e_Precio);
        registro.put("Talla", e_Talla);
        registro.put("Color", e_Color);

        int cant = bd.update("Ropa", registro, "Id_Ropa=" + e_Id_Ropa, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "se modifico la Ropa",Toast.LENGTH_SHORT).show();
            Id_Ropa.requestFocus();
        } else {
            Toast.makeText(this, "No existe la Ropa",Toast.LENGTH_SHORT).show();
            Id_Ropa.requestFocus();
        }
    }

    public void limpiar (View v){
        Id_Ropa.setText("");
        Marca.setText("");
       Descripcion.setText("");
        Precio.setText("");
        Talla.setText("");
        Color.setText("");

        Id_Ropa.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
