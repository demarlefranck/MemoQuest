package com.memoquest.app.manageListe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;


public class ModifyListesActivity  extends Activity {

    private TextView viewListes;

    private int listeInternalBddIdAi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_listes);

        viewListes = (TextView) this.findViewById(R.id.textViewListes);


        Bundle objetbunble  = this.getIntent().getExtras();

        //On récupère les données du Bundle
        if (objetbunble != null && objetbunble.containsKey("listeInternalBddIdAi")) {
            listeInternalBddIdAi = this.getIntent().getIntExtra("listeInternalBddIdAi", -1);
        }else {
            //Erreur
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);
        }


        Toast.makeText(getApplicationContext(), "Click ListItem Number " + listeInternalBddIdAi, Toast.LENGTH_LONG).show();


/*
        ListeService listeService = new ListeService();

        List<ListeRest> listes = listeService.getListes(this);

        viewListes.setText(listes.toString());

*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}