package com.chang.rxjava.operators;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/5 6:32 PM.
 *
 * @version 1.0
 */
public class ComposeOperator {

    private void composeTest() {
        Observable<String> just = Observable.just("1", "2", "3", "4", "5", "6");
        just.compose(new ClassTransformer())
                .ofType(Integer.class)
                .map(integer -> integer * 2)
                .subscribe(integer -> System.out.println(integer), throwable -> System.out.println(throwable));
    }

    public class ClassTransformer implements ObservableTransformer<String, Integer> {
        @Override
        public @NonNull ObservableSource<Integer> apply(@NonNull Observable<String> upstream) {
            return upstream.map(s -> Integer.parseInt(s));
        }
    }

    public static void main(String[] args) {
        ComposeOperator operator = new ComposeOperator();
        operator.composeTest();
    }
}
