package com.chang.rxjava.operators.filtering;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/**
 * Description: 不发射任何数据，只发射 Observable 的终止通知（`onError` 或 `onCompleted` ）。
 * <p>
 * Created by Chang.Xiao on 2021/3/5 1:53 PM.
 *
 * @version 1.0
 */
public class IgnoreElementsOperator {

    private void ignoreTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Completable ignoreElements = just.ignoreElements();
        ignoreElements.subscribe(() -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        IgnoreElementsOperator operator = new IgnoreElementsOperator();
        operator.ignoreTest();
    }
}
