package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:49 PM.
 *
 * @version 1.0
 */
public class TakeLastOperator {

    private void takeLastTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> takeLast = just.takeLast(2);
        takeLast.subscribe(integer -> System.out.println(integer));

        Observable<Integer> takeLast1 = just.takeLast(1, TimeUnit.SECONDS);
        takeLast1.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] ags) {
        TakeLastOperator operator = new TakeLastOperator();
        operator.takeLastTest();
    }
}
