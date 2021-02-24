package com.chang.rxjava.scheduler;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Description: Rxjava 中 Scheduler 的使用，可用于做延时。定时任务.
 * <p>
 * Created by Chang.Xiao on 2021/1/28 2:35 PM.
 *
 * @version 1.0
 */
public class SchedulerTest {
    public static void main(String[] args) {
        SchedulerTest test = new SchedulerTest();
        test.delayScheduler();
    }

    private void workerTest1() {
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedule(new Runnable() {
            @Override
            public void run() {
                // 递归调度器
//                worker.schedule(this);

                // 检测和设置状态
//                worker.isDisposed();
//                worker.dispose();
            }
        });
    }

    // 延时调度
    private void delayScheduler() {
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedule(() -> {
            System.out.println("delay scheduler");
        }, 500, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 周期调度
    private void schedulePeriodically() {
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedulePeriodically(() -> {
            System.out.println("schedule periodically");
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}
