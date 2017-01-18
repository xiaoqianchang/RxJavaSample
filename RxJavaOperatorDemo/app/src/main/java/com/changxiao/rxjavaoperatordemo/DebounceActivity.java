package com.changxiao.rxjavaoperatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
//        textChangesDebounce(edtInput);
        textChangeEventsDebounce(edtInput);
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 操作符：
     * debounce:用简单的话讲就是当N个结点发生的时间太靠近（即发生的时间差小于设定的值T），debounce就会自动过滤掉前N-1个结点。
     *          使用debounce减少频繁的网络请求。避免每输入（删除）一个字就做一次请求
     *
     * @param textView
     */
    private void textChangesDebounce(TextView textView) {
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

    private void textChangeEventsDebounce(TextView textView) {
        RxTextView.textChanges(textView)
                .skip(1) // 跳过第一个默认信号
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(x -> {
            Log.d(TAG, String.format("Searching for %s", x.toString()));
        });
        /*RxTextView.textChanges(textView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.d(TAG, String.format("Searching for %s", charSequence.toString()));
                    }
                });*/
    }
}
