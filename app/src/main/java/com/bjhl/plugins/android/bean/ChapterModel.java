package com.bjhl.plugins.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XIAS on 2018/6/25.
 */

public class ChapterModel implements Serializable {
    public String courseId;
    public String id;
    public String name;
    public String order;
    public String parentChapterId;
    public String visible;
    public List<ChapterModel> children;
}
