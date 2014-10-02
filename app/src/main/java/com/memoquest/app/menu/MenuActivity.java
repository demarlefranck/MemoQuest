package com.memoquest.app.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.manageListe.ManageListesActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.InternalBdd.UserService;

public class MenuActivity extends Activity implements View.OnClickListener {

    private TextView manageListText;
    private TextView playText;
    private TextView changeUserText;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userService = new UserService(this);

        manageListText = (TextView) this.findViewById(R.id.manageListText);
        manageListText.setOnClickListener(this);

        changeUserText = (TextView) this.findViewById(R.id.changeUserText);
        changeUserText.setOnClickListener(this);

        playText = (TextView) this.findViewById(R.id.playTextView);
        playText.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.manageListText:
                Intent intent = new Intent(MenuActivity.this, ManageListesActivity.class);
                startActivity(intent);
            break;

            case R.id.changeUserText:
                startSwitchUserActivity();
            break;

            case R.id.playTextView:
                Alerte.showAlertDialog("Confirmation", "Fonctionnalité indisponible", this);
            break;

            default:
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....", this);
            break;
        }
    }

    private void startSwitchUserActivity(){
        try {

            userService.updateAllUserInternalBddToNoActif();

        } catch (TechnicalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startSwitchUserActivity(): " + e.toString(), this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "startSwitchUserActivity(): " + e.toString(), this);
        }
        Intent intent = new Intent(MenuActivity.this, SwitchUserActivity.class);
        startActivity(intent);
    }
}
