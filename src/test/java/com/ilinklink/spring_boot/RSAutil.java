package com.ilinklink.spring_boot;

import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * RSAutil
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/7/23  12:20
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
public class RSAutil {

    public static void main(String [] agrs){
        int target=33;
      //log.info("质数校验；{},{}",target,isPrimeNumber(target));

        ArrayList<Integer> paimes=new ArrayList<>();
        Integer size=100;
        for (int i = 0; i < size; i++) {
            if(isPrimeNumber(i)){
                paimes.add(i);
            }
        }
        log.info("{}以内质数个数：{}。分别是：{}",size,paimes.size(),paimes);

        size=10000000;
        String binary=Integer.toBinaryString(size);
        log.info("{}的二进制码：{},长度:{}",size,binary,binary.length());

        int count=0;
        int currentIndex=0;
        for (int i = 1; i < size; i++) {
                if(i!=3&&i==currentIndex+1){//为了提高效率，找到某个质数后，下一个一定是合数(2,3除外)，所以continue
                    continue;
                }
                if(isPrimeNumber(i)){
                    currentIndex=i;
                    ++count;
                }
        }
        log.info("{}以内质数个数：{}  ",size,count);

          //log.info("100以内质数个数：{}。分别是：{}",paimes.size(),paimes);


        int p=61;
        int q=53;
        int n = p*q;//3233;

        int eulerNumber=(p-1)*(q-1);//3233的欧拉数=3120，等于61的欧拉函数乘以53的欧拉函数。由于61和53都是质数，所以质数的欧拉数是减一。即 60*52

        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 2; i <= eulerNumber; i++) {
            boolean isCoprimeNumber=isCoprimeNumber(i,eulerNumber);
            if(isCoprimeNumber){
                list.add(i);
            }
        }
        log.info("计算出小于n欧拉数，并且和n的欧拉数互质的全部自然数的集合,数量【{}】，集合：{}",list.size(),list);

        //从上面的集合里随机取出一个数：17 。它与3120是互质的
        int e=17;//1< e < φ(n) 即 1<e<3120
        //log.info("17与3120是否互质？{}",isCoprimeNumber(17,3120));
        //e=list.get(new Random().nextInt(list.size()-1));

        int d=getModularInverseElement(e,eulerNumber);//2723

        log.info("计算e对于φ(n)的模反元素d：{}",d);

        //代入本次的推导过程中的数字，n = 3233,e = 17, d=2753。公钥为 n,e（3233，17），私钥为 n,d（3233，2723）。
        //加密使用 （3233，17），解密使用（3233，2723）。

        int message=3232;//明文,必须小于n。否则解密不出来
        int encode=quick(message,e,n);
        log.info("明文：{}",message);
        log.info("得到密文：{}",encode);

        int decode=quick(encode,d,n);
        log.info("解密得到明文：{}",decode);


        ArrayList<Integer> modularInverseElements = getModularInverseElements(e, eulerNumber);//2723
        log.info("计算e对于φ(n)的模反元素集合：{}",modularInverseElements);

        int mod =quick(65,17,3233);
        log.info("65的17次方，对3233取模：{}",mod);

    }

    /**
     * 判断是否互为质数
     * @param p1
     * @param p2
     * @return
     */
    public static boolean isCoprimeNumber(int p1,int p2){
        if(p1<=0||p2<=0){
            return false;
        }
        /* 1、1和任何自然数互质。
           2、相邻的两个自然数互质。
           3、两个不同的质数互质。
           4、一质数和一个合数两个数不是倍数关系时互质
           5、不含相同质因数的两个合数互质*/
        if(p1==1||p2==1){
            return true;
        }
        if(p1>1&&p2>1&&Math.abs(p1-p2)==1){
           return true;
        }
        if(isPrimeNumber(p1)&&isPrimeNumber(p2)){
            return true;
        }
        if(!isPrimeNumber(p1)&&isPrimeNumber(p2)&&p1<p2){
            return true;
        }
        if(!isPrimeNumber(p2)&&isPrimeNumber(p1)&&p2<p1){
            return true;
        }
        int size=Math.min(p1,p2);
        for (int i = 2; i <=size; i++) {
            boolean divided=p1%i==0;//是否被整除
            if(divided){
                divided=p2%i==0;//是否被整除
                if(divided){//找到公因数了，返回false
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isPrimeNumber(int number){
        if(number<=1){
            return false;
        }
        else  {
            for (int i = 2; i < number; i++) {
                boolean divided=number%i==0;//是否被整除
                boolean isOneOrItself=(i-1==0)||(number-i==0);
                if(!isOneOrItself&&divided){ //能被1和自己之外的数整除
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 计算模反元素
     * @param p1
     * @param p2
     * @return
     */
    public static  int getModularInverseElement(int p1,int p2){
            for (int i = 2; i <Integer.MAX_VALUE ; i++) {
               int mod= (p2*i+1)%p1;
               if(mod==0){
                   return (p2*i+1)/p1;
               }
            }
            return -1;
    }

    /**
     * 计算模反元素集合，注意，它有无限个。为了避免OOM，这里取了10个就返回
     * @param p1
     * @param p2
     * @return
     */
    public static ArrayList<Integer> getModularInverseElements(int p1,int p2){
        ArrayList<Integer> result=new ArrayList<>();
        for (int i = 2; i <Integer.MAX_VALUE ; i++) {
            int mod= (p2*i+1)%p1;
            if(mod==0){
                result.add((p2*i+1)/p1);
            }

            if(result.size()>10){
                break;
            }
        }

        return result;
    }

    /**
     *  快速幂取模   计算 (a^b) %c
     * @param a
     * @param b
     * @param c
     * @return 计算结果
     */
    private static int quick(int a,int b,int c) {
        int ans=1;   //记录结果
        a=a%c;   //预处理，使得a处于c的数据范围之下
        while(b!=0)
        {
            if((b&1)==1){ //1即是0000000000000001，判断个位是否是1.如果b的二进制位是1，那么我们的结果是要参与运算的
                ans=(ans*a)%c;
            }
            b>>=1;    //二进制的移位操作，相当于每次除以2，用二进制看，就是我们不断的遍历b的二进制位
            a=(a*a)%c;   //不断的加倍
        }
        return ans;
    }


}
