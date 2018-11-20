package com.example.gddemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.gddemo.database.greenDao.db.DaoSession;
import com.example.gddemo.database.greenDao.db.IdCardDao;

/**
 * 作者:      周来
 * 包名:      com.example.gddemo.entity
 * 工程名:    MyMVPTest
 * 时间:      2018/11/19
 * 说明:
 */

@Entity(
        //如果缺少，是否应生成属性的 getter 和 setter 方法。
        generateGettersSetters = true,
        nameInDb = "IdCard",
        active = true
)
public class IdCard {

    @Id
    private String userName;//用户名
    @Unique
    private String idNo;//身份证号
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 1200241048)
private transient IdCardDao myDao;
@Generated(hash = 1028827110)
public IdCard(String userName, String idNo) {
    this.userName = userName;
    this.idNo = idNo;
}
@Generated(hash = 1500073048)
public IdCard() {
}
public String getUserName() {
    return this.userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
public String getIdNo() {
    return this.idNo;
}
public void setIdNo(String idNo) {
    this.idNo = idNo;
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1500536145)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getIdCardDao() : null;
}


}
