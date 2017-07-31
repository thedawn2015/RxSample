package com.app.simon.rxsample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.simon.rxsample.models.Person;
import com.app.simon.rxsample.models.Place;
import com.app.simon.rxsample.utils.RxJavaUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_test1)
    Button buttonTest1;
    @BindView(R.id.button_test2)
    Button buttonTest2;
    @BindView(R.id.button_test3)
    Button buttonTest3;
    @BindView(R.id.button_sub1)
    Button buttonSub1;
    @BindView(R.id.button_sub2)
    Button buttonSub2;

    private int personId = 3;
    private int placeId = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        assignViews();
    }

    private void assignViews() {


    }

    @OnClick({R.id.button_test1, R.id.button_test2, R.id.button_test3, R.id.button_sub1, R.id.button_sub2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_test1:
                RxJavaUtil.method1();
                break;
            case R.id.button_test2:
                RxJavaUtil.method2();
                break;
            case R.id.button_test3:
                RxJavaUtil.method3();
                break;
            case R.id.button_sub1:
                if (RxJavaUtil.subscriberFirst != null) {
                    RxJavaUtil.subscriberFirst.onNext(new Person("lixi" + personId++, 1356));
                }
                break;
            case R.id.button_sub2:
                if (RxJavaUtil.subscriberSecond != null) {
                    RxJavaUtil.subscriberSecond.onNext(new Place(12, "ChengDu" + placeId++));
                }
                break;
        }
    }

    private <T extends View> T method(@IdRes int resId) {
        return (T) findViewById(resId);
    }
}
