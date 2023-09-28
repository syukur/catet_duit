package com.lylastudio.catetduit.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PatternTest {


    @Test
    void test01(){
        // Your input string
        String input = "nama saya ${name} alamat di ${address}";

        // Define the replacement values
        String name = "John Doe";
        String address = "123 Main Street";

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "John Doe");
        map.put("address", "123 Main Street");

        // Define the regular expression pattern
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");

        // Create a Matcher for the input string
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()){
            String variable = matcher.group(1);
            matcher.appendReplacement(result, map.get(variable));
            log.info("variable: " + variable);
        }

        matcher.appendTail(result);
        log.info(result.toString());
    }
}
