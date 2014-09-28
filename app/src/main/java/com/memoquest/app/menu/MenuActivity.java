package com.memoquest.app.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.manageListe.ManageListesActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.InternalBdd.UserService;


public class MenuActivity extends Activity {

    private TextView manageListText;
    private TextView changeUserText;
    private UserService userService;
    //  private ImageButton playImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userService = new UserService(this);

        manageListText = (TextView) this.findViewById(R.id.manageListText);
        manageListText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ManageListesActivity.class);
                startActivity(intent);
            }
        });

        changeUserText = (TextView) this.findViewById(R.id.changeUserText);
        changeUserText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSwitchUserActivity();
            }
        });




        /*
        playImageButton = (ImageButton) this.findViewById(R.id.imageButtonPlay);

        playImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ManageListesActivity.class);
                startActivity(intent);
            }
        });
*/
    }


    private void startSwitchUserActivity(){
 /*       try {

            userService.updateAllUserInternalBddToNoActive();

        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startSwitchUserActivity(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startSwitchUserActivity(): " + e.toString(), this);
        }
        Intent intent = new Intent(MenuActivity.this, SwitchUserActivity.class);
        startActivity(intent);
*/    }
}
