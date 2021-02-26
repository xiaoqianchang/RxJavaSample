package com.chang.rxjava.operators.creating;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/24 8:19 PM.
 *
 * @version 1.0
 */
public class RangeOperator {

    private void rangeTest() {
        Observable<Integer> range = Observable.range(1, 5);
        range.subscribe(item -> {
            System.out.println(item);
        });
    }

    public static void main(String[] args) {
        RangeOperator operator = new RangeOperator();
        operator.rangeTest();
    }
}
