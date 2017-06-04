package com.teis.david.chasky;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by jgavi on 31/05/2017.
 */

public class ControladorNatilleras {

    private ListadoNatilleras listadoNatilleras;
    private String url="http://192.168.0.14:3000/users/";
    private ArrayList<Natillera> listaNatilleras;


    public ControladorNatilleras(ListadoNatilleras listadoNatilleras){
        this.listadoNatilleras = listadoNatilleras;
        this.url += Usuario.solicitarUsuario().getId() + "/grupos.json";
    }

    public void actualizarNatilleras(){
        new GetNatilleras().execute();
    }

    private class GetNatilleras extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            listadoNatilleras.iniciarProgressBar();
            listaNatilleras = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray natilleras = new JSONArray(jsonStr);
                    // looping through All Contacts
                    for (int i = 0; i < natilleras.length(); i++) {
                        JSONObject c = natilleras.getJSONObject(i);
                        int id = c.getInt("id");
                        String nombre = c.getString("nombre");
                        int ahorroEsperado = c.getInt("ahorro_esperado");
                        int ahorroActual = c.getInt("ahorro_actual");
                        int cuota = c.getInt("cuota");
                        int cuota_actual = c.getInt("cuota_actual");
                        int[] colores = listadoNatilleras.getResources().getIntArray(R.array.initial_colors);
                        int indiceColor = (int) (Math.random() * colores.length);
                        Natillera natillera = new Natillera(ahorroEsperado, cuota, ahorroActual, nombre, id, colores[indiceColor], 1);
                        listaNatilleras.add(natillera);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            listadoNatilleras.pararProgressBar();
            listadoNatilleras.agregarNatilleras(listaNatilleras);
        }
    }
}
