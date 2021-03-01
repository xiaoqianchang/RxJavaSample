package com.chang.rxjava.operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;

/**
 * Description: 操作符将原始 Observable 发射的每一项数据都强制转换为一个指定的类型，然后再发射数据，它是 `map` 的一个特殊版本。
 * <p>
 * Created by Chang.Xiao on 2021/3/1 11:44 AM.
 *
 * @version 1.0
 */
public class ScanOperator {

    /**
     * 操作符对原始 Observable 发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。
     * 它将函数的结果同第二项数据一起填充给这个函数来产生它自己的第二项数据。它持续进行这个过程来产生剩余的数据序列。
     * 这个操作符在某些情况下被叫做 accumulator。
     */
    private void scanTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> scan = just.scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Throwable {
                return integer + integer2;
            }
        });
        scan.subscribe(integer -> {
            System.out.println(integer);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        ScanOperator operator = new ScanOperator();
        operator.scanTest();
    }
}
