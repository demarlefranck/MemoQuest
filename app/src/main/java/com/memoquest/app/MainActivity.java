package com.memoquest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.memoquest.app.menu.MenuActivity;
import com.memoquest.app.menu.SwitchUserActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.service.Synchro.ManagerSynchroService;

public class MainActivity extends Activity {

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

        if(connexionService.isConnected(this))
            startWithConnection();
        else
            startWithoutConnection();
    }

    public void startWithConnection(){


        Log.d("DEBUG", "MainActivity.class: startWithConnection()");

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
    }

    public void startWithoutConnection() {


        Log.d("DEBUG", "MainActivity.class: startWithoutConnection()");

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
}
