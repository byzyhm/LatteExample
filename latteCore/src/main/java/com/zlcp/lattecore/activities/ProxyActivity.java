package com.zlcp.lattecore.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.zlcp.lattecore.R;
import com.zlcp.lattecore.delegates.LatteDelegate;

import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.SupportActivity;

//import me.yokeyword.fragmentation.ISupportActivity;

/**
 * 作者：zl_freedom
 * 时间：2019/7/21 10:54
 * 功能描述：
 */
public abstract class ProxyActivity extends SupportActivity {

//    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);
//
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
