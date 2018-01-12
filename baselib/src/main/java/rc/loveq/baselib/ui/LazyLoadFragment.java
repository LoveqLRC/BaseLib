package rc.loveq.baselib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by rc on 2018/1/12.
 * Description:
 */

public abstract class LazyLoadFragment extends BaseFragment {
    private boolean mIsViewCreated = false;
    private boolean mIsVisibleToUser = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisibleToUser = true;
            lazyLoad();
        } else {
            mIsVisibleToUser = false;
        }
    }

    private void lazyLoad() {
        if (mIsViewCreated && mIsVisibleToUser) {
            lazyLoadData();
            mIsVisibleToUser = false;
            mIsViewCreated = false;
        }
    }

    @Override
    protected final void initData() {

    }

    protected abstract void lazyLoadData();


}
