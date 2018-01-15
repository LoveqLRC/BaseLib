package rc.loveq.baselib.ui.mvp;

/**
 * Author：Rc
 * 0n 2018/1/15 21:24
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void showMessage(String message);
}
