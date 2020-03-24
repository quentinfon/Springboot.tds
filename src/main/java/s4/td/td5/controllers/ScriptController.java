package s4.td.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.td.td5.models.History;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;
import s4.td.td5.repositories.HistoryRepository;
import s4.td.td5.repositories.ScriptRepository;
import s4.td.td5.repositories.UserRepository;

import javax.servlet.http.HttpSession;
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
    public String viewNewScript(ModelMap model, HttpSession session){

        User connectedUser = (User) session.getAttribute("connectedUser");

        if (connectedUser != null){

            vue.addData("valid", true);
            vue.addData("id", -1);
            vue.addData("title", "");
            vue.addData("description", "");
            vue.addData("content", "");
            vue.addMethod("validate", "if(this.$refs.form.validate()){ scriptForm.submit(); }");
            vue.addMethod("reset", "this.$refs.form.reset()");
            model.put("vue", this.vue);

            return "formNewScript";
        }else{

            vue.addData("valid", true);
            vue.addData("login", "");
            vue.addData("password", "");
            vue.addMethod("validate", "if(this.$refs.form.validate()){ logForm.submit(); }");
            vue.addMethod("reset", "this.$refs.form.reset()");
            model.put("vue", this.vue);
            return "loginPage";
        }
    }

    @PostMapping("/script/submit")
    public RedirectView connexion(@RequestParam int id, @RequestParam String title, @RequestParam String description, @RequestParam String content, HttpSession session) {

        User connectedUser = (User) session.getAttribute("connectedUser");

        if (id == -1){
            //Création
            if (connectedUser != null){
                Script s = new Script();
                s.setTitle(title);
                s.setContent(content);
                s.setDescription(description);
                s.setCreationDate(new Date());
                s.setOwner(connectedUser);
                repoScript.save(s);
            }

        }else{
            //Modification
            Script s = repoScript.findById(id);

            if (connectedUser != null && s != null && s.getOwner().getId() == connectedUser.getId()){

                History h = new History();
                h.setScript(s);
                h.setDate(s.getCreationDate());
                h.setContent(s.getContent());
                h.setComment(s.getDescription());
                repoHistory.save(h);

                s.setTitle(title);
                s.setContent(content);
                s.setDescription(description);
                s.setCreationDate(new Date());
                s.setOwner(connectedUser);
                repoScript.save(s);
            }
        }


        return new RedirectView("/");

    }


    @GetMapping("/script/{id}")
    public String consulterScript(ModelMap model, @PathVariable int id, HttpSession session) {

        User connectedUser = (User) session.getAttribute("connectedUser");

        Script s = repoScript.findById(id);

        if (connectedUser == null){
            vue.addData("valid", true);
            vue.addData("login", "");
            vue.addData("password", "");
            vue.addMethod("validate", "if(this.$refs.form.validate()){ logForm.submit(); }");
            vue.addMethod("reset", "this.$refs.form.reset()");
            model.put("vue", this.vue);
            return "loginPage";
        }
        else if (s != null) {
            if (s.getOwner().getId() == connectedUser.getId()) {

                vue.addData("valid", true);
                vue.addData("id", s.getId());
                vue.addData("title", s.getTitle());
                vue.addData("description", s.getDescription());
                vue.addData("content", s.getContent());
                vue.addMethod("validate", "if(this.$refs.form.validate()){ modifScriptForm.submit(); }");
                vue.addMethod("reset", "this.$refs.form.reset()");
                model.put("vue", this.vue);
                return "formModifScript";

            } else {
                vue.addData("message", "Ce script n'est pas le votre !");
                model.put("vue", this.vue);
                return "pasLesDroits";
            }
        }else {
            vue.addData("message", "Ce script n'existe pas !");
            model.put("vue", this.vue);
            return "404";
        }

    }

    @GetMapping("/search")
    public String rechercheScript(ModelMap model, HttpSession session) {

        User connectedUser = (User) session.getAttribute("connectedUser");

        if(connectedUser != null){
            vue.addData("connecter", true);
            vue.addData("user", connectedUser.getLogin());
        }else{
            vue.addData("connecter", false);
        }

        vue.addDataRaw("headers","[ { text: 'Titre', align: 'start', sortable: false, value: 'title' }, { text: 'Description', value: 'description' }, { text: 'Date dernière modification', value: 'strCreationDate' } ]");

        vue.addData("listeScripts");

        vue.addData("recherche", "");

        vue.addMethod("getData", "var route = '/rest/script/search/'+this.recherche; this.$http.get(route).then((response)=>{ " +
                "this.listeScripts = [];"+
                "for(var i = 0; i< response.data.length;i++)" +
                "{" +
                "this.listeScripts.push({" +
                "title: response.data[i].title," +
                "description: response.data[i].description," +
                "strCreationDate: response.data[i].strCreationDate" +
                "});" +
                "}});");

        model.put("vue", this.vue);

        return "recherche";
    }


}
