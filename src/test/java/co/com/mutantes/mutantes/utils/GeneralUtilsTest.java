package co.com.mutantes.mutantes.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GeneralUtilsTest {
    @InjectMocks
    private  GeneralUtils generalUtils;

    @Test
    public void buildMessageWhenMessageIsEmpty(){
        String response = generalUtils.buildMessage("");
        Assert.assertTrue(response.contains("message"));
    }
    @Test
    public void buildMessageWhenMessageContainInfo(){
        String response = generalUtils.buildMessage("secuencia invalida");
        Assert.assertTrue(response.contains("secuencia invalida"));
    }
}
