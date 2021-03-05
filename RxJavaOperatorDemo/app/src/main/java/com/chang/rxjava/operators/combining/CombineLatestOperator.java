package com.chang.rxjava.operators.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;

/**
 * Description: 当两个Observables中的任何一个发射了数据时，使用一个函数结合每个Observable发射的最近数据项，并且基于这个函数的结果发射数据。
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:57 PM.
 *
 * @version 1.0
 */
public class CombineLatestOperator {

    private void combineLatestTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<String> just1 = Observable.just("a", "b", "c");

        /*
        just just1
        输出
        6a
        6b
        6c

        just1 just
        输出
        1c
        2c
        3c
        4c
        5c
        6c
         */
        Observable<String> combineLatest = Observable.combineLatest(just, just1, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return String.valueOf(integer).concat(s);
            }
        });
        combineLatest.subscribe(s -> System.out.println(s));
    }

    public static void main(String[] args) {
        CombineLatestOperator operator = new CombineLatestOperator();
        operator.combineLatestTest();
    }
}
