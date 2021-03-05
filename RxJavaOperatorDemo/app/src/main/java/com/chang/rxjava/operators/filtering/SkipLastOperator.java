package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:38 PM.
 *
 * @version 1.0
 */
public class SkipLastOperator {

    private void skipLastTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> skipLast = just.skipLast(2);
        skipLast.subscribe(integer -> System.out.println(integer));

        Observable<Integer> skipLast1 = just.skipLast(1, TimeUnit.SECONDS);
        skipLast1.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] ags) {
        SkipLastOperator operator = new SkipLastOperator();
        operator.skipLastTest();
    }
}
