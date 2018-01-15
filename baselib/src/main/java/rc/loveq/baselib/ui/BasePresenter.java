package rc.loveq.baselib.ui;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import rc.loveq.baselib.ui.mvp.MvpPresenter;
import rc.loveq.baselib.ui.mvp.MvpView;
import rc.loveq.baselib.ui.rxlifecycle.PresenterEvent;
import rc.loveq.baselib.ui.rxlifecycle.RxLifecyclePresenter;

/**
 * Author：Rc
 * 0n 2018/1/15 21:33
 */

public class BasePresenter <V extends MvpView> implements MvpPresenter<V>, LifecycleProvider<PresenterEvent> {
    private final BehaviorSubject<PresenterEvent> lifecycleSubject = BehaviorSubject.create();
    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        lifecycleSubject.onNext(PresenterEvent.ATTACH);
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(PresenterEvent.DETACH);
        mMvpView = null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public Observable<PresenterEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(PresenterEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecyclePresenter.bindPresenter(lifecycleSubject);
    }
}
