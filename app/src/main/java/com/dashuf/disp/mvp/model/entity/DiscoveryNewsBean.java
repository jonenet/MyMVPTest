package com.dashuf.disp.mvp.model.entity;

import java.util.List;

/**
 * Created by ex-zhoulai on 2018/5/31.
 */

public class DiscoveryNewsBean {

    private List<PositionTopBean> PositionTop;
    private List<HotSpotsBean> HotSpots;
    private List<HotInfoBean> HotInfo;
    private List<DSClassroomBean> DSClassroom;
    private List<ProductExchangeBean> ProductExchange;

    public List<PositionTopBean> getPositionTop() {
        return PositionTop;
    }

    public void setPositionTop(List<PositionTopBean> PositionTop) {
        this.PositionTop = PositionTop;
    }

    public List<HotSpotsBean> getHotSpots() {
        return HotSpots;
    }

    public void setHotSpots(List<HotSpotsBean> HotSpots) {
        this.HotSpots = HotSpots;
    }

    public List<HotInfoBean> getHotInfo() {
        return HotInfo;
    }

    public void setHotInfo(List<HotInfoBean> HotInfo) {
        this.HotInfo = HotInfo;
    }

    public List<DSClassroomBean> getDSClassroom() {
        return DSClassroom;
    }

    public void setDSClassroom(List<DSClassroomBean> DSClassroom) {
        this.DSClassroom = DSClassroom;
    }

    public List<ProductExchangeBean> getProductExchange() {
        return ProductExchange;
    }

    public void setProductExchange(List<ProductExchangeBean> ProductExchange) {
        this.ProductExchange = ProductExchange;
    }

    public static class PositionTopBean {
        /**
         * id : 8ac0811d62222f2c0162223a61cc0001
         * createDate : 1520992674000
         * updateDate : 1521007333000
         * deleteFlag : 0
         * auditFlag : null
         * columnInfo : null
         * rootColumnInfo : null
         * type : 0
         * title : 置顶3
         * content : null
         * summary : null
         * sourceFrom : null
         * publisher : null
         * href : http://news.cctv.com/2018/03/13/ARTIkNK3EcN2TuJP6PIMBlLT180313.shtml
         * coverImageUrl : https://cdn-tc-st.banketech.com/discover/107c639f-837b-4cf0-9f2f-b48a29ce5770.png
         * coverImageUrlTwo :
         * isTop : null
         * articlePosition : 1
         * isAudit : true
         * viewCount : 0
         * likeCount : null
         * forwardCount : null
         * abstractContent : 置顶3
         * cityIds :
         * orderNo : 2
         * effectDate : 1520992664000
         * failureDate : 1522512000000
         */

        private String id;
        private long createDate;
        private long updateDate;
        private String deleteFlag;
        private Object auditFlag;
        private Object columnInfo;
        private Object rootColumnInfo;
        private int type;
        private String title;
        private Object content;
        private Object summary;
        private Object sourceFrom;
        private Object publisher;
        private String href;
        private String coverImageUrl;
        private String coverImageUrlTwo;
        private Object isTop;
        private String articlePosition;
        private boolean isAudit;
        private int viewCount;
        private Object likeCount;
        private Object forwardCount;
        private String abstractContent;
        private String cityIds;
        private int orderNo;
        private long effectDate;
        private long failureDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getAuditFlag() {
            return auditFlag;
        }

        public void setAuditFlag(Object auditFlag) {
            this.auditFlag = auditFlag;
        }

        public Object getColumnInfo() {
            return columnInfo;
        }

        public void setColumnInfo(Object columnInfo) {
            this.columnInfo = columnInfo;
        }

        public Object getRootColumnInfo() {
            return rootColumnInfo;
        }

        public void setRootColumnInfo(Object rootColumnInfo) {
            this.rootColumnInfo = rootColumnInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getSourceFrom() {
            return sourceFrom;
        }

        public void setSourceFrom(Object sourceFrom) {
            this.sourceFrom = sourceFrom;
        }

        public Object getPublisher() {
            return publisher;
        }

        public void setPublisher(Object publisher) {
            this.publisher = publisher;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getCoverImageUrlTwo() {
            return coverImageUrlTwo;
        }

        public void setCoverImageUrlTwo(String coverImageUrlTwo) {
            this.coverImageUrlTwo = coverImageUrlTwo;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getArticlePosition() {
            return articlePosition;
        }

        public void setArticlePosition(String articlePosition) {
            this.articlePosition = articlePosition;
        }

        public boolean isIsAudit() {
            return isAudit;
        }

        public void setIsAudit(boolean isAudit) {
            this.isAudit = isAudit;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }

        public Object getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Object forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getAbstractContent() {
            return abstractContent;
        }

        public void setAbstractContent(String abstractContent) {
            this.abstractContent = abstractContent;
        }

        public String getCityIds() {
            return cityIds;
        }

        public void setCityIds(String cityIds) {
            this.cityIds = cityIds;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public long getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(long effectDate) {
            this.effectDate = effectDate;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }
    }

    public static class HotSpotsBean {
        /**
         * id : 8aa8095e6223808e0162238b9e2d0001
         * createDate : 1521014775000
         * updateDate : 1521014775000
         * deleteFlag : 0
         * auditFlag : null
         * columnInfo : null
         * rootColumnInfo : null
         * type : 0
         * title : 热点活动2
         * content : null
         * summary : null
         * sourceFrom : null
         * publisher : null
         * href : http://industry.caijing.com.cn/20180313/4417094.shtml
         * coverImageUrl : https://cdn-tc-st.banketech.com/discover/072ebea8-b132-403e-96b1-13f64909fb76.png
         * coverImageUrlTwo :
         * isTop : null
         * articlePosition : 5
         * isAudit : true
         * viewCount : 0
         * likeCount : null
         * forwardCount : null
         * abstractContent : 热点活动2
         * cityIds :
         * orderNo : 2
         * effectDate : 1519833600000
         * failureDate : 1523030400000
         */

        private String id;
        private long createDate;
        private long updateDate;
        private String deleteFlag;
        private Object auditFlag;
        private Object columnInfo;
        private Object rootColumnInfo;
        private int type;
        private String title;
        private Object content;
        private Object summary;
        private Object sourceFrom;
        private Object publisher;
        private String href;
        private String coverImageUrl;
        private String coverImageUrlTwo;
        private Object isTop;
        private String articlePosition;
        private boolean isAudit;
        private int viewCount;
        private Object likeCount;
        private Object forwardCount;
        private String abstractContent;
        private String cityIds;
        private int orderNo;
        private long effectDate;
        private long failureDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getAuditFlag() {
            return auditFlag;
        }

        public void setAuditFlag(Object auditFlag) {
            this.auditFlag = auditFlag;
        }

        public Object getColumnInfo() {
            return columnInfo;
        }

        public void setColumnInfo(Object columnInfo) {
            this.columnInfo = columnInfo;
        }

        public Object getRootColumnInfo() {
            return rootColumnInfo;
        }

        public void setRootColumnInfo(Object rootColumnInfo) {
            this.rootColumnInfo = rootColumnInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getSourceFrom() {
            return sourceFrom;
        }

        public void setSourceFrom(Object sourceFrom) {
            this.sourceFrom = sourceFrom;
        }

        public Object getPublisher() {
            return publisher;
        }

        public void setPublisher(Object publisher) {
            this.publisher = publisher;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getCoverImageUrlTwo() {
            return coverImageUrlTwo;
        }

        public void setCoverImageUrlTwo(String coverImageUrlTwo) {
            this.coverImageUrlTwo = coverImageUrlTwo;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getArticlePosition() {
            return articlePosition;
        }

        public void setArticlePosition(String articlePosition) {
            this.articlePosition = articlePosition;
        }

        public boolean isIsAudit() {
            return isAudit;
        }

        public void setIsAudit(boolean isAudit) {
            this.isAudit = isAudit;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }

        public Object getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Object forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getAbstractContent() {
            return abstractContent;
        }

        public void setAbstractContent(String abstractContent) {
            this.abstractContent = abstractContent;
        }

        public String getCityIds() {
            return cityIds;
        }

        public void setCityIds(String cityIds) {
            this.cityIds = cityIds;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public long getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(long effectDate) {
            this.effectDate = effectDate;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }
    }

    public static class HotInfoBean {
        /**
         * id : 8aa8095e6222d0e50162232841df0006
         * createDate : 1521008263000
         * updateDate : 1521008263000
         * deleteFlag : 0
         * auditFlag : null
         * columnInfo : null
         * rootColumnInfo : null
         * type : 0
         * title : 图文区域2
         * content : null
         * summary : null
         * sourceFrom : null
         * publisher : null
         * href : http://www.donews.com/news/detail/1/2989770.html
         * coverImageUrl : https://cdn-tc-st.banketech.com/discover/f1537199-57c3-4cfb-9689-28015f77bc4d.jpg
         * coverImageUrlTwo :
         * isTop : null
         * articlePosition : 4
         * isAudit : true
         * viewCount : 0
         * likeCount : null
         * forwardCount : null
         * abstractContent : 图文区域2图文区域2图文区域2图文区域2图文区域2图文区域2
         * cityIds :
         * orderNo : 2
         * effectDate : 1520749052000
         * failureDate : 1523030400000
         */

        private String id;
        private long createDate;
        private long updateDate;
        private String deleteFlag;
        private Object auditFlag;
        private Object columnInfo;
        private Object rootColumnInfo;
        private int type;
        private String title;
        private Object content;
        private Object summary;
        private Object sourceFrom;
        private Object publisher;
        private String href;
        private String coverImageUrl;
        private String coverImageUrlTwo;
        private Object isTop;
        private String articlePosition;
        private boolean isAudit;
        private int viewCount;
        private Object likeCount;
        private Object forwardCount;
        private String abstractContent;
        private String cityIds;
        private int orderNo;
        private long effectDate;
        private long failureDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getAuditFlag() {
            return auditFlag;
        }

        public void setAuditFlag(Object auditFlag) {
            this.auditFlag = auditFlag;
        }

        public Object getColumnInfo() {
            return columnInfo;
        }

        public void setColumnInfo(Object columnInfo) {
            this.columnInfo = columnInfo;
        }

        public Object getRootColumnInfo() {
            return rootColumnInfo;
        }

        public void setRootColumnInfo(Object rootColumnInfo) {
            this.rootColumnInfo = rootColumnInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getSourceFrom() {
            return sourceFrom;
        }

        public void setSourceFrom(Object sourceFrom) {
            this.sourceFrom = sourceFrom;
        }

        public Object getPublisher() {
            return publisher;
        }

        public void setPublisher(Object publisher) {
            this.publisher = publisher;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getCoverImageUrlTwo() {
            return coverImageUrlTwo;
        }

        public void setCoverImageUrlTwo(String coverImageUrlTwo) {
            this.coverImageUrlTwo = coverImageUrlTwo;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getArticlePosition() {
            return articlePosition;
        }

        public void setArticlePosition(String articlePosition) {
            this.articlePosition = articlePosition;
        }

        public boolean isIsAudit() {
            return isAudit;
        }

        public void setIsAudit(boolean isAudit) {
            this.isAudit = isAudit;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }

        public Object getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Object forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getAbstractContent() {
            return abstractContent;
        }

        public void setAbstractContent(String abstractContent) {
            this.abstractContent = abstractContent;
        }

        public String getCityIds() {
            return cityIds;
        }

        public void setCityIds(String cityIds) {
            this.cityIds = cityIds;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public long getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(long effectDate) {
            this.effectDate = effectDate;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }
    }

    public static class DSClassroomBean {
        /**
         * id : 8aa8095e6222d0e50162231d14850001
         * createDate : 1521007531000
         * updateDate : 1521007531000
         * deleteFlag : 0
         * auditFlag : null
         * columnInfo : null
         * rootColumnInfo : null
         * type : 0
         * title : 图片区域2
         * content : null
         * summary : null
         * sourceFrom : null
         * publisher : null
         * href : http://www.xinhuanet.com/politics/2018-03/14/c_1122535244.htm
         * coverImageUrl : https://cdn-tc-st.banketech.com/discover/90b0d3b2-9c18-44d6-82fa-9dc86d57943a.png
         * coverImageUrlTwo :
         * isTop : null
         * articlePosition : 2
         * isAudit : true
         * viewCount : 0
         * likeCount : null
         * forwardCount : null
         * abstractContent : 图片区域2
         * cityIds :
         * orderNo : 2
         * effectDate : 1521007525000
         * failureDate : 1523030400000
         */

        private String id;
        private long createDate;
        private long updateDate;
        private String deleteFlag;
        private Object auditFlag;
        private Object columnInfo;
        private Object rootColumnInfo;
        private int type;
        private String title;
        private Object content;
        private Object summary;
        private Object sourceFrom;
        private Object publisher;
        private String href;
        private String coverImageUrl;
        private String coverImageUrlTwo;
        private Object isTop;
        private String articlePosition;
        private boolean isAudit;
        private int viewCount;
        private Object likeCount;
        private Object forwardCount;
        private String abstractContent;
        private String cityIds;
        private int orderNo;
        private long effectDate;
        private long failureDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getAuditFlag() {
            return auditFlag;
        }

        public void setAuditFlag(Object auditFlag) {
            this.auditFlag = auditFlag;
        }

        public Object getColumnInfo() {
            return columnInfo;
        }

        public void setColumnInfo(Object columnInfo) {
            this.columnInfo = columnInfo;
        }

        public Object getRootColumnInfo() {
            return rootColumnInfo;
        }

        public void setRootColumnInfo(Object rootColumnInfo) {
            this.rootColumnInfo = rootColumnInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getSourceFrom() {
            return sourceFrom;
        }

        public void setSourceFrom(Object sourceFrom) {
            this.sourceFrom = sourceFrom;
        }

        public Object getPublisher() {
            return publisher;
        }

        public void setPublisher(Object publisher) {
            this.publisher = publisher;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getCoverImageUrlTwo() {
            return coverImageUrlTwo;
        }

        public void setCoverImageUrlTwo(String coverImageUrlTwo) {
            this.coverImageUrlTwo = coverImageUrlTwo;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getArticlePosition() {
            return articlePosition;
        }

        public void setArticlePosition(String articlePosition) {
            this.articlePosition = articlePosition;
        }

        public boolean isIsAudit() {
            return isAudit;
        }

        public void setIsAudit(boolean isAudit) {
            this.isAudit = isAudit;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }

        public Object getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Object forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getAbstractContent() {
            return abstractContent;
        }

        public void setAbstractContent(String abstractContent) {
            this.abstractContent = abstractContent;
        }

        public String getCityIds() {
            return cityIds;
        }

        public void setCityIds(String cityIds) {
            this.cityIds = cityIds;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public long getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(long effectDate) {
            this.effectDate = effectDate;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }
    }

    public static class ProductExchangeBean {
        /**
         * id : 8aa8095e6222d0e501622324c8c70004
         * createDate : 1521008036000
         * updateDate : 1521008036000
         * deleteFlag : 0
         * auditFlag : null
         * columnInfo : null
         * rootColumnInfo : null
         * type : 0
         * title : 文章区域3
         * content : null
         * summary : null
         * sourceFrom : null
         * publisher : null
         * href : http://www.sohu.com/a/225493407_114731?_f=index_news_12?pvid=ab2a3f3fa09b29b9
         * coverImageUrl : https://cdn-tc-st.banketech.com/discover/dd07d3a7-693c-4fd2-819a-10087964442f.png
         * coverImageUrlTwo :
         * isTop : null
         * articlePosition : 3
         * isAudit : true
         * viewCount : 0
         * likeCount : null
         * forwardCount : null
         * abstractContent : 文章区域3文章区域3文章区域3文章区域3
         * cityIds :
         * orderNo : 3
         * effectDate : 1521007240000
         * failureDate : 1523030400000
         */

        private String id;
        private long createDate;
        private long updateDate;
        private String deleteFlag;
        private Object auditFlag;
        private Object columnInfo;
        private Object rootColumnInfo;
        private int type;
        private String title;
        private Object content;
        private Object summary;
        private Object sourceFrom;
        private Object publisher;
        private String href;
        private String coverImageUrl;
        private String coverImageUrlTwo;
        private Object isTop;
        private String articlePosition;
        private boolean isAudit;
        private int viewCount;
        private Object likeCount;
        private Object forwardCount;
        private String abstractContent;
        private String cityIds;
        private int orderNo;
        private long effectDate;
        private long failureDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getAuditFlag() {
            return auditFlag;
        }

        public void setAuditFlag(Object auditFlag) {
            this.auditFlag = auditFlag;
        }

        public Object getColumnInfo() {
            return columnInfo;
        }

        public void setColumnInfo(Object columnInfo) {
            this.columnInfo = columnInfo;
        }

        public Object getRootColumnInfo() {
            return rootColumnInfo;
        }

        public void setRootColumnInfo(Object rootColumnInfo) {
            this.rootColumnInfo = rootColumnInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getSourceFrom() {
            return sourceFrom;
        }

        public void setSourceFrom(Object sourceFrom) {
            this.sourceFrom = sourceFrom;
        }

        public Object getPublisher() {
            return publisher;
        }

        public void setPublisher(Object publisher) {
            this.publisher = publisher;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getCoverImageUrlTwo() {
            return coverImageUrlTwo;
        }

        public void setCoverImageUrlTwo(String coverImageUrlTwo) {
            this.coverImageUrlTwo = coverImageUrlTwo;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getArticlePosition() {
            return articlePosition;
        }

        public void setArticlePosition(String articlePosition) {
            this.articlePosition = articlePosition;
        }

        public boolean isIsAudit() {
            return isAudit;
        }

        public void setIsAudit(boolean isAudit) {
            this.isAudit = isAudit;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }

        public Object getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(Object forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getAbstractContent() {
            return abstractContent;
        }

        public void setAbstractContent(String abstractContent) {
            this.abstractContent = abstractContent;
        }

        public String getCityIds() {
            return cityIds;
        }

        public void setCityIds(String cityIds) {
            this.cityIds = cityIds;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public long getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(long effectDate) {
            this.effectDate = effectDate;
        }

        public long getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(long failureDate) {
            this.failureDate = failureDate;
        }
    }
}
