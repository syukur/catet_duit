package com.lylastudio.catetduit;

import org.junit.jupiter.api.Test;

public class Test01 {
    @Test
    void test01() {
        String text = "10000| Personal| Aqua";

        String[] split = text.split("\\|");

        System.out.println(split[0]);
        for (String s : split){
            System.out.println(s);
        }
    }
}
