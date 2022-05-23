package com.harf.elearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Accueil extends AppCompatActivity {

    ViewFlipper v_flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        int images[] ={R.drawable.sarety,R.drawable.antananarivo,R.drawable.tanimbary};
//        v_flipper = findViewById(R.id.v_flipper);

//        for(int i=0; i<images.length;i++){
//            flipperImages(images[i]);
//        }
    }
    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
    }

//    public void news(View v)
//    {
//        startActivity(new Intent(this, Login.class));
//    }
}
