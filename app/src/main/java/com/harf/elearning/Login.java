package com.harf.elearning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
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

public class Login extends AppCompatActivity {

    TextInputLayout mail, mdp;
    FirebaseAuth fAuth;
    Button btLogin;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btn_login);
        mail = findViewById(R.id.email);
        mdp = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        spinner=(ProgressBar)findViewById(R.id.progressbar);
        spinner.setVisibility(View.GONE);

        createNotification();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                String email= mail.getEditText().getText().toString();
                String pwd=mdp.getEditText().getText().toString();

                if(TextUtils.isEmpty(email)|| !checkEmail(email)){
                    mail.setError("Email requis");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    mdp.setError("Mot de passe requise");
                    return;
                }
                if(pwd.length()<=4){
                    mdp.setError("Mot de passe doit être superieur a 4");
                    return;
                }
                //Authenticate User
                fAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
//                        if(task.isSuccessful()){
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,"E-learning");
                            builder.setContentTitle("E-learning");
                            builder.setContentText("Bienvenue sur le plateforme de cours en ligne e-learning");
                            builder.setSmallIcon(R.drawable.icon_notification);
                            builder.setAutoCancel(true);
                            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Login.this);
                            managerCompat.notify(1,builder.build());
//                            Toast.makeText(Login.this,"connexion avec succes",Toast.LENGTH_SHORT).show();
//                            createNotification();
//
//                        }else{
//                            spinner.setVisibility(View.VISIBLE);
//                            Toast.makeText(Login.this," "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                        }
                    }
                });

            }
        });

    }

    private void createNotification()  {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    "CHANNEL_ID",
                    "E-learning",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            System.out.print(" tonga ato"+ manager.getImportance());
            manager.createNotificationChannel(channel1);
        }
    }

    public void VersEnregistrement(View v)
    {
        startActivity(new Intent(this,Enregistrement.class));
        System.out.println("wawa");
    }

    public static boolean checkEmail(String email) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            }
        }

        return false;



    }
}
