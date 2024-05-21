package com.datvu.all.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

  @GetMapping("/message")
  public String displayMessage(Model model) {

    model.addAttribute("message", "Hello, World!");
    return "messageView";
  }
  
  @GetMapping("/secure-message")
  public String displaySecureMessage(Model model) {

    model.addAttribute("message", "Secure!");
    return "messageView";
  }
}
