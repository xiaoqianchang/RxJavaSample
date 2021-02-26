package com.chang.rxjava.operator.create;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/25 4:00 PM.
 *
 * @version 1.0
 */
public class StartOperator {

    private void startTest() {
        // 发射这个传递给 fromAction 的数据
        Observable.fromAction(() -> {
            System.out.println("fromAction");
        }).subscribe();

        // 带返回值的
        Observable.fromCallable(() -> {
            System.out.println("fromCallable");
            return "return-callable";
        }).subscribe(item -> {
            System.out.println(item);
        });

        // 发射这个你传递给fromRunnable的数据。
        Observable.fromRunnable(() -> {
            System.out.println("fromRunnable");
        }).subscribe();
    }

    public static void main(String[] args) {
        StartOperator operator = new StartOperator();
        operator.startTest();
    }
}
