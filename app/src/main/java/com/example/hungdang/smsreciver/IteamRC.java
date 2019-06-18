package com.example.hungdang.smsreciver;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IteamRC extends AppCompatActivity {

    TextView tvTinNhan,tvNoiDung;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iteam_rc_sms);
        tvTinNhan = findViewById(R.id.tv_item_tinnhan);
        tvNoiDung = findViewById(R.id.tv_item_noidung);

        tvTinNhan.setText(getIntent().getStringExtra("tinnhan"));
        tvNoiDung.setText(getIntent().getStringExtra("noidung"));
    }
}
