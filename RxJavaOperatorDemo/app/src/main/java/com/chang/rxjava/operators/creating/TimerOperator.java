package com.chang.rxjava.operators.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/2/26 11:43 AM.
 *
 * @version 1.0
 */
public class TimerOperator {

    private void timerTest() {
        Observable<Long> timer = Observable.timer(1, TimeUnit.SECONDS);
        timer.subscribe(item -> {
            System.out.println("timer: " + item);
        });
    }

    public static void main(String[] args) {
        TimerOperator operator = new TimerOperator();
        operator.timerTest();
    }
}
