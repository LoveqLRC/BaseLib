package rc.loveq.baselib.ui;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by rc on 2018/2/12.
 * Description:
 * Fragment管理类 提供fragment的常用操作
 */

public class FragmentManagerHelper {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;

    /**
     * 构造函数
     *
     * @param fragmentManager 管理类FragmentManager
     * @param containerViewId 容器布局id containerViewId
     */
    public FragmentManagerHelper(@Nullable FragmentManager fragmentManager,
                                 @IdRes int containerViewId) {
        mFragmentManager = fragmentManager;
        mContainerViewId = containerViewId;
    }

    public void add(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(mContainerViewId, fragment);
        transaction.commit();
    }

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //隐藏当前所有的Fragment
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment childFragment : fragments) {
            transaction.hide(childFragment);
        }
        //如果容器里没有就添加，否则显示
        if (!fragments.contains(fragment)) {
            transaction.add(mContainerViewId, fragment);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();

    }
}
