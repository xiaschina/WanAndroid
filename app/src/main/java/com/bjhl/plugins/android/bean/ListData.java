package com.bjhl.plugins.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XIAS on 2018/6/25.
 */

public interface ListData extends Serializable {
    /**
     * 获取列表
     *
     * @return
     */
    List<?> getList();

    boolean isOver();
}
