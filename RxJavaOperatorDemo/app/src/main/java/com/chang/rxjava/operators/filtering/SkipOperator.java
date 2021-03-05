package com.chang.rxjava.operators.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:34 PM.
 *
 * @version 1.0
 */
public class SkipOperator {

    private void skipTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);

        // 跳过指定数量
        Observable<Integer> skip = just.skip(2);
        skip.subscribe(integer -> System.out.println(integer));

        // 跳过设置时间发射的数据
        Observable<Integer> skip1 = just.skip(1, TimeUnit.SECONDS);
        skip1.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        SkipOperator operator = new SkipOperator();
        operator.skipTest();

    }
}
