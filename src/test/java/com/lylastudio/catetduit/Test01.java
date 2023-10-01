package com.lylastudio.catetduit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

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

    @Test
    void test04(){
        String s = "agus ?";
        log.info(s);
        log.info(s.replace(" ", ""));
    }

    @Test
    void test05(){
        String s = "expense 10000 \"orang tua \" \"by book\"";
        ArrayList<String> words = splitString(s);

        // Print the elements of the array
        for (String word : words) {
            log.info(word);
        }
    }

    @Test
    void sha256Test(){
        String input ="Ninja Hatori";
        String hash = DigestUtils.sha256Hex(input);
        log.info("hash: " + hash);
    }

    @Test
    void testUrl() throws MalformedURLException {
        String urlString = "http://localhost:8080/web/v1/report.html?id=10";
        URL url = new URL(urlString);
        log.info("path : {}",url.getPath());
        log.info("query : {}",url.getQuery());
        log.info("fragment : {}",url.getRef());
    }

    @Test
    void jsonTest() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("","");
        json.put("","");
        json.put("","");
    }

    public static ArrayList<String> splitString(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(input);


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