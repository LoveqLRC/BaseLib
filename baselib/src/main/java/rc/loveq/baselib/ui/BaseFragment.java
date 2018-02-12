package rc.loveq.baselib.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rc.loveq.baselib.R;
import rc.loveq.baselib.ui.mvp.MvpView;
import rc.loveq.baselib.utils.CommonUtils;

/**
 * Author：Rc
 * 0n 2018/1/12 06:55
 */

public abstract class BaseFragment extends Fragment implements MvpView {
    protected BaseActivity mActivity;
    Unbinder unbinder;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof BaseActivity) {
            mActivity = (BaseActivity) getActivity();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    public String getFragmentTag() {
        return this.getClass().getName();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
