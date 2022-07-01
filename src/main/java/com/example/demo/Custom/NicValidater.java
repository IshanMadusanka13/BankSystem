package com.example.demo.Custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NicValidater implements ConstraintValidator<nicValidate,String> {

    @Override
    public boolean isValid(String nic, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p=Pattern.compile("[0-9]{9}?(v)|[0-9]{12}");
        Matcher m=p.matcher(nic);
        return m.find() && m.group().equals(nic);
    }
}
