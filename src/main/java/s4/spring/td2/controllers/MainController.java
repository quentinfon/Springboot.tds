package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.models.Organization;
import s4.spring.td2.repositories.OrgaRepository;

import java.util.List;


@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class MainController {

    @Autowired
    private OrgaRepository repoOrga;

    @GetMapping("/orgas")
    public String viewListeCategorie(ModelMap model){

        List<Organization> orgas = repoOrga.findAll();
        model.put("orgas", orgas);

        return "listeOrgas";
    }


    @GetMapping("/orgas/{id}")
    public String viewOrgaId(ModelMap model, @PathVariable String id){

        Organization orga = repoOrga.findById(id);

        model.put("orga", orga);
        return "listeItems";

    }



}