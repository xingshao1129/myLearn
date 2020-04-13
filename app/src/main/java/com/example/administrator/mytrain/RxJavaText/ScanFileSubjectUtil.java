package com.example.administrator.mytrain.RxJavaText;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * author : 90589
 * date   : 2020/1/18
 * desc   : 描述
 */
public class ScanFileSubjectUtil {

    private ScanFileSubjectUtil() {
    }

    private static Subject<String> subject;

    private static Subject<String> subject1;
    //更新被观察者的数据
    public static synchronized void  setVoiceData(String data) {

//        getMapDataSubject().toSerialized().onNext(data);

    }

    //获得被观察者，可以添加订阅者或信息变更通知订阅者
    public static Subject<String> getMapDataSubject() {
        if (subject == null) {
            subject = PublishSubject.<String>create().toSerialized();
        }
        return subject;
    }


    public static void clearData() {
        subject = null;
    }
}
