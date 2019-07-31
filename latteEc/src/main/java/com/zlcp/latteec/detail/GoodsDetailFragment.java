package com.zlcp.latteec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.latteec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 00:27
 * Note：
 */
public class GoodsDetailFragment extends LatteFragment {

    public static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    public static GoodsDetailFragment create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
