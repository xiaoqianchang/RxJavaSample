package com.chang.rxjava.operators.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

/**
 * Description: 抑制（过滤掉）重复的数据项
 *
 * Distinct的过滤规则是：只允许还没有发射过的数据项通过。
 * <p>
 * Created by Chang.Xiao on 2021/3/1 5:51 PM.
 *
 * @version 1.0
 */
public class DistinctOperator {

    private void distinctTest() {
        Observable<Integer> just = Observable.just(1, 2, 1, 1, 2, 3);
        just.distinct()
                .subscribe(integer -> {
                    System.out.println(integer);
                });
    }

    /**
     * 这个函数根据原始Observable发射的数据项产生一个Key，然后，比较这些Key而不是数据本身，来判定两个数据是否是不同的。
     */
    private void distinctTest2() {
        Observable<Integer> just = Observable.just(1, 2, 1, 1, 2, 3);
        just.distinct(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer;
            }
        }).subscribe(integer -> {
            System.out.println(integer);
        }, throwable -> {
            System.out.println(throwable);
        });
    }

    /**
     * distinctUntilChanged 它只判定一个数据和它的直接前驱（前一个）是否是不同的。
     */
    private void distinctTest3() {
        Observable<Integer> just = Observable.just(1, 2, 1, 1, 2, 3);
        just.distinctUntilChanged().subscribe(integer -> {
            System.out.println(integer);
        }, throwable -> {
            System.out.println(throwable);
        });
    }

    /**
     * 和distinct(Func1)一样，根据一个函数产生的Key判定两个相邻的数据项是不是不同的。
     */
    private void distinctTest4() {
        Observable<Integer> just = Observable.just(1, 2, 1, 1, 2, 3);
        just.distinctUntilChanged(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer;
            }
        }).subscribe(integer -> {
            System.out.println(integer);
        }, throwable -> {
            System.out.println(throwable);
        });
    }

    public static void main(String[] args) {
        DistinctOperator operator = new DistinctOperator();
        operator.distinctTest();

//        operator.distinctTest2();

//        operator.distinctTest3();

//        operator.distinctTest4();
    }
}
