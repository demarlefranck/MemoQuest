package com.memoquest.app.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.ConnexionService;


public class ConnectionActivity extends Activity {

    private EditText loginText;
    private EditText passwordText;
    private TextView connexionText;
    private TextView signinText;
    private TextView passwordForbidText;
    private ConnexionService connexionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        loginText = (EditText) this.findViewById(R.id.loginText);
        passwordText = (EditText) this.findViewById(R.id.passwordText);
        connexionText = (TextView) this.findViewById(R.id.connexionText);
        signinText = (TextView) this.findViewById(R.id.signinText);
        passwordForbidText = (TextView) this.findViewById(R.id.passwordForbidText);
        connexionService = new ConnexionService(this);

        connexionText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(isAuthentifiate(v)){
                Intent intent = new Intent(ConnectionActivity.this, MenuActivity.class);
                startActivity(intent);
            }
            }
        });
    }

    private Boolean isAuthentifiate(View v) {

        ConnexionService connexionService = new ConnexionService(this);

        String loginTextStr = String.valueOf(loginText.getText());
        String passwordTextStr = String.valueOf(passwordText.getText());

        if (loginTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir votre identifiant", this);
            return false;
        }
        else if (passwordTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir votre password", this);
            return false;
        }
        else {
            try {
                if (connexionService.isAuthentifiateByServeur(loginTextStr, passwordTextStr) == false) {
                    Alerte.showAlertDialog("erreur d'authentification", "L'authentification a échouée", this);
                    return false;
                }
            } catch (TechnicalAppException e) {
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "isAuthentifiate(): " + e.toString(), this);
            } catch (FonctionalAppException e) {
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "isAuthentifiate(): " + e.toString(), this);
            }
        }
        return true;
    }
}
