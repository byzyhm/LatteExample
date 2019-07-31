package com.zlcp.latteec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.latteec.R;
import com.zlcp.latteec.main.sort.SortFragment;
import com.zlcp.latteui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 17:59
 * Note：
 */
public class VerticalListFragment extends LatteFragment {
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.fragment_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        mRecyclerView = $(R.id.rv_vertical_menu_list);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortFragment fragment = getParentFragments();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, fragment);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
