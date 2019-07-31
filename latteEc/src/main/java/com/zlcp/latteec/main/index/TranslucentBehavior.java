package com.zlcp.latteec.main.index;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.zlcp.lattecore.app.Latte;
import com.zlcp.latteec.R;

/**
 * 作者：zl_freedom
 * 时间：2019/7/30 10:56
 * Note：fragment_index.xml中被引用
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;

    //一定要有两个参数的构造方法，否则报错
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //要依赖的View
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull Toolbar child,
            @NonNull View directTargetChild,
            @NonNull View target,
            int axes,
            int type) {
        return true;
    }

    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull Toolbar toolbar,
            @NonNull View target,
            int dxConsumed,
            int dyConsumed,
            int dxUnconsumed,
            int dyUnconsumed,
            int type) {
        final int startOffset = 0;
        final Context context = Latte.getApplicationContext();
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
        mOffset += dyConsumed;
        if (mOffset <= startOffset) {
            toolbar.getBackground().setAlpha(0);
        } else if (mOffset < endOffset) {
            final float percent = (float) (mOffset - startOffset) / endOffset;
            final int alpha = Math.round(percent * 255);
            toolbar.getBackground().setAlpha(alpha);
        } else if (mOffset >= endOffset) {
            toolbar.getBackground().setAlpha(255);
        }

    }
}
