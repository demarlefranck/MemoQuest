package com.memoquest.app.menu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.memoquest.app.R;
import com.memoquest.service.InternalBdd.UserService;

public class SwitchUserActivity extends ActionBarActivity {


    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);



        UserService userService = new UserService(this);


        /*

        AFFICHGE DE :
        userService.getAllUserInternalBdd();
        POUR FAIRE UNE SELECTION
        SUIVI D'UNE SAISIE PASSWORD

        SINON
        BOUTON NEW USER ET AFFICHAGE DE:
        */

        Intent intentConnexion = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
        startActivity(intentConnexion);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.switch_user, menu);
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
