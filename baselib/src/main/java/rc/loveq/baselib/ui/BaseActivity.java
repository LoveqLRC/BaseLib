package rc.loveq.baselib.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import rc.loveq.baselib.R;


/**
 * Author：Rc
 * 0n 2018/1/11 21:57
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
        initData();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    protected void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException();
        }
        setSupportActionBar(mToolbar);
    }
}
