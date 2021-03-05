package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:23 PM.
 *
 * @version 1.0
 */
public class SampleOperator {

    private void sampleTest() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
//        interval.subscribe(aLong -> System.out.println("interval-" + aLong));
        Observable<Long> sample = interval.sample(2, TimeUnit.SECONDS, Schedulers.trampoline());
        sample.subscribe(aLong -> System.out.println("sample-" + aLong));
    }

    public static void main(String[] args) {
        SampleOperator operator = new SampleOperator();
        operator.sampleTest();
    }
}
