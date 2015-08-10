package com.lemon.oschina.base;


import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.lemon.oschina.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        AppContext.saveDisplaySize(this);

//        if (!hasActionBar()) {
//            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        }
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
//        mActionBar = (Toolbar) findViewById(R.id.actionBar);//getSupportActionBar();
//        mInflater = getLayoutInflater();
//        if (hasActionBar()) {
//            initActionBar(mActionBar);
//        }
//        init(savedInstanceState);
//
//        IntentFilter filter = new IntentFilter(INTENT_ACTION_EXIT_APP);
//        registerReceiver(mExistReceiver, filter);

        initStatusBarColor(R.color.colorPrimaryDark);

    }

    protected void onBeforeSetContentLayout() {
    }

    protected int getLayoutId() {
        return 0;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    private void initStatusBarColor(int colorId) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 创建状态栏的管理实例
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 激活导航栏设置
//            tintManager.setNavigationBarTintEnabled(true);
            // 设置一个颜色给系统栏
            tintManager.setTintColor(getResources().getColor(colorId));
        }
    }


}
