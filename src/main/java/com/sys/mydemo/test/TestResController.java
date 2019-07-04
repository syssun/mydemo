package com.sys.mydemo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResController {
    @GetMapping("/m")
    public String h(){
        return "3322";
    }
}
