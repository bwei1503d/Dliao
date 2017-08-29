package com.bw.dliao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muhanxi on 17/6/29.
 */

public class Test {


    public static void main(String[] args) {
        int f = Test.f(10);
        System.out.println("f = " + f);
    }

    public static int f(int n) {
        int t = 0;
        boolean b = (n > 0) && ((t = f(n - 1)) >0);
        return n + t;
    }
}
