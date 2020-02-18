package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.models.Organization;
import s4.spring.td2.repositories.GroupeRepository;
import s4.spring.td2.repositories.OrgaRepository;
import s4.spring.td2.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class RestOrgasController {

    @Autowired
    private OrgaRepository repoOrga;

    @Autowired
    private GroupeRepository repoGroupe;

    @Autowired
    private UserRepository repoUser;


    @GetMapping("/rest/orgas/")
    public List<Organization> viewListeCategorie(){
        return repoOrga.findAll();
    }

    @GetMapping("/rest/orgas/{id}")
    public Organization viewOrgaId(@PathVariable int id){
        return repoOrga.findById(id);
    }

    @PostMapping("/rest/orgas/create")
    public Organization addNewOrga(@RequestBody Organization o) {
        repoOrga.saveAndFlush(o);
        return o;
    }

    @PutMapping("/rest/orgas/update")
    public Organization addEditOrga(@RequestBody Organization o) {

        Organization org =  repoOrga.findById(o.getId());

        if (org != null){
            org = o;
            repoOrga.saveAndFlush(org);
        }

        return o;
    }

    @DeleteMapping("/rest/orgas/delete")
    public Organization deleteOrga(@RequestBody Organization o) {

        Organization org =  repoOrga.findById(o.getId());
        if (org != null){
            repoOrga.delete(org);
        }
        return org;
    }

}
