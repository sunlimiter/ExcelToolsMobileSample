package com.tywho.exceltoolsmobile.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 问题内容
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 11:11
 */
@Entity(nameInDb = "q_content", createInDb = false)
public class QuestionContent {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    private String content;
    private int locationx;
    private int locationy;
    private int parentid;
    @Transient
    private String typeName; // not persisted

    @Generated(hash = 473416869)
    public QuestionContent(Long id, String content, int locationx, int locationy,
                           int parentid) {
        this.id = id;
        this.content = content;
        this.locationx = locationx;
        this.locationy = locationy;
        this.parentid = parentid;
    }

    @Generated(hash = 1069486337)
    public QuestionContent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLocationx() {
        return this.locationx;
    }

    public void setLocationx(int locationx) {
        this.locationx = locationx;
    }

    public int getLocationy() {
        return this.locationy;
    }

    public void setLocationy(int locationy) {
        this.locationy = locationy;
    }

    public int getParentid() {
        return this.parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

}
