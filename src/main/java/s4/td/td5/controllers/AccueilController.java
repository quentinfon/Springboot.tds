package s4.td.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;
import s4.td.td5.repositories.ScriptRepository;
import s4.td.td5.repositories.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class AccueilController {

    @Autowired
    private VueJS vue;

    @Autowired
    private UserRepository repoUser;
    @Autowired
    private ScriptRepository repoScript;



    @GetMapping("/index")
    public String viewAccueil(ModelMap model){

        vue.addDataRaw("headers","[ { text: 'Titre', align: 'start', sortable: false, value: 'title' }, { text: 'Description', value: 'description' }, { text: 'Date dernière modification', value: 'dateModif' } ]");

        List<Script> scripts = repoScript.findByOwner(LoginController.connectedUser);

        vue.addData("listeScripts", scripts);



        if(LoginController.connectedUser != null){
            vue.addData("connecter", true);
            vue.addData("user", LoginController.connectedUser.getLogin());
        }else{
            vue.addData("connecter", false);
        }
        model.put("vue", this.vue);

        return "index";
    }

}
