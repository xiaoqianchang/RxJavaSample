package com.chang.rxjava.operators.creating;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/1/30 5:26 PM.
 *
 * @version 1.0
 */
public class FromOperator {

    Integer[] items = {0, 1, 2, 3, 4, 5};

    private void fromTest() {
        Observable<Integer> array = Observable.fromArray(items);
        array.subscribe(item -> {
            System.out.println(item);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        FromOperator operator = new FromOperator();
        operator.fromTest();
    }
}
