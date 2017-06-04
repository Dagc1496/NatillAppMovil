package com.teis.david.chasky;

import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by jgavi on 31/05/2017.
 */

public interface ListadoNatilleras {
    void agregarNatilleras(ArrayList<Natillera> natilleras);
    void iniciarProgressBar();
    void pararProgressBar();
    Resources getResources();
}
