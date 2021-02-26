package com.chang.rxjava.operators.creating;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/25 11:35 AM.
 *
 * @version 1.0
 */
public class RepeatOperator {

    private void repeatTest() {
        // 1. 先创建特定序列
        Observable<Integer> just = Observable.just(1, 2, 3);
        // 2. 创建发射特定数据重复多次的 Observable
        Observable<Integer> repeat = just.repeat(3);
        // 3. 订阅
        repeat.subscribe(item -> {
            System.out.println(item);
        });
    }

    private void repeatWhenTest() {
        Observable<Integer> just = Observable.just(1, 2, 3);
        Observable<Integer> repeatWhen = just.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
                return objectObservable;
            }
        });
        repeatWhen.subscribe(item -> {
            System.out.println(item);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    /**
     * doWhile/whileDo 属于可选包rxjava-computation-expressions，不是RxJava标准操作符的一部分。doWhile在原始序列的每次重复后检查某个条件，如果满足条件才重复发射。
     */
    private void doWhileTest() {
        Observable<Integer> just = Observable.just(1, 2, 3);
    }

    public static void main(String[] args) {
        RepeatOperator operator = new RepeatOperator();
//        operator.repeatTest();

        operator.repeatWhenTest();
    }
}
