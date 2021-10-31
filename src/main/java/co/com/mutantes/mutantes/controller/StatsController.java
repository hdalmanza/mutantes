package co.com.mutantes.mutantes.controller;

import co.com.mutantes.mutantes.service.StatsService;
import co.com.mutantes.mutantes.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    @Autowired
    private GeneralUtils generalUtils;
    @Autowired
    private StatsService statusService;

    @GetMapping(value = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStats(){
        ResponseEntity<Object> response = null;
        try {
            response =statusService.getStats();
        }catch (Exception exception){
            new ResponseEntity<>( generalUtils.buildMessage("Error" + exception.getMessage()),
                    HttpStatus.FORBIDDEN);
        }
        return response;
    }
}
