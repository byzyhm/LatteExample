package com.zlcp.latteec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.latteec.main.personal.list.ListBean;

/**
 * 作者：zl_freedom
 * 时间：2019/8/3 23:38
 * Note：
 */
public class PersonalClickListener extends SimpleClickListener {

    private final LatteFragment FRAGMENT;

    public PersonalClickListener(LatteFragment fragment) {
        this.FRAGMENT = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                FRAGMENT.getParentFragments().getSupportDelegate().start(bean.getFragment());
                break;
            case 2:
                FRAGMENT.getParentFragments().getSupportDelegate().start(bean.getFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
