package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.mytrain.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 * 创建==》转换==》过滤==》组合
 */

/**
 * 这个页面展示的操作符可用于组合多个Observables。
 * startWith( ) — 在数据序列的开头增加一项数据
 * merge( ) — 将多个Observable合并为一个
 * mergeDelayError( ) — 合并多个Observables，让没有错误的Observable都完成后再发
 * 射错误通知
 * zip( ) — 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果
 * and( ) , then( ) , and when( ) — ( rxjava-joins ) 通过模式和计划组合多个
 * Observables发射的数据集合
 * combineLatest( ) — 当两个Observables中的任何一个发射了一个数据时，通过一个指
 * 定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的
 * 结果
 * join( ) and groupJoin( ) — 无论何时，如果一个Observable发射了一个数据项，只
 * 要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数
 * 据合并发射
 * switchOnNext( ) — 将一个发射Observables的Observable转换成另一个Observable，
 * 后者发射这些Observables最近发射的数据
 * ( rxjava-joins ) — 表示这个操作符当前是可选的 rxjava-joins 包的一部分，还没有包含
 * 在标准的RxJava操作符集合里
 */
public class RxGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_group);
    }

    /**
     * 除了传递多个Observable给 merge ，
     * 你还可以传递一个Observable列表 List ，数组，甚至
     * 是一个发射Observable序列的Observable，
     * merge 将合并它们的输出作为单个Observable的
     */
    private void merge(){
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.merge(odds, evens)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("--merge",Integer.toString(integer));
                    }
                });
    }

    /**
     * 在数据序列的开头插入一条指定的项
     */
    private void startWith(){
        Observable.just("1","3","5")
                .startWith("0")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("--merge",s);
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
     * Switch 订阅一个发射多个Observables的Observable。
     * 它每次观察那些Observables中的一
     * 个， Switch 返回的这个Observable取消订阅前一个发射数据的Observable，
     * 开始发射最近的Observable发射的数据。
     * 注意：当原始Observable发射了一个新的Observable时
     * （不是这个新的Observable发射了一条数据时），它将取消订阅之前的那个Observable。
     * 这意味着，在后来那个Observable产生之后到它开始发射数据之前的这段时间里，
     * 前一个Observable发射
     * 的数据将被丢弃（就像图例上的那个黄色圆圈一样）。
     */
    private void rxSwitch(){
        Observable.just("1","3","5");

    }


    private void zipWith(){
        
    }

}
