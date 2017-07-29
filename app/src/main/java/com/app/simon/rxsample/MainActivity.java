package com.app.simon.rxsample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.simon.rxsample.utils.RxJavaUtil;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button buttontest1;
    private android.widget.Button buttontest2;
    private android.widget.Button buttontest3;
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
    }

    private void assignViews() {
        this.buttontest1 = (Button) findViewById(R.id.button_test1);
        this.buttontest2 = (Button) findViewById(R.id.button_test2);
        this.buttontest3 = (Button) findViewById(R.id.button_test3);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_test1:
                        RxJavaUtil.method1();
                        break;
                    case R.id.button_test2:
                        RxJavaUtil.method2();
                        break;
                    case R.id.button_test3:
                        RxJavaUtil.method3();
                        break;
                }
            }
        };
        buttontest1.setOnClickListener(onClickListener);
        buttontest2.setOnClickListener(onClickListener);
        buttontest3.setOnClickListener(onClickListener);
    }

    private <T> T method(@IdRes int resId) {
        return (T) findViewById(resId);
    }
}
