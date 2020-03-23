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

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public String viewAccueil(ModelMap model, HttpSession session){

        User connectedUser = (User) session.getAttribute("connectedUser");

        vue.addDataRaw("headers","[ { text: 'Titre', align: 'start', sortable: false, value: 'title' }, { text: 'Description', value: 'description' }, { text: 'Date dernière modification', value: 'strCreationDate' }, { text: 'Actions', value: 'actions', sortable: false } ]");

        List<Script> scripts = null;

        if (connectedUser != null) {
            scripts = repoScript.findByOwner(connectedUser);
        }
        vue.addData("listeScripts", scripts);


        if(connectedUser != null){
            vue.addData("connecter", true);
            vue.addData("user", connectedUser.getLogin());
        }else{
            vue.addData("connecter", false);
        }
        model.put("vue", this.vue);

        return "index";
    }


}
