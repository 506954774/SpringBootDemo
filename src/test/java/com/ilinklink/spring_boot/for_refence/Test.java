package com.ilinklink.spring_boot.for_refence;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Test
 * 责任人:  ChenLei
 * 修改人： ChenLei
 * 创建/修改时间: 2021/3/6 10:23
 * Copyright :  版权所有
 **/
public class Test {


    public static void main(String[] args) {


        BigDecimal discount=new BigDecimal("873.8").multiply(new BigDecimal("0.4"));
        //discount=discount.setScale(1, BigDecimal.ROUND_HALF_UP);
        discount=discount.setScale(1, BigDecimal.ROUND_UP);

        System.out.println(discount);

    }

    public static void main1(String[] args) {
        System.out.println("main");

        ArrayList<Entity> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Entity(i,"entity"+i));
        }
        System.out.println(list);

        for (Entity entity:list){
            entity.setName("-");
            entity=new Entity();
        }
        System.out.println(list);

    }
}
