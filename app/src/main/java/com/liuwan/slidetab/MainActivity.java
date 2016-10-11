package com.liuwan.slidetab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuwan.slidetab.fragment.InverterMaxFragment;
import com.liuwan.slidetab.fragment.InverterMinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwan on 2016/10/11.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout maxValue, minValue, back;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView txMaxValue, txMinValue;
    private View lineMaxValue, lineMinValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initLayout();
        MyAdapter fragmentPagerAdapter = new MyAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        changeListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.maxValue:
                viewPager.setCurrentItem(0, true);
                txMaxValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color11));
                lineMaxValue.setVisibility(View.VISIBLE);
                txMinValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color6));
                lineMinValue.setVisibility(View.INVISIBLE);
                break;

            case R.id.minValue:
                viewPager.setCurrentItem(1, true);
                txMaxValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color6));
                lineMaxValue.setVisibility(View.INVISIBLE);
                txMinValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color11));
                lineMinValue.setVisibility(View.VISIBLE);
                break;

            case R.id.back:
                MainActivity.this.finish();
                break;
        }
    }

    /**
     * 初始化控件
     */
    public void initLayout() {
        maxValue = (LinearLayout) findViewById(R.id.maxValue);
        maxValue.setOnClickListener(this);
        minValue = (LinearLayout) findViewById(R.id.minValue);
        minValue.setOnClickListener(this);
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);

        txMaxValue = (TextView) findViewById(R.id.txMaxValue);
        lineMaxValue = findViewById(R.id.lineMaxValue);
        txMinValue = (TextView) findViewById(R.id.txMinValue);
        lineMinValue = findViewById(R.id.lineMinValue);
        viewPager = (ViewPager) findViewById(R.id.inverterViewPager);

        InverterMaxFragment inverterMaxFragment = new InverterMaxFragment();
        fragmentList.add(inverterMaxFragment);
        InverterMinFragment inverterMinFragment = new InverterMinFragment();
        fragmentList.add(inverterMinFragment);
    }

    /**
     * ViewPager适配器
     */
    public class MyAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyAdapter(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

    }

    /**
     * 监听事件
     */
    public void changeListener() {
        // 监听viewPager选中页面，改变顶部标题栏
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        txMaxValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color11));
                        lineMaxValue.setVisibility(View.VISIBLE);
                        txMinValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color6));
                        lineMinValue.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        txMaxValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color6));
                        lineMaxValue.setVisibility(View.INVISIBLE);
                        txMinValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color11));
                        lineMinValue.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}