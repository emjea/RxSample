package com.frontinelabs.rxsample.model;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by EBaba on 11/10/2017.
 */

public class UsernameModel {

    private static UsernameModel instance;

    private PublishSubject<String> subject = PublishSubject.create();

    public static UsernameModel instanceOf() {
        if (instance == null) {
            instance = new UsernameModel();
        }
        return instance;
    }

    /**
     * Pass a String down to event listeners.
     */
    public void setString(String string) {
        subject.onNext(string);
    }

    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     */
    public Observable<String> getStringObservable() {
        return subject;
    }

}