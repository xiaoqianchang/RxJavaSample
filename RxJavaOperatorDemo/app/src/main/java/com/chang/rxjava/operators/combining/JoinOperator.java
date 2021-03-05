package com.chang.rxjava.operators.combining;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 3:32 PM.
 *
 * @version 1.0
 */
public class JoinOperator {

    private void joinTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<String> just1 = Observable.just("a", "b", "c");

        // join
        Observable<Integer> join = just.join(just1, integer -> {
            return Observable.just(integer);
        }, s -> {
            return Observable.just(s);
        }, (integer, s) -> {
            return integer;
        });
        join.subscribe(s -> System.out.println(s));

        // groupJoin
        Observable<Integer> groupJoin = just.groupJoin(just1, integer -> {
            return Observable.just(integer);
        }, s -> {
            return Observable.just(s);
        }, (integer, stringObservable) -> {
            return integer;
        });
        groupJoin.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        JoinOperator operator = new JoinOperator();
        operator.joinTest();
    }
}
