package com.chang.rxjava;

import android.content.Intent;
import android.os.Bundle;

import com.chang.rxjava.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.view.RxView;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setSupportActionBar(mBinding.toolbar);

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        actionExercise();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = null;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_observable:
                observableUsage();
                break;
            case R.id.action_debounce:
                intent = new Intent(this, DebounceActivity.class);
                startActivity(intent);
                break;
            case R.id.action_combineLatest:
                intent = new Intent(this, CombineLatestActivity.class);
                startActivity(intent);
                break;
            case R.id.action_from:
                fromUsage();
                break;
            case R.id.action_just:
                justUsage();
                break;
            case R.id.action_interval:
                intervalUsage();
//                timerUsage();
                break;
            case R.id.action_listView:
                intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionExercise() {
        /**
         * 这些操作符默认不在任何特定的调度器上执行
         */
        Observable.empty(); // 创建一个不发射任何数据但是正常终止的Observable
        Observable.never(); // 创建一个不发射数据也不终止的Observable
        Observable.error(new Exception("error")); // 创建一个不发射数据以一个错误终止的Observable
    }

    /**
     * 观察者与被观察者的创建、关联方式
     */
    private void observableUsage() {
        /**
         * 被观察者(事件源)创建方式
         */
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                try {
                    if (!emitter.isDisposed()) {
                        for (int i = 0; i < 5; i++)
                            emitter.onNext(String.valueOf(i));
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

        /**
         * 观察者的创建方式
         */
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "Observer-onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "Observer-onNext-" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Observer-onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Observer-onComplete");
            }
        };
        /**
         * 观察者Observer的抽象实现(订阅者)
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "Subscriber-onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Subscriber-onNext-" + s);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "Subscriber-onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Subscriber-onComplete");
            }
        };

        // 关联方式一
//        stringObservable.subscribe(observer);
        // 关联方式二
        stringObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "Consumer-onNext-" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.d(TAG, "Consumer-onError");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                Log.d(TAG, "Consumer-onComplete");
            }
        });
    }

    /**
     * openGL三要点：
     * 1.公理：向量与坐标系无关，向量的表示与坐标系有关。
     * 2.R = Mr---(M为矩阵)
     * 3.drawCircle1-canvas.save()-transformMatrix()-drawCircle2-canvas.restore()
     *
     * from用法:
     * From将数组或Iterable的数据取出然后逐个发射。
     */
    private void fromUsage() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable<Integer> from = Observable.fromArray(items);
        from.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, String.valueOf(integer));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.d(TAG, "Error encountered: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                Log.d(TAG, "complete");
            }
        });
    }

    /**
     * just用法:
     * Just只是简单的原样发射，将数组或Iterable当做单个数据。
     */
    private void justUsage() {
        Observable<Integer> just = Observable.just(1, 2, 3);
        just.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d(TAG, String.valueOf(integer));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.d(TAG, "Error encountered: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() {
                Log.d(TAG, "complete");
            }
        });
    }

    /**
     * interval用法:
     * 创建一个按固定时间间隔发射整数序列的Observable
     */
    private void intervalUsage() {
        Observable<Long> interval = Observable.interval(0, 2, TimeUnit.SECONDS);
        interval.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d(TAG, String.valueOf(aLong));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.d(TAG, "Error encountered: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() {
                Log.d(TAG, "complete");
            }
        });
    }

    /**
     * timer用法:
     * 在一个给定的延迟后发射一个特殊的值。
     */
    private void timerUsage() {
        Observable<Long> timer = Observable.timer(1000, TimeUnit.MILLISECONDS);
        timer.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.d(TAG, String.valueOf(aLong));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.d(TAG, "Error encountered: " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() {
                Log.d(TAG, "complete");
            }
        });
    }

    private void mapUsage() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

            }
        });
    }

    /**
     * 在每次事件触发后的一定时间间隔内丢弃新的事件。常用作去抖动过滤(防止重复点击)
     */
    private void throttleFirstUsage() {
        RxView.clicks(mBinding.fab)
                .throttleFirst(1, TimeUnit.SECONDS)
//        .subscribe(new Consumer<Unit>() {
//            @Override
//            public void accept(Unit unit) throws Throwable {
//
//            }
//        });
        .subscribe(aVoid -> {});
    }

    private void clickEventsUsage() {
    }

}
