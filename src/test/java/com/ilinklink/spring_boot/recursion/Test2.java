package com.ilinklink.spring_boot.recursion;

/**
 * Test2
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/6  17:55
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test2 {

    private static List<Organization> organizationList = initData();

    public static void main(String[] args) {
        Organization organization = organizationList.stream().min(Comparator.comparing(Organization::getId)).get();
        Organization result = generateFill(organization.getId());
        System.out.println(result);
    }

    public static Organization generateFill(Long id) {
        Organization root = getById(id);
        List<Organization> childNodes = getChildrenById(id);
        if (!childNodes.isEmpty()) {
            for (Organization node : childNodes) {
                Organization childRoot = generateFill(node.getId());
                root.getOrganizations().add(childRoot);
            }
        }
        return root;
    }

    private static Organization getById(Long id) {
        for (Organization organization : organizationList) {
            if (organization.getId().equals(id)) {
                return organization;
            }
        }
        return null;
    }

    private static List<Organization> getChildrenById(Long id) {
        List<Organization> childNodes = new ArrayList<>();
        for (Organization organization : organizationList) {
            if (organization.getParentId() != null && organization.getParentId().equals(id)) {
                childNodes.add(organization);
            }
        }
        return childNodes;
    }


    private static List<Organization> initData() {
        List<Organization> list = new ArrayList<>();
        Organization o1 = new Organization(1L, "根组织", null);
        Organization o2 = new Organization(2L, "二级组织1", 1L);
        Organization o3 = new Organization(3L, "三级组织1", 2L);
        Organization o4 = new Organization(4L, "二级组织2", 1L);
        Organization o5 = new Organization(5L, "三级组织2", 4L);
        Organization o6 = new Organization(6L, "四级组织1", 3L);
        Organization o7 = new Organization(7L, "四级组织2", 5L);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        list.add(o6);
        list.add(o7);
        return list;
    }
}