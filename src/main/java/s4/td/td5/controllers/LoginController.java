package s4.td.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import s4.td.td5.repositories.UserRepository;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class LoginController {

    @Autowired
    private UserRepository repoUser;


    @GetMapping("/")
    public String viewAccueil(ModelMap model){





        return "accueil";

    }


}
