package com.teis.david.chasky;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jgavi on 31/05/2017.
 */

public class ControladorNuevo {

    private String urlStr = "http://192.168.0.14/grupos";

    public ControladorNuevo(){

    }

    public void NuevaNatillera(Natillera natillera){
        new AsyncT().execute(natillera);
    }

    private class AsyncT extends AsyncTask<Natillera,Void,Void> {

        @Override
        protected Void doInBackground(Natillera... params) {
            Natillera natillera = params[0];
            try {
                URL url = new URL(urlStr); //Enter URL here
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                httpURLConnection.connect();

                JSONObject jsonObject = new JSONObject("grupo");
                jsonObject.put("nombre", natillera.getName());
                jsonObject.put("ahorro_esperado", natillera.getAhorro());
                jsonObject.put("ahorro_actual", natillera.getCantAhorrada());
                jsonObject.put("user_id", natillera.getUserId());
                jsonObject.put("cuota", natillera.getPagos());
                jsonObject.put("cuota_actual", 1);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(jsonObject.toString());
                wr.flush();
                wr.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
