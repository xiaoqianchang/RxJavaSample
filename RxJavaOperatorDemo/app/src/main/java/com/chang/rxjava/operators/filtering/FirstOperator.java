package com.chang.rxjava.operators.filtering;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 11:47 AM.
 *
 * @version 1.0
 */
public class FirstOperator {

    private void firstTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        // 只发射第一个数据，如果没有发射默认值
        just.first(0)
                .subscribe((integer, throwable) -> {
                    System.out.println(integer);
                });

        // 只发射第一个数据
        just.firstElement().subscribe(integer -> System.out.println(integer));

        just.firstOrError().subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        FirstOperator operator = new FirstOperator();
        operator.firstTest();
    }
}
