package com.chang.rxjava.operators.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

/**
 * Description: 对 Observable 发射的每一项数据应用一个函数，执行变换操作。
 * <p>
 * Created by Chang.Xiao on 2021/2/26 5:36 PM.
 *
 * @version 1.0
 */
public class MapOperator {

    /**
     * 对 Observable 发射的每一项数据应用一个函数，执行变换操作
     */
    private void mapTest() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<String> map = just.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                return integer + " map";
            }
        });
        map.subscribe(s -> {
           System.out.println(s);
        });
    }

    public static void main(String[] args) {
        MapOperator operator = new MapOperator();
        operator.mapTest();
    }
}
