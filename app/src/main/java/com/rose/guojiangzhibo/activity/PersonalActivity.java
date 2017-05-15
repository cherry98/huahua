package com.rose.guojiangzhibo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rose.guojiangzhibo.R;
import com.rose.guojiangzhibo.fragment.onefragments.personal.FanFragment;
import com.rose.guojiangzhibo.fragment.onefragments.personal.FocusFragment;
import com.rose.guojiangzhibo.fragment.onefragments.personal.TrendsFragment;

import org.xutils.common.Callback;
import org.xutils.x;

import static com.rose.guojiangzhibo.customclass.RoundHeader.toRoundBitmap;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private String uid;
    private String headerPic;
    private String name;

    private TrendsFragment trendsFragment;
    private FanFragment fanFragment;
    private FocusFragment focusFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransactionInit, fragmentTransactionTrend,
            fragmentTransactionFan, fragmentTransactionFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
//        ButterKnife.bind(this);
        uid = getIntent().getExtras().getString("uid");
        headerPic = getIntent().getExtras().getString("headerPic");
        name = getIntent().getExtras().getString("name");
        Bundle bundle = new Bundle();
        bundle.putString("uid", uid);
        //开启显示第一个页面的事务
        fragmentManager = getSupportFragmentManager();
        fragmentTransactionInit = fragmentManager.beginTransaction();

        //先显示第一个界面
        if (trendsFragment != null) {
            fragmentTransactionInit.show(trendsFragment);
        } else {
            trendsFragment = new TrendsFragment();
            //activity给fragment传值
            trendsFragment.setArguments(bundle);
            fragmentTransactionInit.add(R.id.framelayout_personal, trendsFragment);
        }
        fragmentTransactionInit.commit();
        initView();
        image_perosonback.setOnClickListener(this);
        image_personbigheader.setOnClickListener(this);
        image_personattention.setOnClickListener(this);
        image_privatetalk.setOnClickListener(this);
        image_defriend.setOnClickListener(this);
    }

    private ImageView image_perosonback, image_personbigheader, image_personattention, image_privatetalk, image_defriend;
    private TextView text_personbigname, text_trendnumber, text_fannumber, text_focusnumber, text_personattention,
            text_privatetalk, text_defriend, text_personenter;

    private void initView() {
        image_perosonback = (ImageView) findViewById(R.id.image_perosonback);
        image_personbigheader = (ImageView) findViewById(R.id.image_personbigheader);
        image_personattention = (ImageView) findViewById(R.id.image_personattention);
        image_defriend = (ImageView) findViewById(R.id.image_defriend);
        image_privatetalk = (ImageView) findViewById(R.id.image_privatetalk);
        text_personbigname = (TextView) findViewById(R.id.text_personbigname);
        text_trendnumber = (TextView) findViewById(R.id.text_trendnumber);
        text_fannumber = (TextView) findViewById(R.id.text_fannumber);
        text_focusnumber = (TextView) findViewById(R.id.text_focusnumber);
        text_privatetalk = (TextView) findViewById(R.id.text_privatetalk);
        text_personattention = (TextView) findViewById(R.id.text_personattention);
        text_defriend = (TextView) findViewById(R.id.text_defriend);
        text_personenter = (TextView) findViewById(R.id.text_personenter);
        text_personbigname.setText(name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                x.image().bind(image_personbigheader, headerPic, new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
                        if (result != null) {
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                            Bitmap bitmap = bitmapDrawable.getBitmap();
                            Bitmap circleBitmap = toRoundBitmap(bitmap);
                            image_personbigheader.setImageBitmap(circleBitmap);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }
        }).start();

    }

    public void hideFragment(FragmentTransaction fragmentTransaction) {
        if (trendsFragment != null) {
            fragmentTransaction.hide(trendsFragment);
        }
        if (fanFragment != null) {
            fragmentTransaction.hide(fanFragment);
        }
        if (focusFragment != null) {
            fragmentTransaction.hide(focusFragment);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_perosonback:
                //点击回退
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.image_personbigheader:

                break;
            case R.id.text_persontrend:
                //显示当前的页面
                fragmentTransactionTrend = fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionTrend);
                if (trendsFragment != null) {
                    fragmentTransactionTrend.show(trendsFragment);
                } else {
                    trendsFragment = new TrendsFragment();
                    fragmentTransactionTrend.add(R.id.framelayout_personal, trendsFragment);
                }
                fragmentTransactionTrend.commit();
                break;
            case R.id.text_personfan:

                fragmentTransactionFan = fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionFan);
                if (fanFragment != null) {
                    fragmentTransactionFan.show(fanFragment);
                } else {
                    fanFragment = new FanFragment();
                    fragmentTransactionFan.add(R.id.framelayout_personal, fanFragment);
                }
                fragmentTransactionFan.commit();
                break;
            case R.id.text_personfocus:

                fragmentTransactionFocus = fragmentManager.beginTransaction();
                hideFragment(fragmentTransactionFocus);
                if (focusFragment != null) {
                    fragmentTransactionFocus.show(focusFragment);
                } else {
                    focusFragment = new FocusFragment();
                    fragmentTransactionFocus.add(R.id.framelayout_personal, focusFragment);
                }
                fragmentTransactionFocus.commit();
                break;
            case R.id.image_personattention:

                break;
            case R.id.text_personattention:
                break;
            case R.id.image_privatetalk:
                break;
            case R.id.text_privatetalk:
                break;
            case R.id.image_defriend:
                break;
            case R.id.text_defriend:
                break;
        }
    }
}
