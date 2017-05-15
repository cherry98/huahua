package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rose.guojiangzhibo.R;

public class AdviceActivity extends AppCompatActivity {
    private EditText advice_edit1, advice_edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        advice_edit1 = (EditText) findViewById(R.id.advice_edit1);
        advice_edit2 = (EditText) findViewById(R.id.advice_edit2);
        findViewById(R.id.adviceimg_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClick(View view) {
        String edit1 = advice_edit1.getText().toString().trim();
        String edit2 = advice_edit2.getText().toString().trim();
        if (TextUtils.isEmpty(edit1)) {
            Toast.makeText(this, "您还没有填写内容噢~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edit2)) {
            Toast.makeText(this, "请填写您的联系方式~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(edit1) && !TextUtils.isEmpty(edit2)) {
            Toast.makeText(this, "意见已提交，请等待我们稍后会与您联系~", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }
    }
}
