package com.example.gestion.controller.publicite.page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/publicites")
public class PubliciteController {

    // @GetMapping("/create")
    // public String creation(Model model) {
    //     return "publicite/publiciteCreation";
    // }

    @GetMapping("/list")
    public String list(Model model) {
        return "publicite/publiciteList";
    }

    

}