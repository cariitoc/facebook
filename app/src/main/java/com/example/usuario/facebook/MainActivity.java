package com.example.usuario.facebook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView welcome,nombre,email,uid;
    Button logear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email= findViewById(R.id.email);
        welcome= findViewById(R.id.welcome);
        nombre= findViewById(R.id.nombre);
        uid= findViewById(R.id.uid);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            String Dnombre = user.getDisplayName();
            String Demail = user.getEmail();
            String Duid = user.getUid();

            nombre.setText(Dnombre);
            email.setText(Demail);
            uid.setText(Duid);
        }else{
            goLoginScreen();
        }

        if(AccessToken.getCurrentAccessToken()==null){
            goLoginScreen();
        }
    }

    private void goLoginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
