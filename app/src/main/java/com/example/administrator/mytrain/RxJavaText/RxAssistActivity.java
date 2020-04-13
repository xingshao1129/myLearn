package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytrain.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 这个页面列出了很多用于Observable的辅助操作符
 * materialize( ) — 将Observable转换成一个通知列表convert an Observable into a list
 * of Notifications
 * dematerialize( ) — 将上面的结果逆转回一个Observable
 * timestamp( ) — 给Observable发射的每个数据项添加一个时间戳
 * serialize( ) — 强制Observable按次序发射数据并且要求功能是完好的
 * cache( ) — 记住Observable发射的数据序列并发射相同的数据序列给后续的订阅者
 * observeOn( ) — 指定观察者观察Observable的调度器
 * subscribeOn( ) — 指定Observable执行任务的调度器
 * doOnEach( ) — 注册一个动作，对Observable发射的每个数据项使用
 * doOnCompleted( ) — 注册一个动作，对正常完成的Observable使用
 * doOnError( ) — 注册一个动作，对发生错误的Observable使用
 * doOnTerminate( ) — 注册一个动作，对完成的Observable使用，无论是否发生错误
 * doOnSubscribe( ) — 注册一个动作，在观察者订阅时使用
 * doOnUnsubscribe( ) — 注册一个动作，在观察者取消订阅时使用
 * finallyDo( ) — 注册一个动作，在Observable完成时使用
 * delay( ) — 延时发射Observable的结果
 * delaySubscription( ) — 延时处理订阅请求
 * timeInterval( ) — 定期发射数据
 * using( ) — 创建一个只在Observable生命周期存在的资源
 * single( ) — 强制返回单个数据，否则抛出异常
 * singleOrDefault( ) — 如果Observable完成时返回了单个数据，就返回它，否则返回默
 * 认数据
 * toFuture( ) , toIterable( ) , toList( ) — 将Observable转换为其它对象或数据结构
 */
public class RxAssistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_assist);
    }

    private void  delay(){
        Observable.just("1","2","3","4","5")
                .delay(500, TimeUnit.MILLISECONDS)
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


    private void rxDo(){
        Observable.just("9","6","7")
                .doOnEach(new Observer<String>() {
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
