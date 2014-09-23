package com.memoquest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.memoquest.app.menu.ConnectionActivity;
import com.memoquest.app.menu.MenuActivity;
import com.memoquest.app.menu.SwitchUserActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.service.Synchro.ManagerSynchroService;


public class MainActivity extends Activity {
//public class MainActivity extends Activity {


    private ConnexionService connexionService;
    private UserService userService;
    private ManagerSynchroService managerSynchroService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        Log.i("", "MainActivity.class: onStart()");
        super.onStart();

        connexionService = new ConnexionService(this);
        userService = new UserService(this);
        managerSynchroService = new ManagerSynchroService();
/*
        if(connexionService.isConnected(this))
            startWithConnection();
        else
            startWithoutConnection();
*/

        Intent intentConnexion = new Intent(MainActivity.this, SwitchUserActivity.class);
        startActivity(intentConnexion);
    }

    public void startWithConnection(){

       try {
            //verif authentification
           if(userService.isAuthentifiate()){

                //lancement des synchro donnees
                managerSynchroService.updateAllData(this);

                Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intentMenu);
           }
            else{
                Intent intentConnexion = new Intent(MainActivity.this, SwitchUserActivity.class);
                startActivity(intentConnexion);
            }
        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startWithConnection(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startWithConnection(): " + e.toString(), this);
        }

        //this.startActivityForResult(intentConnexion, 1000);

    }
    public void startWithoutConnection() {

        try {
            if (userService.isAuthentifiate()) {
                Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intentMenu);
            }
            else {
                Alerte.showAlertDialog("Probleme de connexion", "Une connexion internet est requise", this);
            }
        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startWithoutConnection(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startWithoutConnection(): " + e.toString(), this);
        }
    }

    @Override
    protected void onStop() {
        Log.i("","MainActivity.class: onStop()");
        super.onStop();
    }


    @Override
    protected void onPause() {
        Log.i("","MainActivity.class: onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("","MainActivity.class: onResume()");
        super.onResume();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
