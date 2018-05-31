package com.dashuf.disp.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class HomeNotificationBean {
    private int totalElements;
    private int totalPages;
    private int numberOfElements;
    private boolean firstPage;
    private boolean lastPage;
    private int size;
    private int number;
    private Object sort;
    /**
     * id : 575e1585e4b02f79cd22381f
     * appUser : {"id":"58997b7a9475476cbfe3e771d1d92f03","username":"测试","realName":"测试达人","cellphone":"13684957256","email":null,"address":null,"wechatUsername":null,"devOpsUserMobile":null,"identityNumber":"362203198406105524","businessCity":{"id":"100400","code":"100400","name":"重庆市","sortNo":"10004","status":"1"},"bank":{"id":"307","code":"307","name":"平安银行股份有限公司","bankCardFormat":null,"sortNO":"0090","parentCode":null},"bankBranch":{"id":"307001","code":"307001","name":"平安银行深圳分行","bankCardFormat":null,"sortNO":"307001","parentCode":"307"},"bankAccountName":"测试达人","bankCardNumber":"6222023653596321","canUpgrade":null,"createdAt":1446514046000,"modifiedAt":1462256650448,"registerId":null,"expired":false,"locked":false,"enabled":true,"star":1,"appRoles":["ROLE_USER","ROLE_AGENT","ROLE_SUPER_AGENT"],"osVersion":"undefined undefined","appVersion":"1.6.2/230","registerDevice":null,"referrer":"YUDAN","refererName":"余丹","job":"03","refMobilePhone":"","groupFlag":"否","groupName":"","archived":false,"channel":null,"companyName":"aaa","popularizeType":"MANAGER_GET","enableRecommendation":true,"isChannelUser":false,"isSuperAgent":true,"isChannelMgr":false,"roles":["ROLE_USER","ROLE_AGENT","ROLE_SUPER_AGENT"]}
     * summary : [客户:企业一]推荐产品结果
     * content : 您好，我们已收到您推荐的客户申请，客户详情[客户名称：企业一，证件号码：110101198710092231]，推荐产品结果详情如下:
     * createdAt : 1465783685858
     * status : UNREAD
     * type : DSPC_RECOMMEND_DONE
     * category : BUSINESS
     * attributes : {"id":"575e1585e4b02f79cd22381e","status":"none","recommendId":"138332a755744d198c809e95359bc60b"}
     * deleted : false
     * deletedAt : null
     */

    private List<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Serializable {
        private String id;
        /**
         * id : 58997b7a9475476cbfe3e771d1d92f03
         * username : 测试
         * realName : 测试达人
         * cellphone : 13684957256
         * email : null
         * address : null
         * wechatUsername : null
         * devOpsUserMobile : null
         * identityNumber : 362203198406105524
         * businessCity : {"id":"100400","code":"100400","name":"重庆市","sortNo":"10004","status":"1"}
         * bank : {"id":"307","code":"307","name":"平安银行股份有限公司","bankCardFormat":null,"sortNO":"0090","parentCode":null}
         * bankBranch : {"id":"307001","code":"307001","name":"平安银行深圳分行","bankCardFormat":null,"sortNO":"307001","parentCode":"307"}
         * bankAccountName : 测试达人
         * bankCardNumber : 6222023653596321
         * canUpgrade : null
         * createdAt : 1446514046000
         * modifiedAt : 1462256650448
         * registerId : null
         * expired : false
         * locked : false
         * enabled : true
         * star : 1
         * appRoles : ["ROLE_USER","ROLE_AGENT","ROLE_SUPER_AGENT"]
         * osVersion : undefined undefined
         * appVersion : 1.6.2/230
         * registerDevice : null
         * referrer : YUDAN
         * refererName : 余丹
         * job : 03
         * refMobilePhone :
         * groupFlag : 否
         * groupName :
         * archived : false
         * channel : null
         * companyName : aaa
         * popularizeType : MANAGER_GET
         * enableRecommendation : true
         * isChannelUser : false
         * isSuperAgent : true
         * isChannelMgr : false
         * roles : ["ROLE_USER","ROLE_AGENT","ROLE_SUPER_AGENT"]
         */

        private String summary;
        private String content;
        private long createdAt;
        private String status;
        private String type;
        private String category;
        /**
         * id : 575e1585e4b02f79cd22381e
         * status : none
         * recommendId : 138332a755744d198c809e95359bc60b
         */

        private AttributesBean attributes;
        private boolean deleted;
        private Object deletedAt;
        private String imageUrl;
        private String jummpingUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJummpingUrl() {
            return jummpingUrl;
        }

        public void setJummpingUrl(String jummpingUrl) {
            this.jummpingUrl = jummpingUrl;
        }

        public static class AttributesBean implements Serializable {
            private String id;
            private String status;
            private String recommendId;
            private String isCreditUpload;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRecommendId() {
                return recommendId;
            }

            public void setRecommendId(String recommendId) {
                this.recommendId = recommendId;
            }

            public String getIsCreditUpload() {
                return isCreditUpload;
            }

            public void setIsCreditUpload(String isCreditUpload) {
                this.isCreditUpload = isCreditUpload;
            }
        }
    }
}
