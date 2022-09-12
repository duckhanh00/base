package com.example.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DecimalFormat;
public class Util {

    public static String toJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public static String doubleToString(Double number) {
        try {
            if (number == 0) {
                return "0";            }
            else {
                DecimalFormat df = new DecimalFormat("#.#####");
                String result = df.format(number);
                return result;
            }
        } catch (Exception ex) {
            return "0";
        }
    }
}