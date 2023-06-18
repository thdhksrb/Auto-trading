package com.example.upbit.controller;

import com.example.upbit.dto.AccountDTO;
import com.example.upbit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/myAsset")
    @ResponseBody
    public List<AccountDTO> viewAsset() {

        List<AccountDTO> list = orderService.executeGetAccounts();

        return list;
    }



}

