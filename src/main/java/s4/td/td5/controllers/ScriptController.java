package s4.td.td5.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.td.td5.models.*;
import s4.td.td5.repositories.*;

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

    @Autowired
    private LanguageRepository repoLangage;

    @Autowired
    private CategoryRepository repoCategory;

    @GetMapping("/script/new")
    public String viewNewScript(ModelMap model, HttpSession session){

        User connectedUser = (User) session.getAttribute("connectedUser");

        if (connectedUser != null){

            vue.addData("langages", repoLangage.findAll());
            vue.addData("categories", repoCategory.findAll());
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
    public RedirectView connexion(@RequestParam int id, @RequestParam String title, @RequestParam String description, @RequestParam String content, HttpSession session, @RequestParam(required = false) String langage, @RequestParam(required = false) String categorie) {

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

                if (repoCategory.findByName(categorie).size() > 0){
                    s.setCategory(repoCategory.findByName(categorie).get(0));
                }

                if (repoLangage.findByName(langage).size() > 0){
                    s.setLanguage(repoLangage.findByName(langage).get(0));
                }

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

                if (repoCategory.findByName(categorie).size() > 0){
                    s.setCategory(repoCategory.findByName(categorie).get(0));
                }

                if (repoLangage.findByName(langage).size() > 0){
                    s.setLanguage(repoLangage.findByName(langage).get(0));
                }

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

                vue.addData("langages", repoLangage.findAll());
                vue.addData("categories", repoCategory.findAll());

                vue.addData("valueCategorie", s.getCategory());
                vue.addData("valueLangage", s.getLanguage());
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


        vue.addDataRaw("headers","[ { text: 'Titre', align: 'start', value: 'title' }, { text: 'Description', value: 'description' }, { text: 'Date dernière modification', value: 'strCreationDate' } ]");

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

    @PostMapping("/delete/{id}")
    public RedirectView supp(@PathVariable int id, HttpSession session) {

        User connectedUser = (User) session.getAttribute("connectedUser");

        Script s = repoScript.findById(id);

        if (connectedUser != null && s != null && s.getOwner().getId() == connectedUser.getId()){

            repoScript.delete(s);

        }

        return new RedirectView("/index");

    }


}
