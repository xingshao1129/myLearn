package com.example.administrator.mytrain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytrain.RxJavaText.RxjavaActivity;
import com.example.administrator.mytrain.RxJavaText.ScanFileSubjectUtil;
import com.example.administrator.mytrain.androidh5.AndroidH5Activity;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.bean.ObjectToJson;
import com.example.administrator.mytrain.broadcast.BroadcastMainActivity;
import com.example.administrator.mytrain.commontitle.CommonTitleActivity;
import com.example.administrator.mytrain.databing.DataActivity;
import com.example.administrator.mytrain.designmode.DesignModeActivity;
import com.example.administrator.mytrain.dialog.DialogShowActivity;
import com.example.administrator.mytrain.kotlin.KotlinMainActivity;
import com.example.administrator.mytrain.location.LocationActivity;
import com.example.administrator.mytrain.selfview.ViewActivity;
import com.example.administrator.mytrain.share.ShareActivity;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.Subject;

import static java.lang.Thread.sleep;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.andincrement).setOnClickListener(this);
        findViewById(R.id.object_to_json).setOnClickListener(this);
        findViewById(R.id.rxjava).setOnClickListener(this);
        findViewById(R.id.h5).setOnClickListener(this);
        findViewById(R.id.databing).setOnClickListener(this);
        dialog = ((TextView) findViewById(R.id.dialog));
        dialog.setOnClickListener(this);
        findViewById(R.id.design_mode).setOnClickListener(this);
        findViewById(R.id.common_title).setOnClickListener(this);
        findViewById(R.id.location).setOnClickListener(this);
        findViewById(R.id.broadcast).setOnClickListener(this);
        findViewById(R.id.kotlin).setOnClickListener(this);
        findViewById(R.id.self_view).setOnClickListener(this);
        findViewById(R.id.video_voice).setOnClickListener(this);
        setTitle("主页");
        final Integer[] num = {0};
        ScanFileSubjectUtil
                .getMapDataSubject()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
//                        sleep(40);
                        num[0]=num[0]+1;
                        Log.i("--main====>",Thread.currentThread().getName()+"  num==>"+ num[0] +"  name==>"+s);
                    }
                });
        final Subject<String> subject=ScanFileSubjectUtil.getMapDataSubject().toSerialized();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=1;i<=2500;i++){
//                        sleep(70);
                        Log.i("-~-Thread1==>",Thread.currentThread().getName()+"  num==>"+i);
                        subject.onNext("A"+i);
                    }
                }catch (Exception e) {

                }


            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=1;i<=2500;i++){
//                        sleep(50);
                        Log.i("-~-Thread2==>",Thread.currentThread().getName()+"  num==>"+i);
                        subject.onNext("B"+i);
                    }
                }catch (Exception e){

                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.text){
            startActivity(new Intent(mContext, ShareActivity.class));
        }else if (id==R.id.andincrement){
            AtomicInteger integer=new AtomicInteger();
            Log.i("--get",integer.get()+"");
            Log.i("--getAndIncrement",integer.getAndIncrement()+"");
            Log.i("--getAndIncrement结果",integer.get()+"");
            integer.set(1);
            Log.i("--set",integer.get()+"");
            Log.i("--getAndDecrement",integer.getAndDecrement()+"");
            Log.i("--getAndDecrement结果",integer.get()+"");
        }else if (id==R.id.object_to_json){
            ObjectToJson objectToJson=new ObjectToJson
                    .Builder()
                    .name("邢少强")
                    .age("24")
                    .address("浦东新区")
                    .location("北艾路").build();
            Gson gson=new Gson();
            String json=gson.toJson(objectToJson);
            Log.i("--json",json);
            objectToJson=gson.fromJson(json,ObjectToJson.class);
            Log.i("--bean",objectToJson.toString());
            MulitTypeBean mulitTypeBean=new MulitTypeBean.Builder()
                    .name("内部类")
                    .days(23)
                    .json(objectToJson)
                    .money(567)
                    .time(new Date())
                    .list(Arrays.asList(new String[]{"list1","list2","list3"}))
                    .map(new HashMap<String, String>(){{put("name","姓　　名");put("value","我的");}
                    })
                    .build();
            String json2=gson.toJson(mulitTypeBean);
            Log.i("--含有内部类转换为json",json2);
            mulitTypeBean=null;
            mulitTypeBean=gson.fromJson(json2,MulitTypeBean.class);
            mulitTypeBean.money=899;
            Log.i("--json to object",mulitTypeBean.toString());
        }else if (id==R.id.rxjava){
            startActivity(new Intent(mContext, RxjavaActivity.class));
        }else if (id==R.id.h5){
            startActivity(new Intent(mContext, AndroidH5Activity.class));
        }else if (id==R.id.databing){
            startActivity(new Intent(mContext, DataActivity.class));
        }else if (id==R.id.dialog){
            startActivity(new Intent(mContext, DialogShowActivity.class));
        }else if (R.id.design_mode==id){
            startActivity(new Intent(mContext, DesignModeActivity.class));
        }else if (R.id.common_title==id){
            startActivity(new Intent(mContext, CommonTitleActivity.class));
        }else if (R.id.location==id){
            startActivity(new Intent(mContext, LocationActivity.class));
        }else if (id==R.id.broadcast){
            startActivity(new Intent(mContext, BroadcastMainActivity.class));
        }else if (id==R.id.kotlin){
            startActivity(new Intent(mContext, KotlinMainActivity.class));
        }else if (id==R.id.self_view){
            startActivity(new Intent(mContext, ViewActivity.class));
        }else if (id==R.id.video_voice){
            startActivity(new Intent(mContext, VideoVoiceActivity.class));
//            startActivity(new Intent(mContext, VideoActivity.class));
        }
    }
}
