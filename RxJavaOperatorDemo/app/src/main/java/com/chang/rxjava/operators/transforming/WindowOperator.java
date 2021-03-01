package com.chang.rxjava.operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/1 1:53 PM.
 *
 * @version 1.0
 */
public class WindowOperator {

    /**
     * Window和Buffer类似，但不是发射来自原始Observable的数据包，它发射的是Observables，
     * 这些Observables中的每一个都发射原始Observable数据的一个子集，最后发射一个onCompleted通知。
     */
    private void windowTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Observable<Integer>> window = just.window(2);
        window.subscribe(new Consumer<Observable<Integer>>() {
            @Override
            public void accept(Observable<Integer> integerObservable) throws Throwable {
                integerObservable.subscribe(integer -> {
                    System.out.println(integer);
                });
            }
        });
    }

    private void windowTest2() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Observable<Integer>> window = just.window(2, 3);
        window.subscribe(integerObservable -> {
            integerObservable.subscribe(integer -> {
                System.out.println(integer);
            });
        });
    }

    public static void main(String[] args) {
        WindowOperator operator = new WindowOperator();
        operator.windowTest();
        /*
        输出
        1
        2
        3
        4
        5
        6
         */

        operator.windowTest2();
        /*
        输出
        1
        2
        4
        5
         */
    }
}
