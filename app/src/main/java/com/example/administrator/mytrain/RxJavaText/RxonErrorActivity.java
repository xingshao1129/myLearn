package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytrain.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 很多操作符可用于对Observable发射的 onError 通知做出响应或者从错误中恢复，例如，你
 * 可以：
 * 1. 吞掉这个错误，切换到一个备用的Observable继续发射数据
 * 2. 吞掉这个错误然后发射默认值
 * 3. 吞掉这个错误并立即尝试重启这个Observable
 * 4. 吞掉这个错误，在一些回退间隔后重启这个Observable
 * 这是操作符列表：
 * onErrorResumeNext( ) — 指示Observable在遇到错误时发射一个数据序列
 * onErrorReturn( ) — 指示Observable在遇到错误时发射一个特定的数据
 * onExceptionResumeNext( ) — instructs an Observable to continue emitting items after it
 * encounters an exception (but not another variety of throwable)指示Observable遇到错误
 * 时继续发射数据
 * retry( ) — 指示Observable遇到错误时重试
 * retryWhen( ) — 指示Observable遇到错误时，将错误传递给另一个Observable来决定
 * 是否要重新给订阅这个Observable
 */
public class RxonErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxon_error);
    }

    private void rxCatch() {
        Observable<String> observable = Observable.just("1", "2");

        //让Observable遇到错误时发射一个特殊的项并且正常终止。
        observable.onErrorReturn(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) throws Exception {
                return "9";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //让Observable在遇到错误时开始发射第二个Observable的数据序列。
        observable.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Throwable throwable) throws Exception {
                return Observable.just("8", "9");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        //让Observable在遇到错误时继续发射后面的数据项。
        observable.onExceptionResumeNext(new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("567");
            }
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
