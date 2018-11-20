package com.example.gddemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者:      周来
 * 包名:      com.example.gddemo.entity
 * 工程名:    MyMVPTest
 * 时间:      2018/11/19
 * 说明:
 */

@Entity
public class StudentAndTeacherBean {
    @Id(autoincrement = true)
    private Long id;
    private Long studentId;
    private Long teacherId;

    public StudentAndTeacherBean(Long studentId, Long teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    @Generated(hash = 2146410221)
    public StudentAndTeacherBean(Long id, Long studentId, Long teacherId) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    @Generated(hash = 207804266)
    public StudentAndTeacherBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
