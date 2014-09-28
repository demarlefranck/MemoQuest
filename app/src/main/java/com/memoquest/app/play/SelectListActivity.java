package com.memoquest.app.play;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.memoquest.app.R;
import com.memoquest.app.menu.MenuActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectListActivity extends Activity {

    private ListeService listeService;
    private UserService userService;
    private List<ListeInternalBdd> listes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        listeService = new ListeService(this);
        userService = new UserService(this);
        getAllListeInternalBddByUser();

        final ListView listView = (ListView) findViewById(R.id.userListview);
        String[] values = getListeTitleValues();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListeInternalBdd listeInternalBdd = listes.get(position);
                Intent intent = new Intent(SelectListActivity.this, SelectPlayActivity.class);
                intent.putExtra("listeInternalBddId", listeInternalBdd.getId());
                startActivity(intent);
            }
        });
    }

    private void getAllListeInternalBddByUser(){
        try {
            int userId = userService.getUserInternalBddActif().getId();
            listes = listeService.getListeInternalBddByUser(userId);

        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Technical Problem", this.getClass().getSimpleName() + "getAllListeInternalBddByUser(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "getAllListeInternalBddByUser(): " + e.toString(), this);
        }
    }

    private String[] getListeTitleValues(){
        List<String> titleListeList = new ArrayList<String>();

        for(ListeInternalBdd liste : listes){
            titleListeList.add(liste.getNom());
        }

        return titleListeList.toArray(new String[0]);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}