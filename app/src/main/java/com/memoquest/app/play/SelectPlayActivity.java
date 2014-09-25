package com.memoquest.app.play;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;

public class SelectPlayActivity extends ActionBarActivity {

    private int listeInternalBddIdAi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_play);

        Bundle objetbunble  = this.getIntent().getExtras();

        //On récupère les données du Bundle
        if (objetbunble != null && objetbunble.containsKey("listeInternalBddIdAi")) {
            listeInternalBddIdAi = this.getIntent().getIntExtra("listeInternalBddIdAi", -1);
        }else {
            //Erreur
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);
        }


        Toast.makeText(getApplicationContext(), "Click ListItem Number " + listeInternalBddIdAi, Toast.LENGTH_LONG).show();
    }
}
