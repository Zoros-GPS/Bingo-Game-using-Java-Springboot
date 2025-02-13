package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    NewGame game;

    @RequestMapping("/newgame")
    public int[] gamedata(){
        return game.newgame();
    }

    @RequestMapping("/call")
    public int[] calldata(){
        return game.callnum();
    }


}
