package com.bjhl.plugins.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XIAS on 2018/6/22.
 */

public class NewsModel implements ListData, Serializable {

    public List<News> datas;

    public boolean over;

    @Override
    public List<?> getList() {
        return datas;
    }

    @Override
    public boolean isOver() {
        return over;
    }

    public static class News implements Serializable {
        public String apkLink;
        public String author;
        public String chapterId;
        public String chapterName;
        public boolean collect;
        public String courseId;
        public String desc;
        public String envelopePic;
        public String fresh;
        public String id;
        public String link;
        public String niceDate;
        public String origin;
        public String projectLink;
        public long publishTime;
        public String superChapterId;
        public String superChapterName;
        public String title;
        public String type;
        public String userId;
        public String visible;
        public String zan;
        public String[] tags;
    }
}
