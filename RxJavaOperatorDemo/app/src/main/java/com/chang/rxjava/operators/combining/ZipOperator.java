package com.chang.rxjava.operators.combining;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 4:39 PM.
 *
 * @version 1.0
 */
public class ZipOperator {

    private void zipTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<String> just1 = Observable.just("a", "b", "c");

        /*
        just,just1 或者 just1,just
        都是输出
        1a
        2b
        3c

        这里是与 combineLatest 有区别的，数据量以最少的 just1 为准。
         */
        Observable<String> zip = Observable.zip(just, just1, (integer, s) -> {
            return String.valueOf(integer).concat(s);
        });
    }

    public static void main(String[] args) {
        ZipOperator operator = new ZipOperator();
        operator.zipTest();
    }
}
