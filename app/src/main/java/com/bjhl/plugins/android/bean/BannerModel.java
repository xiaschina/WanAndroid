package com.bjhl.plugins.android.bean;

import java.io.Serializable;

/**
 * Created by XIAS on 2018/6/21.
 */

public class BannerModel implements Serializable{

    private Banner data;

    public Banner getData() {
        return data;
    }

    public void setData(Banner data) {
        this.data = data;
    }

    public static class Banner implements Serializable{

        private String desc;  //tip
        private int id;
        private String imagePath; //图片地址
        private int isVisible;
        private int order;
        private String title;  //内容
        private int type;
        private String url; //跳转地址

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
