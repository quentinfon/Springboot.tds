package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.models.Groupe;
import s4.spring.td2.models.Organization;
import s4.spring.td2.repositories.GroupeRepository;
import s4.spring.td2.repositories.OrgaRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class MainController {

    @Autowired
    private OrgaRepository repoOrga;

    @Autowired
    private GroupeRepository repoGroupe;

    @GetMapping("/orgas")
    public String viewListeCategorie(ModelMap model){

        List<Organization> orgas = repoOrga.findAll();
        model.put("orgas", orgas);

        return "listeOrgas";
    }


    @GetMapping("/orgas/{id}")
    public String viewOrgaId(ModelMap model, @PathVariable int id){

        Organization orga = repoOrga.findById(id);

        model.put("orga", orga);
        return "listeItems";

    }

    @GetMapping("/orgas/new")
    public String viewFormOrga(ModelMap model){

        return "formOrga";

    }


    @PostMapping("orgas/addNew")
    public RedirectView addNewOrga(@RequestParam String nom, @RequestParam String domain, @RequestParam String alias) {

        Organization org = new Organization();
        org.setName(nom);
        org.setDomain(domain);
        org.setAliases(alias);

        repoOrga.save(org);

        return new RedirectView("/orgas");
    }

    @PostMapping("orgas/addEdit")
    public RedirectView addEditOrga(@RequestParam int id, @RequestParam String nom, @RequestParam String domain, @RequestParam String alias) {

        Organization org = repoOrga.findById(id);
        org.setName(nom);
        org.setDomain(domain);
        org.setAliases(alias);

        repoOrga.save(org);

        return new RedirectView("/orgas");
    }



    @GetMapping("/orgas/edit/{id}")
    public String viewModifOrga(ModelMap model, @PathVariable int id){

        Organization orga = repoOrga.findById(id);

        if (orga != null){
            model.put("orga", orga);
            return "formEditOrga";
        }else{
            return "orgaIntrouvable";
        }
    }

    @PostMapping("orgas/delete/{id}")
    public RedirectView deleteOrga(@PathVariable int id) {

        Organization org = repoOrga.findById(id);

        if (org != null){
            repoOrga.delete(org);
        }

        return new RedirectView("/orgas");
    }

    @PostMapping("orgas/confDelete/{id}")
    public String confDeleteOrga(@PathVariable int id, ModelMap model) {

        Organization org = repoOrga.findById(id);

        if (org != null){
            model.put("SuppressionID", id);
        }

        List<Organization> orgas = repoOrga.findAll();
        model.put("orgas", orgas);

        return "listeOrgas";
    }


    @GetMapping("/orgas/display/{id}")
    public String viewInfosOrga(ModelMap model, @PathVariable int id){

        Organization orga = repoOrga.findById(id);
        List<Groupe> listeG = repoGroupe.findAll();

        System.out.println(orga.getGroupes());

        if (orga != null){
            model.put("orga", orga);
            model.put("listeGroupe", listeG);
            return "consulterOrga";
        }else{
            return "orgaIntrouvable";
        }
    }


    @PostMapping("/orgas/ajoutNewGroupe")
    public RedirectView nouveauGroupeOrga(@RequestParam int orga, @RequestParam String nom) {

        Organization org = repoOrga.findById(orga);

        List<Groupe> verif = repoGroupe.findByName(nom);

        Groupe groupe;

        if (verif.size() == 0) {

            groupe = new Groupe();
            groupe.setName(nom);

            if (org != null) {
                groupe.setOrganization(org);
            }

            repoGroupe.save(groupe);

        }else {
            groupe = verif.get(0);
            if (org != null) {
                groupe.setOrganization(org);
            }
            repoGroupe.save(groupe);
        }


        return new RedirectView("/orgas/display/"+orga);
    }


    @PostMapping("/orgas/ajoutGroupe")
    public RedirectView ajoutGroupeOrga(@RequestParam int orga, @RequestParam int numGroupe) {

        Organization org = repoOrga.findById(orga);

        Groupe groupe = repoGroupe.findById(numGroupe);

        if (org != null && groupe != null) {
            groupe.setOrganization(org);
            repoGroupe.save(groupe);
        }

        return new RedirectView("/orgas/display/"+orga);
    }





}