package com.test.ThreadTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bw.cache.vo.BwMineCollectorAllVO;

public class Test {

    public static List<TestListSort> sortList(List<TestListSort> list) {
        Collections.sort(list, new Comparator<TestListSort>() {

            @Override
            public int compare(TestListSort b1,
                    TestListSort b2) {
                Integer cCount = b1.getAa();
                Integer newCCount = b2.getBb();
                return cCount.compareTo(newCCount);
            }

        });
        return list;
    }

    public static void main(String[] args) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	//System.out.println(sdf.format(new java.util.Date()));
//	//Date date=sdf.parse(source)
//	
//	System.out.println(System.currentTimeMillis());
//	String sd=sdf.format(1354088955); 
//	System.out.println(sd);   //1354088292875

//	 String time="1354167684025";
//	String ss=sdf.format(Long.parseLong(time));
//	 System.out.println(ss);
        String test = "t_q";

//	 String tempid=	test.substring(test.lastIndexOf("_") + 1, test.length());
//	 String tempid1=test.substring(0,test.lastIndexOf("_") );
//	 System.out.println(tempid1);
//	 System.out.println(tempid);
//	System.out.println(37/6);
        Date date = new Date();
        System.out.println(date);

    }

    class TestListSort {

        int id;
        int aa;
        int bb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAa() {
            return aa;
        }

        public void setAa(int aa) {
            this.aa = aa;
        }

        public int getBb() {
            return bb;
        }

        public void setBb(int bb) {
            this.bb = bb;
        }
    }

}
