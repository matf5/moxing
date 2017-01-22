package com.moxing.ssm.model;

/**
 * Created by mtf on 2017/1/6.
 */
public class CosJsonBean {
    /**
     * code : 0
     * message : SUCCESS
     * request_id : NTg2ZjJhNDhfNDA0MTcwXzdhY19kZWNmNw==
     * data : {"access_url":"http://moxing1-1251789093.file.myqcloud.com/201701061325466119455696.jpg","resource_path":"/1251789093/moxing1/201701061325466119455696.jpg","source_url":"http://moxing1-1251789093.cosgz.myqcloud.com/201701061325466119455696.jpg","url":"http://gz.file.myqcloud.com/files/v2/1251789093/moxing1/201701061325466119455696.jpg","vid":"85cb28a93e024831600fde49a1613a17"}
     */

    private int code;
    private String message;
    private String request_id;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * access_url : http://moxing1-1251789093.file.myqcloud.com/201701061325466119455696.jpg
         * resource_path : /1251789093/moxing1/201701061325466119455696.jpg
         * source_url : http://moxing1-1251789093.cosgz.myqcloud.com/201701061325466119455696.jpg
         * url : http://gz.file.myqcloud.com/files/v2/1251789093/moxing1/201701061325466119455696.jpg
         * vid : 85cb28a93e024831600fde49a1613a17
         */

        private String access_url;
        private String resource_path;
        private String source_url;
        private String url;
        private String vid;

        public String getAccess_url() {
            return access_url;
        }

        public void setAccess_url(String access_url) {
            this.access_url = access_url;
        }

        public String getResource_path() {
            return resource_path;
        }

        public void setResource_path(String resource_path) {
            this.resource_path = resource_path;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }
    }
}
