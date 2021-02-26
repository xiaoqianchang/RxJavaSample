package com.chang.rxjava.operators.creating;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/1/29 11:17 AM.
 *
 * @version 1.0
 */
public class CreateOperator {
    public static void main(String[] args) {
        CreateOperator operator = new CreateOperator();
        operator.createTest();
    }

    /**
     * create 方法默认不在任何特定的调度器上执行。
     */
    private void createTest() {
        Observable observable = Observable.create(emitter -> {
            try {
                if (!emitter.isDisposed()) {
                    for (int i = 1; i < 5; i++) {
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("create-onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer o) {
                System.out.println("create-onNext: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("create-onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("create-onComplete");
            }
        });
    }
}
