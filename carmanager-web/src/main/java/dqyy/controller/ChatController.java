package dqyy.controller;

import dqyy.Hr;
import dqyy.service.Detai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    Detai hr;

    @GetMapping("/hrs")
    public List<Hr> getAllHrs() {
        return hr.getAllHr();
    }
}
