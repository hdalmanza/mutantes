package co.com.mutantes.mutantes.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Parameters {

    @Value("${message.sequence.invalid}")
    public String sequenceInvalid;

    @Value("${pattern.regex}")
    public String regex;

    @Value("${size.sequence}")
    public int sizeSequence;

    @Value("${list.sequence.mutant}")
    public List<String> listSequenceMutant;


}
