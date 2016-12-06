package com.changxiao.rxjavaoperatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func3;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;
import static com.jakewharton.rxbinding.widget.RxTextView.textChanges;

public class CombineLatestActivity extends AppCompatActivity {

    private static final String TAG  = CombineLatestActivity.class.getSimpleName();

    @Bind(R.id.edt_user_name)
    EditText edtUserName;

    @Bind(R.id.edt_auth_code)
    EditText edtAuthCode;

    @Bind(R.id.edt_user_pass)
    EditText edtUserPass;

    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_latest);
        ButterKnife.bind(this);

        Observable<CharSequence> edtUserNameChangeObservable = RxTextView.textChanges(edtUserName).skip(1);
        Observable<CharSequence> edtAuthCodeChangeObservable = RxTextView.textChanges(edtAuthCode).skip(1);
        Observable<CharSequence> edtUserPassChangeObservable = RxTextView.textChanges(edtUserPass).skip(1);

        Observable.combineLatest(
                edtUserNameChangeObservable,
                edtAuthCodeChangeObservable,
                edtUserPassChangeObservable,
                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence newUserName, CharSequence newAuthCode, CharSequence newUserPass) {
                        Log.d(TAG, newUserName + " " + newAuthCode + " " + newUserPass);
//                        boolean emailValid = !isEmpty(newUserName) &&
//                                EMAIL_ADDRESS.matcher(newUserName).matches();
                        boolean emailValid = !isEmpty(newUserName);
                        if (!emailValid) {
                            edtUserName.setError("Invalid UserName!");
                        }

//                        boolean passValid = !isEmpty(newAuthCode) && newAuthCode.length() > 8;
                        boolean passValid = !isEmpty(newAuthCode);
                        if (!passValid) {
                            edtAuthCode.setError("Invalid AuthCode!");
                        }

                        boolean numValid = !isEmpty(newUserPass);
                        if (!numValid) {
                            edtUserPass.setError("Invalid Password!");
                        }

                        return emailValid && passValid && numValid;
                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        btnRegister.setEnabled(aBoolean);
                    }
                });

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

        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onCompleted();
            }
        });

        Observable<String> just = Observable.just("1", "2");
    }
}
