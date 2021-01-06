package com.ilinklink.spring_boot.recursion;

/**
 * Organization
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/6  17:55
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/


import java.util.ArrayList;
import java.util.List;

public class Organization {

    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 子级组织结构
     */
    private List<Organization> organizations;


    public Organization(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.organizations = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

}
