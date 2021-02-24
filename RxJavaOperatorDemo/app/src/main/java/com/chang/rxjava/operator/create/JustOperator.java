package com.chang.rxjava.operator.create;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/24 8:13 PM.
 *
 * @version 1.0
 */
public class JustOperator {

    private void justTest() {
        Observable<Integer> observable = Observable.just(1, 2, 3);
        observable.subscribe(item -> {
            System.out.println(item);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        JustOperator operator = new JustOperator();
        operator.justTest();
    }
}
