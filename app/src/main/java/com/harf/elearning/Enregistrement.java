package com.harf.elearning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuth;

public class Enregistrement extends AppCompatActivity {

    TextInputLayout username,email, mdp;
    Button enregistrer;
    FirebaseAuth fAuth;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        username = findViewById(R.id.username_enregistrement);
        email = findViewById(R.id.email_enregistrement);
        enregistrer = findViewById(R.id.btn_enregistrement);
        mdp = findViewById(R.id.password_enregistrement);
        fAuth = FirebaseAuth.getInstance();
        spinner=(ProgressBar)findViewById(R.id.progressbar2);
        spinner.setVisibility(View.GONE);

        enregistrer.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                String inom = username.getEditText().getText().toString();
                String mail= email.getEditText().getText().toString();
                String pwd=mdp.getEditText().getText().toString();
                if(TextUtils.isEmpty(inom) ){
                    username.setError("Nom requis");
                    return;
                }
                if(TextUtils.isEmpty(mail)|| !checkEmail(mail)){
                    email.setError("Email requis");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    mdp.setError("Mot de passe requis");
                    return;
                }
                if(pwd.length()<=4){
                    mdp.setError("Mot de passe doit être superieur à 4");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Enregistrement.this,"Utilisateur crée",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                            Toast.makeText(Enregistrement.this,"Erreur"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public static boolean checkEmail(String email) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}
