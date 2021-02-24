package com.chang.rxjava.operator.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/24 7:47 PM.
 *
 * @version 1.0
 */
public class IntervalOperator {

    private void intervalTest() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(item -> {
            System.out.println(item);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    private void timerTest() {
        Observable<Long> timer = Observable.timer(1, TimeUnit.SECONDS);
        timer.subscribe((item) -> {
            System.out.println(item);
        });
    }

    public static void main(String[] args) {
        IntervalOperator operator = new IntervalOperator();
        operator.intervalTest();

//        operator.timerTest();
    }
}
