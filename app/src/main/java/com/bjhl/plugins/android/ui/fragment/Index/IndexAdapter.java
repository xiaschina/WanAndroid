package com.bjhl.plugins.android.ui.fragment.Index;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseAdapter;
import com.bjhl.plugins.android.bean.NewsModel;
import com.bjhl.plugins.android.ui.activity.NewsDetailActivity;
import com.bjhl.plugins.android.util.DateUtil;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by XIAS on 2018/6/25.
 */

public class IndexAdapter extends BaseAdapter<NewsModel.News> {

    public IndexAdapter(@Nullable List data) {
        super(R.layout.item_index, data);
    }

    @Override
    public void bind(final NewsModel.News helper, final BaseViewHolder item) {
        item.setText(R.id.item_index_origin, String.format(item.itemView.getContext().getResources().getString(R.string.from), helper.author));
        item.setText(R.id.item_index_time, DateUtil.format(helper.publishTime));
        item.setText(R.id.item_index_title, helper.title);
        item.setText(R.id.item_index_super_chapter, String.format(item.itemView.getContext().getResources().getString(R.string.belong), helper.superChapterName));
        item.setImageResource(R.id.item_index_collect, helper.collect ? R.drawable.collect : R.drawable.collect_default);
        item.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(item.itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, helper.link);
                item.itemView.getContext().startActivity(intent);
            }
        });
    }
}
