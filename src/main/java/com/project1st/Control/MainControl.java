package com.project1st.Control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControl {


    @GetMapping( "/" )
    public String home(){
        return "home";
    }
}
