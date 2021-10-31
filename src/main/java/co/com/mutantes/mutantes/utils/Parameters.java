package co.com.mutantes.mutantes.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parameters {

    @Value("${message.sequence.invalid}")
    public String sequenceInvalid;

    @Value("${pattern.regex}")
    public String regex;
}
