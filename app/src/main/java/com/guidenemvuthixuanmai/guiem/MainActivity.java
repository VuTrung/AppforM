package com.guidenemvuthixuanmai.guiem;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout mh;
    MediaPlayer song;
    ImageView imgvMai;
    public void AnhXa(){
        mh=(RelativeLayout)findViewById(R.id.manHinh);
        imgvMai=(ImageView)findViewById(R.id.imageViewmai);

    }
    public void onBackPressed(View e) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        //Background
        mh.setBackgroundResource(R.drawable.bg2);
        //Nhạc nền
        song = MediaPlayer.create(getApplicationContext(),R.raw.kisstherain);
        song.start();
        //Hiệu ứng hình
        Animation f = AnimationUtils.loadAnimation(this, R.anim.fade);
        f.reset();
        imgvMai.clearAnimation();
        imgvMai.startAnimation(f);

        imgvMai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                song.stop();
                Intent mhMusic=new Intent(getApplicationContext(),Music.class);
                startActivity(mhMusic);

            }
        });
    }
}
