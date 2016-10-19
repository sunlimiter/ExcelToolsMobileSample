package com.tywho.exceltoolsmobile.bean;

/**
 * 查询结果
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 17:31
 */
public class QueryInfo {
    private long id;
    private String[] content;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
