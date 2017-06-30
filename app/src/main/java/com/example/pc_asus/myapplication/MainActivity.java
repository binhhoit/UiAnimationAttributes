package com.example.pc_asus.myapplication;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.activity_main_layout_show)
    protected AnimationLayout animationLayout;

    private int count = 0;

    @Click(R.id.tv_btn_process)
    protected void process() {
        if (count % 2 == 0) {
        animationLayout.setShowOutHorizontal(true);
            count++;
        } else {
            animationLayout.setShowInHorizontal(true);
            count ++;
        }
    }

}
