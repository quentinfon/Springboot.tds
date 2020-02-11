package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.td2.models.Groupe;
import s4.spring.td2.models.Organization;
import s4.spring.td2.models.User;
import s4.spring.td2.repositories.GroupeRepository;
import s4.spring.td2.repositories.OrgaRepository;
import s4.spring.td2.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class UserController {


    @Autowired
    private OrgaRepository repoOrga;

    @Autowired
    private GroupeRepository repoGroupe;

    @Autowired
    private UserRepository repoUser;

    @PostMapping("/orgas/ajoutNewUser")
    public RedirectView nouveauUserOrga(@RequestParam int orga, @RequestParam String prenom, @RequestParam String nom, @RequestParam String mail) {

        Organization org = repoOrga.findById(orga);

        List<User> verif = repoUser.findByEmail(mail);

        User uti;

        if (verif.size() == 0) {

            uti = new User();
            uti.setFirstname(prenom);
            uti.setLastname(nom);
            uti.setEmail(mail);

            if (org != null) {
                uti.setOrganization(org);
            }

            repoUser.save(uti);

        }else {
            uti = verif.get(0);
            if (org != null) {
                uti.setOrganization(org);
            }
            repoUser.save(uti);
        }


        return new RedirectView("/orgas/display/"+orga);
    }


    @PostMapping("/orgas/ajoutUser")
    public RedirectView ajoutUserOrga(@RequestParam int orga, @RequestParam int numUser) {

        Organization org = repoOrga.findById(orga);

        User u = repoUser.findById(numUser);

        if (org != null && u != null) {
            u.setOrganization(org);
            repoUser.save(u);
        }

        return new RedirectView("/orgas/display/"+orga);
    }


}
