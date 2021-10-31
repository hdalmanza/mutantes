package co.com.mutantes.mutantes.utils;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class GeneralUtils {

    public String buildMessage(String info){
        JsonObject message = new JsonObject();
        message.addProperty("message", info);
        return message.toString();
    }
}
