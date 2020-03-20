package s4.td.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.td.td5.models.History;
import s4.td.td5.models.Script;
import s4.td.td5.repositories.HistoryRepository;
import s4.td.td5.repositories.ScriptRepository;
import s4.td.td5.repositories.UserRepository;
import java.util.Date;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class ScriptController {

    @Autowired
    private VueJS vue;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private ScriptRepository repoScript;

    @Autowired
    private HistoryRepository repoHistory;

    @GetMapping("/script/new")
    public String viewNewScript(ModelMap model){

        if (LoginController.connectedUser != null){

            vue.addData("valid", true);
            vue.addData("login", "");
            vue.addData("password", "");
            vue.addMethod("validate", "if(this.$refs.form.validate()){ scriptForm.submit(); }");
            vue.addMethod("reset", "this.$refs.form.reset()");
            model.put("vue", this.vue);

            return "formNewScript";
        }else{
            vue.addData("valid", true);
            vue.addData("title", "");
            vue.addData("description", "");
            vue.addData("content", "");
            vue.addMethod("validate", "if(this.$refs.form.validate()){ logForm.submit(); }");
            vue.addMethod("reset", "this.$refs.form.reset()");
            model.put("vue", this.vue);
            return "loginPage";
        }
    }

    @PostMapping("/script/ajout")
    public RedirectView connexion(@RequestParam String title, @RequestParam String description, @RequestParam String content) {

        if (LoginController.connectedUser != null){
            Script s = new Script();
            s.setTitle(title);
            s.setContent(content);
            s.setDescription(description);
            s.setCreationDate(new Date());
            s.setOwner(LoginController.connectedUser);
            repoScript.save(s);
        }

        return new RedirectView("/");

    }


    @GetMapping("/script/{id}")
    public String consulterScript(ModelMap model, @PathVariable int id){

        Script s = repoScript.findById(id);

        if (s != null){
            if (s.getOwner().getId() == LoginController.connectedUser.getId()){

                vue.addData("valid", true);
                vue.addData("id", s.getId());
                vue.addData("title", s.getTitle());
                vue.addData("description", s.getDescription());
                vue.addData("content", s.getContent());
                vue.addMethod("validate", "if(this.$refs.form.validate()){ modifScriptForm.submit(); }");
                vue.addMethod("reset", "this.$refs.form.reset()");
                model.put("vue", this.vue);
                return "formModifScript";

            }else{
                vue.addData("message","Ce script n'est pas le votre !");
                model.put("vue",this.vue);
                return "pasLesDroits";
            }
        }else{
            vue.addData("message","Ce script n'existe pas !");
            model.put("vue",this.vue);
            return "404";
        }

    }

    @PostMapping("/script/modifier")
    public RedirectView modifScript(@RequestParam int id, @RequestParam String title, @RequestParam String description, @RequestParam String content) {

        Script s = repoScript.findById(id);

        if (LoginController.connectedUser != null && s != null && s.getOwner().getId() == LoginController.connectedUser.getId()){

            History h = new History();
            h.setVersion(s);
            h.setDate(s.getCreationDate());
            h.setContent(s.getContent());
            h.setComment(s.getDescription());
            repoHistory.save(h);

            s.setTitle(title);
            s.setContent(content);
            s.setDescription(description);
            s.setCreationDate(new Date());
            s.setOwner(LoginController.connectedUser);
            repoScript.save(s);
        }

        return new RedirectView("/");

    }


}
