package com.chang.rxjava.operators.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Description: timer、delay、interval 用法
 * timer、delay、interval 在main方法中不起作用 https://blog.csdn.net/jdsjlzx/article/details/52860632
 * 解决方法：
 * 1. 在timer、delay、interval代码后面通过Thread.sleep延长当前线程的时间。
 * 2. 给timer、delay、interval指定调度器Schedulers.trampoline()。
 * <p>
 * Created by Chang.Xiao on 2021/2/24 7:47 PM.
 *
 * @version 1.0
 */
public class IntervalOperator {

    /**
     * interval 会一直保持运行，有如下两种方式去停止
     * 1. 用 disposable.dispose()
     * 2. 用 take(n) 操作符，任务执行n次自动结束
     */
    private void intervalTest() {
        // 方式一：map
//        Observable.interval(2, TimeUnit.SECONDS, Schedulers.trampoline())
//                .take(5) // 设置发射数据项的最大值
//                .map(aLong -> {
//                    return aLong;
//                }).subscribe(item -> {
//                    System.out.println(item);
//                }, throwable -> {
//                    System.out.println(throwable);
//                }, () -> {
//                    System.out.println("Complete");
//                });

        // 方式二：flatMap
        Disposable disposable = Observable.interval(2, TimeUnit.SECONDS, Schedulers.trampoline())
                .take(5) // 设置发射数据项的最大值
                .flatMap(aLong -> {
//                    return Observable.just(aLong);
                    return Observable.create(emitter -> {
                        emitter.onNext(aLong);
                        emitter.onComplete();
                    });
                }).subscribe(item -> {
                    System.out.println(item);
                }, throwable -> {
                    System.out.println(throwable);
                }, () -> {
                    System.out.println("Complete");
                });
    }

    /**
     * timer 用法
     */
    private void timerTest() {
        Observable<Long> timer = Observable.timer(2, TimeUnit.SECONDS, Schedulers.trampoline());
        timer.subscribe((item) -> {
            System.out.println(item);
        });
    }

    /**
     * delay 用法
     */
    private void delayTest() {
        Observable.just(1)
                .delay(2, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribe(integer -> {
                    System.out.println(integer);
                });

//        Observable.create(emitter -> {
//            emitter.onNext("delay");
//            emitter.onComplete();
//        }).delay(2, TimeUnit.SECONDS, Schedulers.trampoline()).subscribe(o -> System.out.println(o));
    }

    public static void main(String[] args) {
        IntervalOperator operator = new IntervalOperator();
        operator.intervalTest();

        operator.timerTest();

//        operator.delayTest();

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
