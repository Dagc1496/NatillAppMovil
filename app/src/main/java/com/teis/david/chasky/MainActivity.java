package com.teis.david.chasky;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListadoNatilleras{

    private RecyclerView recyclerView;
    private NatilleraListAdapter adapter;
    private String[] names;
    private int[] ahorros;
    private int[] metas;
    private ArrayList<Natillera> natilleras = new ArrayList<>();
    private int[] colors;

    public static final String TRANSITION_FAB = "fab_transition";
    public static final String TRANSITION_INITIAL = "initial_transition";
    public static final String TRANSITION_NAME = "name_transition";
    public static final String TRANSITION_AHORRO = "ahorro_transition";
    public static final String TRANSITION_META = "meta_transition";
    public static final String TRANSITION_PROGRESSBAR = "progressbar_transition";

    private ControladorNatilleras controlador;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getResources().getStringArray(R.array.names_array);
        colors = getResources().getIntArray(R.array.initial_colors);
        ahorros = getResources().getIntArray(R.array.ahorros_array);
        metas = getResources().getIntArray(R.array.metas_array);

        controlador = new ControladorNatilleras(this);
        controlador.actualizarNatilleras();

        if (adapter == null) {
            adapter = new NatilleraListAdapter(this, natilleras);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<View, String> pair = Pair.create(v.findViewById(R.id.fab), TRANSITION_FAB);

                ActivityOptionsCompat options;
                Activity act = MainActivity.this;
                //Toast.makeText(MainActivity.this, "Boton para añadir natillera", Toast.LENGTH_SHORT).show();
                Intent transitionIntent = new Intent(act, AddActivity.class);
                act.startActivityForResult(transitionIntent, AGREGADA_NATILLERA);
            }
        });
    }

    /*
    private void initNatilleras() {
        for (int i = 0; i < 8; i++) {
            Natillera nati = new Natillera();
            nati.setId((long) i);
            nati.setName(names[i]);
            nati.setColorResource(colors[i]);
            nati.setCantAhorrada(ahorros[i]);
            nati.setAhorro(metas[i]);
            Log.i("Main",names[i]);
            //Log.i(DEBUG_TAG, "Card created with id " + card.getId() + ", name " + card.getName() + ", color " + card.getColorResource());
            natilleras.add(nati);
        }
    }
    */

    public void agregarNatilleras(ArrayList<Natillera> natilleras){
        this.natilleras.clear();
        this.natilleras.addAll(natilleras);
        this.adapter.notifyDataSetChanged();
    }

    public void iniciarProgressBar(){
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Espere por favor");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void pararProgressBar(){
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
