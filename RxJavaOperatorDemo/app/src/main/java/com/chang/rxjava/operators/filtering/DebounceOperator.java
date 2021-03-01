package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/1 2:07 PM.
 *
 * @version 1.0
 */
public class DebounceOperator {

    private void debounceTest() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
        Observable<Long> debounce = interval.debounce(2, TimeUnit.SECONDS, Schedulers.trampoline());
        debounce.subscribe(aLong -> {
            System.out.println(aLong);
        });
    }

    private void throttleTest() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
        Observable<Long> throttle = interval.throttleWithTimeout(2, TimeUnit.SECONDS, Schedulers.trampoline());
        throttle.subscribe(aLong -> {
            System.out.println(aLong);
        });
    }

    public static void main(String[] args) {
        DebounceOperator operator = new DebounceOperator();
        operator.debounceTest();

//        operator.throttleTest();
    }
}
