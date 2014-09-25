package com.memoquest.app.manageListe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.memoquest.app.R;

public class ManageListesActivity extends Activity {

    private TextView createNewListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_list);

        createNewListText = (TextView) this.findViewById(R.id.createNewListText);
        createNewListText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ManageListesActivity.this, CreateNewListesActivity.class);
                startActivity(intent);
            }
        });
    }
}
