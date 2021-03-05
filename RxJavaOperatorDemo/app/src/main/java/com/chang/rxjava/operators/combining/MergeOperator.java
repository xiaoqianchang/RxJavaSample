package com.chang.rxjava.operators.combining;

import java.io.Serializable;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 4:06 PM.
 *
 * @version 1.0
 */
public class MergeOperator {

    private void mergeTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<String> just1 = Observable.just("a", "b", "c");

        // merge
        Observable<? extends Serializable> merge = Observable.merge(just, just1);
        merge.subscribe(serializable -> System.out.println(serializable));

        // mergeDelayError
        Observable<? extends Serializable> mergeDelayError = Observable.mergeDelayError(just, just1);
        mergeDelayError.subscribe(serializable -> System.out.println(serializable));

        // mergeWith 多个数据源的数据类型必须相同
//        Observable<Integer> mergeWith = just.mergeWith(just1);
//        mergeWith.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        MergeOperator operator = new MergeOperator();
        operator.mergeTest();

    }
}
