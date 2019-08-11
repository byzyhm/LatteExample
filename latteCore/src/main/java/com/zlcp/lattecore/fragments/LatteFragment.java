package com.zlcp.lattecore.fragments;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 作者：zl_freedom
 * 时间：2019/7/21 17:42
 * 功能描述：
 */
public abstract class LatteFragment extends PermissionCheckFragment {
    @SuppressWarnings("unchecked")
    public <T extends LatteFragment> T getParentFragments() {
        return (T) getParentFragment();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
