package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.mytrain.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * Rxjava数据转换操作
 * 变换操作
 * 这些操作符可用于对Observable发射的数据进行变换，详细解释可以看每个操作符的文档
 * Buffer — 缓存，可以简单的理解为缓存，它定期从Observable收集数据到一个集合，
 * 然后把这些数据集合打包发射，而不是一次发射一个
 * FlatMap — 扁平映射，将Observable发射的数据变换为Observables集合，然后将这些
 * Observable发射的数据平坦化的放进一个单独的Observable，可以认为是一个将嵌套的
 * 数据结构展开的过程。
 * GroupBy — 分组，将原来的Observable分拆为Observable集合，将原始Observable发射
 * 的数据按Key分组，每一个Observable发射一组不同的数据
 * Operators
 * 30
 * Map — 映射，通过对序列的每一项都应用一个函数变换Observable发射的数据，实质是
 * 对序列中的每一项执行一个函数，函数的参数就是这个数据项
 * Scan — 扫描，对Observable发射的每一项数据应用一个函数，然后按顺序依次发射这
 * 些值
 * Window — 窗口，定期将来自Observable的数据分拆成一些Observable窗口，然后发射
 * 这些窗口，而不是每次发射一项。类似于Buffer，但Buffer发射的是数据，Window发射的
 * 是Observable，每一个Observable发射原始Observable的数据的一个子集
 */
public class RxTransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_transform);
    }

    private void groupBy() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 2 == 0 ? 0 : 1;
                    }
                }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupedObservable<Integer, Integer> groupedObservable) {
                int key = groupedObservable.getKey();
                switch (key){
                    case 0:
                        groupedObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.i("--groupBy()--0", Integer.toString(integer));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        break;
                    case 1:
                        groupedObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.i("--groupBy()--1", Integer.toString(integer));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void map() {
        Observable.just(1, 2, 3, 4)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return Integer.toString(integer);
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("--map()", s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void cast() {
        Observable.just(1, 2, 3, 4)
                .cast(String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("--cast()", s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void scan(){
        Observable.just(1, 2, 3, 4,5,6,7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer t1, Integer t2) throws Exception {
                        return t1+t2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i("--scan()", Integer.toString(integer));
            }
        });
    }

    /**
     * 定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次
     * 发射一项数据
     */
    private void window(){
        Observer observer=new Observer() {
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
        };
        Observable.just(1,3,5,7,9,11,13,15)
                .window(3)
                .subscribe(observer);

        Observable.just(1,3,5,7,9,11,13,15)
                .window(4,1)
                .subscribe(observer);
    }


}
