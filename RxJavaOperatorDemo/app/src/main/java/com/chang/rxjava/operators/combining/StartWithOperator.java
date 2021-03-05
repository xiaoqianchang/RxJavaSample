package com.chang.rxjava.operators.combining;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 4:20 PM.
 *
 * @version 1.0
 */
public class StartWithOperator {

    private void startWithTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> just1 = Observable.just(9, 8, 7);

        // startWith
//        Observable<Integer> startWith = just.startWith(just1);
//        startWith.subscribe(integer -> System.out.println(integer));

        // startWithItem
//        just.startWithItem(0).subscribe(integer -> System.out.println(integer));

        just.startWithArray(-1, 0).subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        StartWithOperator operator = new StartWithOperator();
        operator.startWithTest();
    }
}
