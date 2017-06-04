package com.teis.david.chasky;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DAVID on 30/05/2017.
 */

public class NatilleraListAdapter extends RecyclerView.Adapter<NatilleraListAdapter.ViewHolder> {
    private static final String DEBUG_TAG = "Natillera Adapter";

    public Context context;
    public ArrayList<Natillera> natillerasList;

    public NatilleraListAdapter(Context context, ArrayList<Natillera> natillerasLista) {
        this.context = context;
        this.natillerasList = natillerasLista;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String name = natillerasList.get(position).getName();
        String ahorro = String.valueOf(natillerasList.get(position).getCantAhorrada());
        String meta = String.valueOf(natillerasList.get(position).getAhorro());
        int color = natillerasList.get(position).getColorResource();
        TextView initial = viewHolder.initial;
        TextView nameTextView = viewHolder.name;
        TextView ahorroTextView = viewHolder.ahorro;
        TextView metaTextView = viewHolder.meta;
        ProgressBar progressBar = viewHolder.progressBar;
        nameTextView.setText(name);
        ahorroTextView.setText(ahorro);
        metaTextView.setText(meta);
        initial.setBackgroundColor(color);
        initial.setText(Character.toString(name.charAt(0)));
        progressBar.setMax(natillerasList.get(position).getAhorro());
        progressBar.setProgress(natillerasList.get(position).getCantAhorrada());
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    public void animateCircularDelete(final View view, final int list_position) {
        int centerX = view.getWidth();
        int centerY = view.getHeight();
        int startRadius = view.getWidth();
        int endRadius = 0;
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.d(DEBUG_TAG, "onAnimationEnd for Edit adapter position " + list_position);
                Log.d(DEBUG_TAG, "onAnimationEnd for Edit natilleradId " + getItemId(list_position));

                view.setVisibility(View.INVISIBLE);
                natillerasList.remove(list_position);
                notifyItemRemoved(list_position);
            }
        });
        animation.start();
    }

    /*
    En caso de ser necesario estos 3 metodos
     */
    /*
    public void addNatillera(String name, int color) {
        Natillera natillera = new Natillera();
        natillera.setName(name);
        natillera.setColorResource(color);
        natillera.setId(getItemCount());
        natillerasList.add(natillera);
        notifyItemInserted(getItemCount());
    }

    public void updateNatillera(String name, int list_position) {
        natillerasList.get(list_position).setName(name);
        Log.d(DEBUG_TAG, "list_position is " + list_position);
        notifyItemChanged(list_position);
    }

    public void deleteNatillera(View view, int list_position) {
        animateCircularDelete(view, list_position);
    }
    */

    @Override
    public int getItemCount() {
        if (natillerasList.isEmpty()) {
            return 0;
        } else {
            return natillerasList.size();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.natillera_view_holder, viewGroup, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView initial;
        private TextView name;
        private TextView ahorro;
        private TextView meta;
        private ProgressBar progressBar;

        public ViewHolder(View v) {
            super(v);
            initial = (TextView) v.findViewById(R.id.initial);
            name = (TextView) v.findViewById(R.id.name);
            ahorro = (TextView) v.findViewById(R.id.txt_ahorrado);
            meta = (TextView) v.findViewById(R.id.txt_meta);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBarMoney);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair<View, String> p1 = Pair.create((View) initial, MainActivity.TRANSITION_INITIAL);
                    Pair<View, String> p2 = Pair.create((View) name, MainActivity.TRANSITION_NAME);
                    Pair<View, String> p3 = Pair.create((View) ahorro, MainActivity.TRANSITION_AHORRO);
                    Pair<View, String> p4 = Pair.create((View) meta, MainActivity.TRANSITION_META);
                    Pair<View, String> p5 = Pair.create((View) progressBar, MainActivity.TRANSITION_PROGRESSBAR);

                    ActivityOptionsCompat options;
                    Activity act = (AppCompatActivity) context;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, p1, p2, p3, p4, p5);

                    int requestCode = getAdapterPosition();
                    String name = natillerasList.get(requestCode).getName();

                    Log.d(DEBUG_TAG, "SampleMaterialAdapter itemView listener for Edit adapter position " + requestCode);

                    /*
                    Agregar codigo para redireccionar a pantalla que muestra detalles de la natillera
                     */
                }
            });
        }
    }
}