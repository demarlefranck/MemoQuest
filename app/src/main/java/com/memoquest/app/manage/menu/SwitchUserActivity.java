package com.memoquest.app.manage.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.bdd.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwitchUserActivity extends Activity implements View.OnClickListener {

    private UserService userService;
    private TextView newUseText;
    private List<UserInternalBdd> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        userService = new UserService(this);

        newUseText = (TextView) this.findViewById(R.id.newUseText);
        newUseText.setOnClickListener(this);

        initActivity();
    }

    public void onClick(View v) {

        if(v.getId() == R.id.newUseText){

            Intent intent = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
            startActivity(intent);

        } else{
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....", this);
        }
    }

    private void initActivity(){
        getAllUserInternalBdd();

        if(users.isEmpty()){

            Intent intentMenu = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
            startActivity(intentMenu);

        }else if(users.size() == 1 && users.get(0).getActif()){

            Intent intentMenu = new Intent(SwitchUserActivity.this, MenuActivity.class);
            startActivity(intentMenu);

        }else {

            showUserListview();
        }
    }

    private void showUserListview(){
        final ListView listView = (ListView) findViewById(R.id.userListview);
        String[] values = getUserEmailListValues();

        final List<String> list = new ArrayList<String>();

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
            Log.e("ERROR", e.toString());
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

        Map<String, Integer> mIdMap = new HashMap<String, Integer>();

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