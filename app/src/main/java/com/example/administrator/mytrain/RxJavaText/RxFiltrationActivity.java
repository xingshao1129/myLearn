package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.mytrain.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 *这个页面展示的操作符可用于过滤和选择Observable发射的数据序列。
 * filter( ) — 过滤数据
 * takeLast( ) — 只发射最后的N项数据
 * last( ) — 只发射最后的一项数据
 * lastOrDefault( ) — 只发射最后的一项数据，如果Observable为空就发射默认值
 * takeLastBuffer( ) — 将最后的N项数据当做单个数据发射
 * skip( ) — 跳过开始的N项数据
 * skipLast( ) — 跳过最后的N项数据
 * take( ) — 只发射开始的N项数据
 * first( ) and takeFirst( ) — 只发射第一项数据，或者满足某种条件的第一项数据
 * firstOrDefault( ) — 只发射第一项数据，如果Observable为空就发射默认值
 * elementAt( ) — 发射第N项数据
 * elementAtOrDefault( ) — 发射第N项数据，如果Observable数据少于N项就发射默认值
 * sample( ) or throttleLast( ) — 定期发射Observable最近的数据
 * throttleFirst( ) — 定期发射Observable发射的第一项数据
 * throttleWithTimeout( ) or debounce( ) — 只有当Observable在指定的时间后还没有发
 * 射数据时，才发射一个数据
 * timeout( ) — 如果在一个指定的时间段后还没发射数据，就发射一个异常
 * distinct( ) — 过滤掉重复数据
 * distinctUntilChanged( ) — 过滤掉连续重复的数据
 * ofType( ) — 只发射指定类型的数据
 * ignoreElements( ) — 丢弃所有的正常数据，只发射错误或完成通知
 */
public class RxFiltrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_filtration);
    }

    /**
     * 400 毫秒才发射数据（说的直白点就是 400 毫秒后才会走后面的逻辑）
     */
    private void debounce(){
        Observable.just("1","3","1","1","2","3")
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("--debounce()--0", s);
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
     * Distinct 的过滤规则是：只允许还没有发射过的数据项通过。
     */
    private void distinct(){
        Observable.just("1","3","1","1","2","3")
                .distinct()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("--distinct()--0", s);
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
     * RxJava将这个操作符实现为 elementAt ，
     * 给它传递一个基于0的索引值，它会发射原始
     * Observable数据序列对应索引位置的值，如果你传递给 elementAt 的值为5，
     * 那么它会发射第
     * 六项的数据。
     */
    private void elementAt(){
        Observable.just("1","3","1","10","2","3")
                .elementAt(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("--elementAt()--0", s);
                    }
                });
    }

    /**
     * Filter 操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射。
     */
    private void filter(){
        Observable.just(1,2,3,4,5,6,7,8,9)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer%2==0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("--filter()--Integer", Integer.toString(integer));
            }
        });

        Observable.just(1,2,"32",4,"61",6,"91",8,9)
                .ofType(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("--filter()--String", s);
                    }
                });
    }

    /**
     *只发射第一项（或者满足某个条件的第一项）数据
     */
    private void first(){
        Observable.just(1,2,3.4,5,7,9)
                .first(2)
                .subscribe(new Consumer<Number>() {
                    @Override
                    public void accept(Number number) throws Exception {

                    }
                });
    }

    /**
     * IgnoreElements 操作符抑制原始Observable发射的所有数据，只允许它的终止通知
     * （ onError 或 onCompleted ）通过。
     */
    private void ignoreElements(){
        Observable.just(1,2,3,4,5,6)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("--ignoreElements()", "onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * 只发射最后一项（或者满足某个条件的最后一项）数据
     */
    private void last(){
        Observable.just("2","5","9")
                .last("9")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("--last()", s);
                    }
                });
    }

    private void sample(){
        final Observable observable=Observable.just("2,3,5");


        observable.sample(200,TimeUnit.MILLISECONDS);

        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

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
     * 只发射前面的N项数据
     */
    private void take(){
        Observable.just("1","2","3","4","5")
                .take(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
    }

    /**
     * 发射Observable发射的最后N项数据
     */
    private void takeLast(){
        Observable.just("1","2","3","4","5")
                .take(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
    }


}
