package com.zlcp.latteec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 23:02
 * Note：内容数据集合{头部+商品+商品+商品+头部2+商品+...}，一个recycler搞定
 */
public class SectionDataConverter {
    final List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title 这里定义分组头head和分组内content
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodId = contentItem.getInteger("goods_id");
                final String goodName = contentItem.getString("goods_name");
                final String goodThumb = contentItem.getString("goods_thumb");

                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodId);
                itemEntity.setGoodsName(goodName);
                itemEntity.setGoodsThumb(goodThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束

        }
        return dataList;
    }
}
