package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:41 PM.
 *
 * @version 1.0
 */
public class TakeOperator {

    private void takeTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> take = just.take(2);
        take.subscribe(integer -> System.out.println(integer));

        Observable<Integer> take1 = just.take(1, TimeUnit.SECONDS);
        take1.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        TakeOperator operator = new TakeOperator();
        operator.takeTest();
    }
}
