package rc.loveq.baselib.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import rc.loveq.baselib.R;
import rc.loveq.baselib.ui.mvp.MvpView;
import rc.loveq.baselib.utils.CommonUtils;


/**
 * Author：Rc
 * 0n 2018/1/11 21:57
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    protected Toolbar mToolbar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initTitle();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = getMenuId();
        if (menuId == -1) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(menuId, menu);
        return true;
    }

    protected @MenuRes
    int getMenuId() {
        return -1;
    }

    // 设置布局layoutID
    protected abstract @LayoutRes int getLayoutId();
    
    // 初始化界面
    protected abstract void initView(Bundle savedInstanceState);

    // 初始化头部
    protected abstract void initTitle();

    // 初始化数据
    protected abstract void initData();

    protected void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException();
        }
        setSupportActionBar(mToolbar);
    }

    protected void hideToolbarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

