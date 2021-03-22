package com.ilinklink.spring_boot.for_refence;

import lombok.Data;

import java.io.Serializable;

/**
 * Entity
 * 责任人:  ChenLei
 * 修改人： ChenLei
 * 创建/修改时间: 2021/3/6 10:23
 * Copyright :  版权所有
 **/
@Data
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    public Entity( ) {
    }

    public Entity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;
    private String name;
}
