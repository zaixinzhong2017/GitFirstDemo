package com.example.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yfeng on 2017/11/6.
 */

@Entity
public class StudentMsgBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "STUDENTNUM")
    private String studentNum;
    @Property(nameInDb = "NAME")
    private String name;
    @Generated(hash = 381350025)
    public StudentMsgBean(Long id, String studentNum, String name) {
        this.id = id;
        this.studentNum = studentNum;
        this.name = name;
    }
    @Generated(hash = 160565988)
    public StudentMsgBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStudentNum() {
        return this.studentNum;
    }
    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
