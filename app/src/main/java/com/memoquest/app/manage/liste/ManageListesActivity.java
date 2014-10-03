package com.memoquest.app.manage.liste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;

public class ManageListesActivity extends Activity implements View.OnClickListener {

    private TextView createNewListText;
    private TextView modifyListText;
    private TextView deleteListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_list);

        createNewListText = (TextView) this.findViewById(R.id.createNewListText);
        createNewListText.setOnClickListener(this);
        modifyListText = (TextView) this.findViewById(R.id.modifyListText);
        modifyListText.setOnClickListener(this);
        deleteListText = (TextView) this.findViewById(R.id.deleteListText);
        deleteListText.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.createNewListText:
                Intent intent = new Intent(ManageListesActivity.this, CreateNewListesActivity.class);
                startActivity(intent);
            break;

            case R.id.modifyListText:
                Intent intent2 = new Intent(ManageListesActivity.this, SelectOrDeleteListesActivity.class);
                intent2.putExtra("modifyMode", true);
                intent2.putExtra("deleteMode", false);
                startActivity(intent2);
            break;

            case R.id.deleteListText:
                Intent intent3 = new Intent(ManageListesActivity.this, SelectOrDeleteListesActivity.class);
                intent3.putExtra("modifyMode", false);
                intent3.putExtra("deleteMode", true);
                startActivity(intent3);
            break;

            default:
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....", this);
            break;
        }
    }
}