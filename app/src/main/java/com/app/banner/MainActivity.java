package com.app.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.banner.entity.BannerDataInfo;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Banner banner;
    private List<BannerDataInfo> list = new ArrayList<>();
    private Spinner spinner;
    private TextView tv_category_name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        banner = findViewById(R.id.banner);
        spinner = findViewById(R.id.spinner);
        tv_category_name = findViewById(R.id.tv_category_name);
        //----------------------banner的使用------------------
        //模拟一些数据
        list.add(new BannerDataInfo(R.mipmap.img1, "标题一"));
        list.add(new BannerDataInfo(R.mipmap.img2, "标题二"));
        list.add(new BannerDataInfo(R.mipmap.img3, "标题三"));
        list.add(new BannerDataInfo(R.mipmap.img4, "标题四"));
        //设置适配器
        banner.setAdapter(new BannerImageAdapter<BannerDataInfo>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerDataInfo data, int position, int size) {
                //设置数据
                holder.imageView.setImageResource(data.getImg());
            }
        });
        //添加生命周期观察者
        banner.addBannerLifecycleObserver(this);
        //设置指示器
        banner.setIndicator(new CircleIndicator(this));
        //设置画廊效果
//        banner.setBannerGalleryEffect(10,10);
        //设置点击事件
        banner.setOnBannerListener(new OnBannerListener<BannerDataInfo>() {

            @Override
            public void OnBannerClick(BannerDataInfo data, int position) {
                Toast.makeText(MainActivity.this, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        //----------------------spinner的使用-----------
        //设置点击，选中事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //当选中当前item时
                //设置当前文字为
                String categoryName = spinner.getSelectedItem().toString();
                tv_category_name.setText(categoryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}