package com.chang.rxjava.operators.assist;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 6:07 PM.
 *
 * @version 1.0
 */
public class AssistOperator {

    // doOnNext 要先于 onNext();
    private void doOnNextTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onComplete();
        }).doOnNext(o -> {
            System.out.println("doOnNext: " + o);
        }).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    // doOnError 要先于 onError();
    private void doOnErrorTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onNext(1 / 0);
            emitter.onComplete();
        }).doOnError(throwable -> {
            System.out.println("doOnError: " + throwable);
        }).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    // doOnComplete 要先于 onComplete();
    private void doOnCompletedTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onComplete();
        }).doOnComplete(() -> {
            System.out.println("doOnComplete");
        }).doFinally(() -> {
            System.out.println("doFinally");
        }).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    // doOnSubscribe 要先于 subscribe();
    private void doOnSubscribeTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onComplete();
        }).doOnSubscribe(disposable -> {
            System.out.println("doOnSubscribe: " + disposable);
        }).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    // 给Observable发射的每个数据项添加一个时间戳
    private void timestampTest() {
        Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }).timestamp(TimeUnit.MILLISECONDS).subscribe(o -> {
            System.out.println(o);
        }, throwable -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("Complete");
        });
    }

    public static void main(String[] args) {
        AssistOperator operator = new AssistOperator();

//        operator.doOnNextTest();

//        operator.doOnErrorTest();

//        operator.doOnCompletedTest();

//        operator.doOnSubscribeTest();

        operator.timestampTest();
    }
}
