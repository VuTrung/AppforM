package com.guidenemvuthixuanmai.guiem;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Music extends AppCompatActivity {
    TextView txtvLoiNhan;
    ListView lvBaiHat;
    RelativeLayout mh;
    MediaPlayer song;
    ArrayList<String> mangTenBH,mangLoiNhan;
    ArrayList<Integer> mangMp3;

    public void AnhXa(){
        txtvLoiNhan=(TextView)findViewById(R.id.textViewLoiNhan);
        lvBaiHat=(ListView)findViewById(R.id.listViewBaiHat);
        mh=(RelativeLayout)findViewById(R.id.manHinh);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        //Anh xa
        AnhXa();

        //Hình nền
        mh.setBackgroundResource(R.drawable.bg3);
        //Play nhạc nền
        song = MediaPlayer.create(getApplicationContext(), R.raw.may);
        song.start();
        //Lời nhắn
        txtvLoiNhan.setText("Nghe nhạc vui vẻ em nhé!!!");
        TaoAnimation();
        //Tạo mảng
        TaoMang();
        //ListView

        ArrayAdapter adapter=new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1,mangTenBH
        );
        lvBaiHat.setAdapter(adapter);
        //ListView OnItemClick
        lvBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                song.stop();
                song = MediaPlayer.create(getApplicationContext(), mangMp3.get(position));
                song.start();
                txtvLoiNhan.setText(mangLoiNhan.get(position));
                TaoAnimation();

            }
        });
        txtvLoiNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.stop();
                Intent mhMain =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mhMain);
            }
        });
    }
        //Zoom Lời nhắn
    public void TaoAnimation(){
        Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        zoom.reset();
        txtvLoiNhan.clearAnimation();
        txtvLoiNhan.startAnimation(zoom);
    }
    public void TaoMang() {
        mangTenBH = new ArrayList<String>();
        mangLoiNhan = new ArrayList<String>();
        mangMp3 = new ArrayList<Integer>();

        mangMp3.add(R.raw.duongmotchieu);
        mangTenBH.add("Đường một chiều");
        mangLoiNhan.add("Bài này đi thi hát là có giải rồi, tiếc quá!");

        mangMp3.add(R.raw.noiayngondoitinhyeu);
        mangTenBH.add("Nơi ấy ngọn đồi tình yêu");
        mangLoiNhan.add("Bài này cũ mà hay nè, nhớ hồi ấy em cũng cuồng bài này lắm thì phải");

        mangMp3.add(R.raw.songfromsecretgarden);
        mangTenBH.add("Song from secret garden");
        mangLoiNhan.add("Nghe để lắng lòng lại đôi chút. Ah mà nghe đừng có xúc động quá nhé");

        mangMp3.add(R.raw.thislove);
        mangTenBH.add("This love");
        mangLoiNhan.add("Phim có nam chính đẹp trai nhỉ!?");

        mangMp3.add(R.raw.tunhienbuon);
        mangTenBH.add("Tự nhiên buồn");
        mangLoiNhan.add("Có thời gian cứ thấy em mở bài này suốt, chắc tự nhiên buồn thật!");
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            song.stop();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}