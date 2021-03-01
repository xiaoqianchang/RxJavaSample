package com.chang.rxjava.operators.filtering;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: $
 * <p>
 * Created by Chang.Xiao on 2021/3/1 7:10 PM.
 *
 * @version 1.0
 */
public class ElementAtOperator {

    /**
     * 传递一个基于0的索引值，它会发射原始Observable数据序列对应索引位置的值，如果你传递给elementAt的值为5，
     * 那么它会发射第六项的数据。
     *
     * 如果你传递的是一个负数，或者原始Observable的数据项数小于index+1，将会抛出一个IndexOutOfBoundsException异常。
     */
    private void elementAtTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        just.elementAt(2)
                .subscribe(integer -> {
                    System.out.println(integer);
                });
    }

    /**
     * 与elementAt的区别是，如果索引值大于数据项数，它会发射一个默认值（通过额外的参数指定），而不是抛出异常。
     * 但是如果你传递一个负数索引值，它仍然会抛出一个IndexOutOfBoundsException异常。
     */
    private void elementAtOrDefault() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        just.elementAt(7, 7)
                .subscribe((integer, throwable) -> {
                    System.out.println(integer);
                });
    }

    public static void main(String[] args) {
        ElementAtOperator operator = new ElementAtOperator();
//        operator.elementAtTest();

        operator.elementAtOrDefault();
    }
}
