package com.chang.rxjava.operators.creating;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Supplier;

/**
 * Description: defer 等待直到有观察者订阅时才创建 Observable，并且为每个观察者创建一个新的 Observable。
 * <p>
 * Created by Chang.Xiao on 2021/1/30 4:30 PM.
 *
 * @version 1.0
 */
public class DeferOperator {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    private Observable<String> valueObservableJust() {
        return Observable.just(value);
    }

    private Observable<String> valueObservableCreate() {
        return Observable.create(emitter -> {
            emitter.onNext(value);
            emitter.onComplete();
        });
    }

    private Observable<String> valueObservableDefer() {
        // defer 这个操作符接受一个你选择的 Observable 工厂函数作为单个参数。
        return Observable.defer(new Supplier<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> get() throws Throwable {
                // 这个函数没有参数，返回一个 Observable。
                return Observable.just(value);
            }
        });
    }

    private Observable<String> valueObservableDefer(String value) {
        return Observable.defer(() -> {
            try {
                // 将内容 value 写到 file 或者 disk
            } catch (Exception e) {
                return Observable.error(e);
            }

            return Observable.just(value);
        });
    }

    public static void main(String[] args) {
        // 错误用法
        DeferOperator operator = new DeferOperator();
        Observable<String> observable = operator.valueObservableJust(); // 此处报错，因为 value 为 null，将下面先进行赋值就 ok
        operator.setValue("Some Value");
        observable.subscribe(System.out::println);

        // 1. 用 valueObservableCreate 方法 ok 的

        // 2. 用 defer 操作符修正

    }
}
