package com.bw.dliao;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muhanxi on 17/6/29.
 */

public class Test {


    public static void main(String [] args){






       Pattern pattern =   Pattern.compile("^--.*");


       Matcher matcher =  pattern.matcher("--dkjrekrejkr");


        System.out.println("matcher = " + matcher.matches());


        while (matcher.find()){
           int start =   matcher.start();
            int end = matcher.end();
        }


    }

}
