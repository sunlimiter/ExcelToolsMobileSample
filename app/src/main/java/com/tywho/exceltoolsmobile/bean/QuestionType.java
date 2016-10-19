package com.tywho.exceltoolsmobile.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * 问题所属类型
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 11:06
 */
@Entity(nameInDb = "q_type",createInDb=false)
public class QuestionType {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    private String name;
    @Generated(hash = 218389870)
    public QuestionType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 295514829)
    public QuestionType() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
