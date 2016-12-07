package com.changxiao.rxjavaoperatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class DebounceActivity extends AppCompatActivity {

    private static final String TAG = DebounceActivity.class.getSimpleName();

            @Bind(R.id.edt_input)
    EditText edtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce);
        ButterKnife.bind(this);

        // action
        debounce(edtInput);
    }

    /**
     * 操作符：
     * debounce:用简单的话讲就是当N个结点发生的时间太靠近（即发生的时间差小于设定的值T），debounce就会自动过滤掉前N-1个结点。
     *          使用debounce减少频繁的网络请求。避免每输入（删除）一个字就做一次请求
     *
     * @param textView
     */
    private void debounce(TextView textView) {
        RxTextView.textChangeEvents(textView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        Log.d(TAG, String.format("Searching for %s", textViewTextChangeEvent.text().toString()));
                    }
                });
    }
}
