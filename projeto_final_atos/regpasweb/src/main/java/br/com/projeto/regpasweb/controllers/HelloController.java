package br.com.projeto.regpasweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {


    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView("hello"); // nome do arquivo html a ser renderizado//exibido
        mv.addObject("nome", "Maria!");
        return mv; // o Spring vai renderizar o arquivo templates/hello.html
    }

    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request){
        request.setAttribute("nome", "TOBIAS");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html
    }

}

