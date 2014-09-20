package com.memoquest.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.memoquest.service.ConnexionService;


public class MainActivity extends ActionBarActivity{
//public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.i("", "MainActivity.class: onStart()");
        super.onStart();

        ConnexionService connexionService = new ConnexionService();
        if(connexionService.isConnected(this))
            startWithConnection();
        else
            startWithoutConnection();
    }

    public void startWithConnection(){



        //verif authentification

        //si id de user
          //  BddService bddService = new BddService();
          //  bddService.reloadAllTables(this);

            Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intentMenu);
        //sinon
           // Intent intentConnexion = new Intent(MainActivity.this, ConnectionActivity.class);
           // startActivity(intentConnexion);
            //this.startActivityForResult(intentConnexion, 1000);

    }
    public void startWithoutConnection(){

        //si id de user
            Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intentMenu);
        //sinon
             //Alerte.showAlertDialog("Probleme de connexion", "Une connexion internet est requise", this);
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
