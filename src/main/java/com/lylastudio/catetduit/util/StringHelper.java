package com.lylastudio.catetduit.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class StringHelper {

    Pattern replacePattern = Pattern.compile("\\$\\{([^}]+)\\}");
    Pattern splitPattern = Pattern.compile("\\$\\{([^}]+)\\}");

    public String replacePattern(String input, HashMap<String, String> replacer){

        log.info("input: {} ", input);

        Matcher matcher = replacePattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()){
            String variable = matcher.group(1);
            matcher.appendReplacement(result, replacer.get(variable));
        }

        matcher.appendTail(result);

        return result.toString();

    }

    public ArrayList<String> splitString(String input) {
        ArrayList<String> tokens = new ArrayList<>();

        Matcher matcher = splitPattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // If the match is inside quotes, add the content without quotes
                tokens.add(matcher.group(1));
            } else {
                // If the match is outside quotes, add the match as it is
                tokens.add(matcher.group());
            }
        }

        // Convert the ArrayList to an array
        return tokens;
    }
}
