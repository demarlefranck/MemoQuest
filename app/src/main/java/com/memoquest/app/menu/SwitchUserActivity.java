package com.memoquest.app.menu;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.manageListe.ManageListesActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
Infos bulle

Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
*/


public class SwitchUserActivity extends Activity {

    private UserService userService;
    private TextView newUseText;
    private List<UserInternalBdd> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        userService = new UserService(this);
        getAllUserInternalBdd();

        if(users.size() == 0){
            Intent intentMenu = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
            startActivity(intentMenu);
        }
        else if(users.size() == 1 && users.get(0).getActif()){
            Intent intentMenu = new Intent(SwitchUserActivity.this, MenuActivity.class);
            startActivity(intentMenu);
        }
        else {
            showUserListview();
        }

        newUseText = (TextView) this.findViewById(R.id.newUseText);
        newUseText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showUserListview(){
        final ListView listView = (ListView) findViewById(R.id.userListview);
        String[] values = getUserEmailListValues();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //verif authentification
                try {
                    UserInternalBdd userInternalBdd = users.get(position);
                    userService.updateAllUserInternalBddToNoActif();
                    userInternalBdd.setActif(true);
                    userService.updateUserInternalBdd(userInternalBdd);

                if (userService.isAuthentifiate()) {

                    Intent intentMenu = new Intent(SwitchUserActivity.this, MenuActivity.class);
                    startActivity(intentMenu);
                }

            } catch (TechnicalAppException e) {
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startMenuActivity(): " + e.toString(), getApplicationContext());
            } catch (FonctionalAppException e) {
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startMenuActivity(): " + e.toString(), getApplicationContext());
            }
            }
        });
    }

    private void getAllUserInternalBdd(){
        try {

            users = userService.getAllUserInternalBdd();

        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Technical Problem", this.getClass().getSimpleName() + "getAllUserInternalBdd(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "getAllUserInternalBdd(): " + e.toString(), this);
        }
    }

    private String[] getUserEmailListValues(){
        List<String> emailList = new ArrayList<String>();

        for(UserInternalBdd user : users){
            emailList.add(user.getEmail())      ;
        }

        return emailList.toArray(new String[0]);
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