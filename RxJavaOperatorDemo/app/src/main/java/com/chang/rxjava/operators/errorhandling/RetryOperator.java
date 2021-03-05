package com.chang.rxjava.operators.errorhandling;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 5:50 PM.
 *
 * @version 1.0
 */
public class RetryOperator {

    private void catchTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onNext(1 / 0);
            emitter.onComplete();
//        }).onErrorResumeNext(throwable -> {
//            return Observable.just(2);
//        }).onErrorReturn(throwable -> {
//            return new Exception("报错啦");
        }).onErrorComplete().subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    // 遇到错误重试 n 次
    private void retryTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onNext(1 / 0);
            emitter.onComplete();
        }).retry(5).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        RetryOperator operator = new RetryOperator();
//        operator.catchTest();

        operator.retryTest();
    }
}
