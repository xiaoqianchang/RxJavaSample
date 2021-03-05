package com.chang.rxjava.operators.filtering;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * Description: 只发射最后一项（或者满足某个条件的最后一项）数据
 *
 * 如果你想要的是一个过滤操作符，最好使用TakeLast(1)。
 * <p>
 * Created by Chang.Xiao on 2021/3/5 2:03 PM.
 *
 * @version 1.0
 */
public class LastOperator {

    private void lastTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Single<Integer> last = just.last(0);
        last.subscribe(integer -> System.out.println(integer));

        Maybe<Integer> lastElement = just.lastElement();
        lastElement.subscribe(integer -> System.out.println(integer));

        Single<Integer> lastOrError = just.lastOrError();
        lastOrError.subscribe(integer -> System.out.println(integer));

        // 如果你想要的是一个过滤操作符，最好使用TakeLast(1)。
        Observable<Integer> takeLast = just.takeLast(1);
        takeLast.subscribe(integer -> System.out.println(integer));
    }

    public static void main(String[] args) {
        LastOperator operator = new LastOperator();
        operator.lastTest();
    }
}
