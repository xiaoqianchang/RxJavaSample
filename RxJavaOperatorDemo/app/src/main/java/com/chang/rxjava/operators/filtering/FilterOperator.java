package com.chang.rxjava.operators.filtering;

import java.io.Serializable;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/1 7:20 PM.
 *
 * @version 1.0
 */
public class FilterOperator {

    private void filterOperator() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        just.filter(integer -> {
            return integer < 4;
        }).subscribe(integer -> {
            System.out.println(integer);
        });
    }

    /**
     * ofType 类似于 isInstanceOf
     */
    private void ofTypeTest() {
        Observable<? extends Serializable> just = Observable.just(1, 2, 3, "4", 5, "6");
        just.ofType(Integer.class).subscribe(integer -> {
            System.out.println(integer);
        });
    }

    public static void main(String[] args) {
        FilterOperator operator = new FilterOperator();
//        operator.filterOperator();

        operator.ofTypeTest();
    }
}
