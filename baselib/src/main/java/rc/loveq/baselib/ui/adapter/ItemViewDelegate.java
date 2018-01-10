package rc.loveq.baselib.ui.adapter;

/**
 * Created by rc on 2018/1/10.
 * Description:
 */

public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
