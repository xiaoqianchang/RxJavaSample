package com.chang.rxjava.operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

/**
 * Description: 将一个发射数据的 Observable 变换为多个 Observables，然后将它们发射的数据合并后放进一个单独的 Observable 。
 * <p>
 * Created by Chang.Xiao on 2021/2/26 3:37 PM.
 *
 * @version 1.0
 */
public class FlatMapOperator {

    /**
     * flatMap 将生成的多个 Observable 发射的数据进行 merge 操作，不能保证数据不交错。
     */
    private void flatMapTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4 ,5 ,6);
        Observable<Object> flatMap = just.flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.just(integer + " flatMap");
            }
        });
        flatMap.subscribe(o -> {
            System.out.println(o);
        });
    }

    /**
     * concatMap 将生成的多个 Observable 发射的数据进行 merge 操作，能保证数据不交错，按照严格的顺序发射这些数据。
     */
    private void concatMapTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Object> concatMap = just.concatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.just(integer + " concatMap");
            }
        });
        concatMap.subscribe(o -> {
            System.out.println(o);
        });
    }

    private void switchMapTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Object> switchMap = just.switchMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.just(integer + " switchMap");
            }
        });
        switchMap.subscribe(o -> {
            System.out.println(o);
        });
    }

    public static void main(String[] args) {
        FlatMapOperator operator = new FlatMapOperator();
        operator.flatMapTest();

//        operator.concatMapTest();

//        operator.switchMapTest();
    }
}
