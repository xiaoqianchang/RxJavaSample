package com.chang.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import com.chang.rxjava.databinding.ActivityDebounceBinding;
import com.jakewharton.rxbinding4.widget.RxTextView;
import com.jakewharton.rxbinding4.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

public class DebounceActivity extends AppCompatActivity {

    private static final String TAG = DebounceActivity.class.getSimpleName();

    private ActivityDebounceBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDebounceBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // action
//        textChangesDebounce(edtInput);
        textChangeEventsDebounce(mBinding.edtInput);
        mBinding.edtInput.addTextChangedListener(new TextWatcher() {
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
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(TextViewTextChangeEvent textViewTextChangeEvent) throws Throwable {
                        Log.d(TAG, String.format("Searching for %s", textViewTextChangeEvent.getText().toString()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, "onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d(TAG, "onCompleted");
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
