package com.alkemy.wallet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*inicio sin security*/

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping()
    public String index() {
        return "Inicio";
    }

}
