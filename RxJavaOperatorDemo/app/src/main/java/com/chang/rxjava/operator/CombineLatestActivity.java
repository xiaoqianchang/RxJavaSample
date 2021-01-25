package com.chang.rxjava.operator;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function3;

import android.os.Bundle;
import android.util.Log;

import com.chang.rxjava.operator.databinding.ActivityCombineLatestBinding;
import com.jakewharton.rxbinding4.widget.RxTextView;

import static android.text.TextUtils.isEmpty;

public class CombineLatestActivity extends AppCompatActivity {

    private static final String TAG  = CombineLatestActivity.class.getSimpleName();

    private ActivityCombineLatestBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCombineLatestBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        combineLatestUsage();
    }

    /**
     * combineLatest 用法：使用combineLatest合并最近N个结点
     *
     */
    private void combineLatestUsage() {
        Observable<CharSequence> edtUserNameChangeObservable = RxTextView.textChanges(mBinding.edtUserName).skip(1);
        Observable<CharSequence> edtAuthCodeChangeObservable = RxTextView.textChanges(mBinding.edtAuthCode).skip(1);
        Observable<CharSequence> edtUserPassChangeObservable = RxTextView.textChanges(mBinding.edtUserPass).skip(1);

        Observable.combineLatest(
                edtUserNameChangeObservable,
                edtAuthCodeChangeObservable,
                edtUserPassChangeObservable,
                new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence newUserName, CharSequence newAuthCode, CharSequence newUserPass) throws Throwable {
                Log.d(TAG, newUserName + " " + newAuthCode + " " + newUserPass);
                //                        boolean emailValid = !isEmpty(newUserName) &&
                //                                EMAIL_ADDRESS.matcher(newUserName).matches();
                boolean emailValid = !isEmpty(newUserName);
                if (!emailValid) {
                    mBinding.edtUserName.setError("Invalid UserName!");
                }

                //                        boolean passValid = !isEmpty(newAuthCode) && newAuthCode.length() > 8;
                boolean passValid = !isEmpty(newAuthCode);
                if (!passValid) {
                    mBinding.edtAuthCode.setError("Invalid AuthCode!");
                }

                boolean numValid = !isEmpty(newUserPass);
                if (!numValid) {
                    mBinding.edtUserPass.setError("Invalid Password!");
                }

                return emailValid && passValid && numValid;
            }
        })
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                mBinding.btnRegister.setEnabled(aBoolean);
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
}
