package com.memoquest.app.play;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;

public class SelectPlayActivity extends ActionBarActivity {

    private int listeInternalBddId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_play);

        Bundle objetbunble  = this.getIntent().getExtras();

        //On récupère les données du Bundle
        if (objetbunble != null && objetbunble.containsKey("listeInternalBddId")) {
            listeInternalBddId = this.getIntent().getIntExtra("listeInternalBddId", -1);
        }else {
            //Erreur
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);
        }


        Toast.makeText(getApplicationContext(), "Click ListItem Number " + listeInternalBddId, Toast.LENGTH_LONG).show();
    }
}
