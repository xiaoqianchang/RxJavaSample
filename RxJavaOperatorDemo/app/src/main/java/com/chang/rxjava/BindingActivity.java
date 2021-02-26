package com.chang.rxjava;

import com.chang.rxjava.databinding.ActivityBindingBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.appcompat.RxToolbar;
import com.jakewharton.rxbinding4.material.RxSnackbar;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BindingActivity extends AppCompatActivity {

    private ActivityBindingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBindingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initToolbar(); // 初始化Toolbar
        initFabButton(); // 初始化Fab按钮
        initEditText(); // 初始化编辑文本
    }

    // 初始化Toolbar
    private void initToolbar() {
        // 添加菜单按钮
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        // 添加浏览按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RxToolbar.itemClicks(mBinding.toolbar).subscribe(this::onToolbarItemClicked);

        RxToolbar.navigationClicks(mBinding.toolbar).subscribe(this::onToolbarNavigationClicked);
    }

    // 点击Toolbar的项
    private void onToolbarItemClicked(MenuItem menuItem) {
        String m = "点击\"" + menuItem.getTitle() + "\"";
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }

    // 浏览点击
    private void onToolbarNavigationClicked(Unit unit) {
        Toast.makeText(this, "浏览点击", Toast.LENGTH_SHORT).show();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_rxbinding, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 初始化Fab按钮
    private void initFabButton() {
        RxView.clicks(mBinding.fab).subscribe(this::onFabClicked);
    }

    // 点击Fab按钮
    private void onFabClicked(Unit unit) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "点击Snackbar", Snackbar.LENGTH_SHORT);
        snackbar.show();
        RxSnackbar.dismisses(snackbar).subscribe(this::onSnackbarDismissed);
    }

    // 销毁Snackbar, event参考{Snackbar}
    private void onSnackbarDismissed(int event) {
        String text = "Snackbar消失代码:" + event;
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    // 初始化编辑文本
    private void initEditText() {
        // 正常方式
        mBinding.mainContent.rxbindingEtUsualApproach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBinding.mainContent.rxbindingTvShow.setText(s);
            }

            @Override public void afterTextChanged(Editable s) {

            }

        });

        // Rx方式
        RxTextView.textChanges(mBinding.mainContent.rxbindingEtReactiveApproach).subscribe(mBinding.mainContent.rxbindingTvShow::setText);
    }
}
