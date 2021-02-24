package com.chang.rxjava.operator.test;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/1/26 10:23 AM.
 *
 * @version 1.0
 */
public class ObservableTest {
    public static void main(String[] args) {
        ObservableTest text = new ObservableTest();
//        text.test1();
//        text.test2();
        text.test3();
    }

    private void test1() {
        Observable<Long> observable = Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        });
//        observable.subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                System.out.println(aLong);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                System.out.println("onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });
        observable.subscribe(aLong -> {
            System.out.println(aLong);
        }, throwable -> {
            System.out.println("onError: " + throwable.getMessage());
        });
    }

    private void test2() {
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(integer -> integer * integer)
                .filter(integer -> integer % 3 == 0);

        flow.subscribe(System.out::println);
    }

    private void test3() {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            return "Done";
        }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(System.out::println, Throwable::printStackTrace);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
