package com.app.simon.rxsample.utils;

import android.util.Log;

import com.app.simon.rxsample.models.BaseResponse;
import com.app.simon.rxsample.models.Person;
import com.app.simon.rxsample.models.Place;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * desc: RxJavaUtil
 * date: 2017/7/29
 *
 * @author xw
 */
public class RxJavaUtil {
    /** TAG */
    public static final String TAG = RxJavaUtil.class.getSimpleName();

    private static BaseResponse<Person> baseResponse;
    public static Subscriber<? super Person> subscriberFirst;
    public static Subscriber<? super Place> subscriberSecond;

    /** create */
    public static void method1() {
        initData();
        Observable
                .create(new Observable.OnSubscribe<BaseResponse<Person>>() {
                    @Override
                    public void call(Subscriber<? super BaseResponse<Person>> subscriber) {
                        if (baseResponse != null) {
                            subscriber.onNext(baseResponse);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(null);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ObservableUtil.<Person>checkCodeForResult())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(Person person) {
                        Log.i(TAG, "onNext: " + person.getName());
                    }
                });
    }

    /** create&flatMap */
    public static void method2() {
        initData();
        Observable
                .create(new Observable.OnSubscribe<BaseResponse<Person>>() {
                    @Override
                    public void call(Subscriber<? super BaseResponse<Person>> subscriber) {
                        if (baseResponse != null) {
                            subscriber.onNext(baseResponse);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(null);
                        }
                    }
                })
                .flatMap(new Func1<BaseResponse<Person>, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(final BaseResponse<Person> personBaseResponse) {
                        return Observable
                                .create(new Observable.OnSubscribe<Person>() {
                                    @Override
                                    public void call(Subscriber<? super Person> subscriber) {
                                        if (personBaseResponse != null) {
                                            subscriber.onNext(personBaseResponse.getData());
                                            subscriber.onCompleted();
                                        } else {
                                            subscriber.onError(null);
                                        }
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(Person person) {
                        Log.i(TAG, "onNext: " + person.getName());
                    }
                });
    }


    /** create&comp */
    public static void method3() {
        initData();
        Observable<Person> personObservable = Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                RxJavaUtil.subscriberFirst = subscriber;
            }
        });
        Observable<Place> placeObservable = Observable.create(new Observable.OnSubscribe<Place>() {
            @Override
            public void call(Subscriber<? super Place> subscriber) {
                RxJavaUtil.subscriberSecond = subscriber;

            }
        });
        Observable
                .combineLatest(personObservable, placeObservable, new Func2<Person, Place, String[]>() {
                    @Override
                    public String[] call(Person person, Place place) {
                        return new String[]{person.getName(), place.getCity()};
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String[]>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String[] o) {
                        Log.i(TAG, "onNext: " + o[0] + ";" + o[1]);
                    }
                })

        ;
    }


    private static void initData() {
        baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("test message");
        baseResponse.setData(new Person("赵云", 1582131241));
    }
}
