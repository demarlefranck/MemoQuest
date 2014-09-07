package com.memoquest.app.Liste;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.model.Liste;
import com.memoquest.service.ListeService;

import java.util.List;


public class ModifyListesActivity  extends Activity {

    private TextView viewListes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_listes);

        viewListes = (TextView) this.findViewById(R.id.textViewListes);

        ListeService listeService = new ListeService();

        List<Liste> listes = listeService.getListes(this);

        viewListes.setText(listes.toString());
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