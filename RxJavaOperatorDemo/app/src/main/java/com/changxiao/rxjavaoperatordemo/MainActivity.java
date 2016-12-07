package com.changxiao.rxjavaoperatordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
         * 观察者的创建方式
         */
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        /**
         * 观察者Observer的抽象实现(订阅者)
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        /**
         * 被观察者(事件源)创建方式
         */
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++)
                            subscriber.onNext("1");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        /**
         * 观察者与被观察者关联在一起
         */
        stringObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

    }

    /**
     * openGL三要点：
     * 1.公理：向量与坐标系无关，向量的表示与坐标系有关。
     * 2.R = Mr---(M为矩阵)
     * 3.drawCircle1-canvas.save()-transformMatrix()-drawCircle2-canvas.restore()
     */
    private void fromUsage() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable<Integer> from = Observable.from(items);
        from.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, String.valueOf(integer));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "Error encountered: " + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "Sequence complete");
            }
        });
    }

}
