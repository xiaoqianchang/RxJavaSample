package com.chang.rxjava.operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/26 4:56 PM.
 *
 * @version 1.0
 */
public class GroupByOperator {

    private void groupByTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<GroupedObservable<Boolean, Integer>> groupBy = just.groupBy(integer -> {
            return integer % 2 == 0;
        });
        groupBy.subscribe(booleanIntegerGroupedObservable -> {
            System.out.println(booleanIntegerGroupedObservable);
        });
    }

    public static void main(String[] args) {
        GroupByOperator operator = new GroupByOperator();
        operator.groupByTest();
    }
}
