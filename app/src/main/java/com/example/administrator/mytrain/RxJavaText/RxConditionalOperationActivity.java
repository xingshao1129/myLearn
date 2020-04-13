package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytrain.R;

/**
 * 这个页面的操作符可用于根据条件发射或变换Observables，或者对它们做布尔运算：
 * 条件操作符
 * amb( ) — 给定多个Observable，只让第一个发射数据的Observable发射全部数据
 * defaultIfEmpty( ) — 发射来自原始Observable的数据，如果原始Observable没有发射
 * 数据，就发射一个默认数据
 * ( rxjava-computation-expressions ) doWhile( ) — 发射原始Observable的数据序列，然
 * 后重复发射这个序列直到不满足这个条件为止
 * ( rxjava-computation-expressions ) ifThen( ) — 只有当某个条件为真时才发射原始
 * Observable的数据序列，否则发射一个空的或默认的序列
 * skipUntil( ) — 丢弃原始Observable发射的数据，直到第二个Observable发射了一个
 * 数据，然后发射原始Observable的剩余数据
 * skipWhile( ) — 丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发
 * 射原始Observable剩余的数据
 * ( rxjava-computation-expressions ) switchCase( ) — 基于一个计算结果，发射一个指定
 * Observable的数据序列
 * takeUntil( ) — 发射来自原始Observable的数据，直到第二个Observable发射了一个
 * 数据或一个通知
 * takeWhile( ) and takeWhileWithIndex( ) — 发射原始Observable的数据，直到一个特
 * 定的条件为真，然后跳过剩余的数据
 * ( rxjava-computation-expressions ) whileDo( ) — 如果条件为 true ，则发射源
 * Observable数据序列，并且只要条件保持为 true 就重复发射此数据序列
 * ( rxjava-computation-expressions ) — 表示这个操作符当前是可选包 rxjavacomputation-expressions 的一部分，还没有包含在标准RxJava的操作符集合里
 * 布尔操作符
 * all( ) — 判断是否所有的数据项都满足某个条件
 * contains( ) — 判断Observable是否会发射一个指定的值
 * exists( ) and isEmpty( ) — 判断Observable是否发射了一个值
 * sequenceEqual( ) — 判断两个Observables发射的序列是否相等
 */
public class RxConditionalOperationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_conditional_operation);
    }
}
