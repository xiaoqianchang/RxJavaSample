package com.chang.rxjava.operators.transforming;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description: 重载方法较多，较难掌握
 * <p>
 * Created by Chang.Xiao on 2021/2/26 2:29 PM.
 *
 * @version 1.0
 */
public class BufferOperator {

    private void bufferTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<List<Integer>> buffer = just.buffer(2);
        buffer.subscribe(item -> {
            System.out.println(item);
        });
    }

    /**
     * 发射的buffer缓存可能会有重叠部分（比如skip < count时），也可能会有间隙（比如skip > count时）。
     */
    private void bufferTest2() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<List<Integer>> buffer = just.buffer(2, 3);
        buffer.subscribe(item -> {
            System.out.println(item);
        });
    }

    public static void main(String[] args) {
        BufferOperator operator = new BufferOperator();
//        operator.bufferTest();
        /*
        输出
        [1, 2]
        [3, 4]
        [5, 6]
         */

        operator.bufferTest2();
        /*
        输出
        [1, 2]
        [4, 5]
         */
    }
}
