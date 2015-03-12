package com.royz;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> l = Arrays.asList(new String[]{"a", "b", "c"});
        System.out.println(l.subList(1, l.size()));


    }
}
