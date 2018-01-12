package rc.loveq.baselib.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

import rc.loveq.baselib.ui.adapter.base.ItemViewDelegate;
import rc.loveq.baselib.ui.adapter.base.ViewHolder;

/**
 * Created by rc on 2018/1/10.
 * Description:
 */

public abstract class BaseAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public BaseAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                BaseAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

}
