package org.todeschini.controller;

/**
 * Created by Artur on 16/05/18.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}