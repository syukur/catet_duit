package com.lylastudio.catetduit.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class StringHelper {

    Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");

    public String replacePattern(String input, HashMap<String, String> replacer){

        log.info("input: {} ", input);

        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()){
            String variable = matcher.group(1);
            matcher.appendReplacement(result, replacer.get(variable));
        }

        matcher.appendTail(result);

        return result.toString();

    }
}
