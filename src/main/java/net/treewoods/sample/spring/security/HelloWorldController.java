package net.treewoods.sample.spring.security;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Locale locale, Model model, Principal principal) {
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        String message = "";
        if( principal instanceof UsernamePasswordAuthenticationToken) {
            MyUser user = (MyUser)(((UsernamePasswordAuthenticationToken)principal).getPrincipal());
            message = "  [Spring + thymeleafサンプル]: " + formattedDate + ". " + user.getCompanyName();
        } else {
            message = principal.getClass().getName();
        }
            
        model.addAttribute("thymeleaf", message);
        return "hello";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Locale locale, Model model) {
        return "login";
    }

}
