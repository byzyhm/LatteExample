package com.zlcp.latteec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.lattecore.util.storage.LattePreference;
import com.zlcp.latteec.R;
import com.zlcp.latteui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

public class SearchFragment extends LatteFragment {

    private AppCompatEditText mSearchEdit = null;
    private SearchAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_search;
    }

    //服务器搜索 返回json 然后解析
    private void onClickSearch(final String searchItemText) {
        RestClient.builder()
                .url("search.php?key="+ searchItemText)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        saveItem(searchItemText);
                        mSearchEdit.setText("");
                        //展示一些东西
                        //弹出一段话
                    }
                })
                .build()
                .get();
    }

    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isEmpty(item)) {
            List<String> history;
            final String historyStr =
                    LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        final RecyclerView recyclerView = $(R.id.rv_search);
        final Toolbar mToolbar = $(R.id.tb_search);
        mSearchEdit = $(R.id.et_search_view);
        mSearchEdit.setFocusable(true);
        mSearchEdit.setFocusableInTouchMode(true);
        mSearchEdit.requestFocus();
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        $(R.id.tv_top_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = mSearchEdit.getText().toString();
                onClickSearch(search);
            }
        });
        $(R.id.icon_top_search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportDelegate().pop();
            }
        });
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        mAdapter = new SearchAdapter(data);
        recyclerView.setAdapter(mAdapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });
        recyclerView.addItemDecoration(itemDecoration);
    }
}
