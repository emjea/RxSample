package com.frontinelabs.rxsample.model;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by EBaba on 11/10/2017.
 */

public class MyRxBus {

    private static PublishSubject<Object> sSubject = PublishSubject.create();

    private MyRxBus() {
        // hidden constructor
    }


    public static Disposable subscribe(@NonNull Consumer<Object> action) {
        return sSubject.subscribe(action);
    }

    public static void publish(@NonNull Object message) {
        sSubject.onNext(message);
    }
}