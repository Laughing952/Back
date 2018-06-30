package com.laughing.android.retrofitlaughing.resp;

import java.util.List;

/**
 * 作者：Laughing on 2018/6/4 18:39
 * 邮箱：719240226@qq.com
 */

public class GetRep {

    /**
     * error : false
     * results : s
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5b10bce8421aa966feb27798
         * createdAt : 2018-06-01T11:26:32.532Z
         * desc : 基于react-natice实现的SmartRefreshLayout,可提供类似ios的弹性刷新、加载。
         * images : ["http://img.gank.io/647c81bb-5bc0-4e7f-9d4e-d2ebfda9a3d2","http://img.gank.io/1023da2e-9727-464d-a389-542eef0c182b"]
         * publishedAt : 2018-06-04T00:00:00.0Z
         * source : chrome
         * type : Android
         * url : https://github.com/react-native-studio/react-native-SmartRefreshLayout
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }


}
