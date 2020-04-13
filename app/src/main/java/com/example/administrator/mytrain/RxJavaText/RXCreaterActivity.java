package com.example.administrator.mytrain.RxJavaText;

import android.icu.util.TimeUnit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.mytrain.R;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 创建操作
 * 用于创建Observable的操作符
 * 	Create		—	通过调用观察者的方法从头创建一个Observable
 * 	Defer		—	在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的 Observable
 * 	Empty/Never/Throw		—	创建行为受限的特殊Observable
 * 	From		—	将其它的对象或数据结构转换为Observable
 * 	Interval		—	创建一个定时发射整数序列的Observable
 * 	Just		—	将对象或者对象集合转换为一个会发射这些对象的Observable
 * 	Range		—	创建发射指定范围的整数序列的Observable
 * 	Repeat		—	创建重复发射特定的数据或数据序列的Observable
 * 	Start		—	创建发射一个函数的返回值的Observable
 * 	Timer		—	创建在一个指定的延迟之后发射单个数据的Observable
 */
public class RXCreaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_x_creater);
    }

    private void rxJust() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("--rxRange", Integer.toString(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.just("1", "3", "4")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("--rxRange", s);
                    }
                });
    }

    /**
     * RxJava将这个操作符实现为 range 函数，它接受两个参数，一个是范围的起始值，一个是范
     * 围的数据的数目。如果你将第二个参数设为0，将导致Observable不发射任何数据（如果设置
     * 为负数，会抛异常）。
     */
    private void rxRange() {
        Observable.range(50, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        //拿到被观察者，是否取消
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("--rxRange", Integer.toString(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * Repeat重复地发射数据。某些实现允许你重复的发射某个数据序列，还有一些允许你限制重
     * 复的次数。
     */
    private void rxRepeat() {
        Observable.range(50, 3)
                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        //拿到被观察者，是否取消
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("--rxRange", Integer.toString(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("sss");
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

    }
}
