package com.app.simon.rxsample.utils;

import android.support.annotation.NonNull;

import com.app.simon.rxsample.models.BaseResponse;

import rx.Observable;
import rx.functions.Func1;

/**
 * desc: ObservableUtil
 * date: 2017/7/29
 *
 * @author xw
 */
public class ObservableUtil {

    @NonNull
    public static <T> Observable.Transformer<BaseResponse<T>, T> checkCodeForResult() {
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> baseResponseObservable) {
                return baseResponseObservable
                        .filter(new Func1<BaseResponse<T>, Boolean>() {
                            @Override
                            public Boolean call(BaseResponse<T> tBaseResponse) {
                                return tBaseResponse.isSuccess();
                            }
                        })
                        .map(new Func1<BaseResponse<T>, T>() {
                            @Override
                            public T call(BaseResponse<T> tBaseResponse) {
                                return tBaseResponse.getData();
                            }
                        });
            }
        };
    }
}
