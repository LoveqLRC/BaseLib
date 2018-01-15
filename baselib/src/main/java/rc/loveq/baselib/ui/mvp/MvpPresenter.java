package rc.loveq.baselib.ui.mvp;

/**
 * Author：Rc
 * 0n 2018/1/15 21:25
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();
}
