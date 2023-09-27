package com.lylastudio.catetduit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Locale;

@Slf4j
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

    @Test
    void test02() {
        String text = "agus;gus;agus ?;gus";

        String[] split = text.split("\\;");

        log.info(split[0]);
        for (String s : split){
            log.info(s);
        }

    }

    @Test
    void test03() {
        String text = "SyukuR";

        log.info(text);
        log.info(text.toLowerCase());
        log.info(text.toUpperCase());

    }
}
