package com.dashuf.disp.mvp.model.entity;

import java.util.List;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class HomeBean {

    private List<BannerBean> banner;
    private List<EasyProductBean> easyProduct;
    private List<NewProductBean> newProduct;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<EasyProductBean> getEasyProduct() {
        return easyProduct;
    }

    public void setEasyProduct(List<EasyProductBean> easyProduct) {
        this.easyProduct = easyProduct;
    }

    public List<NewProductBean> getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(List<NewProductBean> newProduct) {
        this.newProduct = newProduct;
    }

    public static class BannerBean {
        /**
         * img : https://disp-api-sit2.banketech.com/api/show/image/2018-03-13/RESftpJNQKBJwaaGZN37Ik0vd.png
         * endDate : 2018-03-31
         * link : http://pxy-disp-sit2.banketech.com/app/bankeAppPages/view/poster.html#/
         * sort : 1
         * startDate : 2018-03-01
         */

        private String img;
        private String endDate;
        private String link;
        private String sort;
        private String startDate;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }

    public static class EasyProductBean {
        /**
         * img : https://disp-api-sit2.banketech.com/api/show/image/2018-03-13/RESdfvlLdqUMKFi0dBbUMn6Rz.png
         * link : https://www.baidu.com/
         * title1 : 标签一
         * title2 : 标签二
         * sort : 1
         * title3 : 标签三
         */

        private String img;
        private String link;
        private String title1;
        private String title2;
        private String sort;
        private String title3;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle3() {
            return title3;
        }

        public void setTitle3(String title3) {
            this.title3 = title3;
        }
    }

    public static class NewProductBean {
        /**
         * img : https://disp-api-sit2.banketech.com/api/show/image/2018-03-13/RESfJYufOQltX0hyU5MRyYhXq.png
         * title1Color : AC89ED
         * title2Color : DABB88
         * mainTitle : 主标题
         * link : https://www.baidu.com/
         * viceTitle : 副标题
         * title1 : 标签一
         * title2 : 标签二
         * sort : 5
         * title3 : 标签三
         * title4 : 标签四
         */

        private String img;
        private String title1Color;
        private String title2Color;
        private String mainTitle;
        private String link;
        private String viceTitle;
        private String title1;
        private String title2;
        private String sort;
        private String title3;
        private String title4;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle1Color() {
            return title1Color;
        }

        public void setTitle1Color(String title1Color) {
            this.title1Color = title1Color;
        }

        public String getTitle2Color() {
            return title2Color;
        }

        public void setTitle2Color(String title2Color) {
            this.title2Color = title2Color;
        }

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getViceTitle() {
            return viceTitle;
        }

        public void setViceTitle(String viceTitle) {
            this.viceTitle = viceTitle;
        }

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle3() {
            return title3;
        }

        public void setTitle3(String title3) {
            this.title3 = title3;
        }

        public String getTitle4() {
            return title4;
        }

        public void setTitle4(String title4) {
            this.title4 = title4;
        }
    }

}
