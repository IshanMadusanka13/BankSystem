package com.example.demo.Custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidater implements ConstraintValidator<mobileValidate,String> {

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext context) {
        Pattern p=Pattern.compile("(\\+94|0)?[0-9]{9}");
        Matcher m=p.matcher(mobile);
        return (m.find() && m.group().equals(mobile));
    }
}
