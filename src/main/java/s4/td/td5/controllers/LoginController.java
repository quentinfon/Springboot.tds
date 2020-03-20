package s4.td.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.td.td5.models.User;
import s4.td.td5.repositories.UserRepository;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class LoginController {

    @Autowired
    private VueJS vue;

    @Autowired
    private UserRepository repoUser;

    public static User connectedUser;


    @GetMapping("/")
    public RedirectView viewAccueil(ModelMap model){

        return new RedirectView("/index");

    }

    @GetMapping("/login")
    public String viewLogin(ModelMap model){

        vue.addData("valid", true);
        vue.addData("login", "");
        vue.addData("password", "");
        vue.addMethod("validate", "if(this.$refs.form.validate()){ logForm.submit(); }");
        vue.addMethod("reset", "this.$refs.form.reset()");
        model.put("vue", this.vue);
        return "loginPage";
    }

    @PostMapping("/connexion")
    public RedirectView connexion(@RequestParam String login, @RequestParam String password) {

        LoginController.connectedUser = repoUser.connexion(login, password);

        return new RedirectView("/index");

    }

    @PostMapping("/logout")
    public RedirectView deconnexion() {

        LoginController.connectedUser = null;

        return new RedirectView("/index");

    }

}
