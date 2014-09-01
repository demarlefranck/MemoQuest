package com.memoquest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class CreateNewListActivity extends Activity {

    private TextView cancelText;
    private TextView addWordDefText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);

        addWordDefText = (TextView) this.findViewById(R.id.addWordDefText);
        addWordDefText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewListActivity.this, AddWordDefActivity.class);
                startActivity(intent);
            }
        });


        cancelText = (TextView) this.findViewById(R.id.cancelText);
        cancelText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onStop();
            }
        });
/*
        TextView textTitreList;
        textTitreList = (TextView)findViewById(R.id.titreListText);


        ArrayList<Item> listItem = new ArrayList<Item>();

        for (int i = 0; i != 10; i++){
            Item item = new Item();
            item.setMot("mot" + i);
            item.setDefinition("definition" + i);
            listItem.add(item);
        }

        ListeMot listeMot = new ListeMot();
        listeMot.setListItem(listItem);


        Liste liste = new Liste();
        liste.setNom("listeNom");
        liste.setListeMot(listeMot);


        CreateListDao createListDao = new CreateListDao();
        String text = new String();
        //text = createListDao.getCelsiusConversion("40");
        text = createListDao.getCelsiusConversion(liste);
        textTitreList.setText(text);
*/

    }

    @Override
    protected void onStop() {
        Log.i("","CreateNewListActivity.class: onStop()");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_new_list, menu);
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
