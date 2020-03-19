package s4.td.td5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class LoginController {


    @GetMapping("/")
    public String viewAccueil(ModelMap model){

        return "accueil";

    }


}
