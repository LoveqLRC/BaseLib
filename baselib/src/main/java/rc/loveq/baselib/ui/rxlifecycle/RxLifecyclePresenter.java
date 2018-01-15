package rc.loveq.baselib.ui.rxlifecycle;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Author：Rc
 * 0n 2018/1/15 21:29
 */

public class RxLifecyclePresenter {
    private RxLifecyclePresenter() {
        throw new AssertionError("No instances");
    }

    @NonNull
    public static <T> LifecycleTransformer<T> bindPresenter(@NonNull final Observable<PresenterEvent> lifecycle) {
        return RxLifecycle.bind(lifecycle, PRESENTER_LIFECYCLE);
    }

    private static final Function<PresenterEvent, PresenterEvent> PRESENTER_LIFECYCLE =
            event -> {
                switch (event) {
                    case ATTACH:
                        return PresenterEvent.DETACH;
                    case DETACH:
                        throw new OutsideLifecycleException("Cannot bind to Presenter lifecycle when outside of it.");
                    default:
                        throw new UnsupportedOperationException("Binding to " + event + " not yet implemented");

                }
            };

}
