package s4.spring.quentin.td1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import s4.spring.quentin.td1.models.Categorie;
import s4.spring.quentin.td1.models.Element;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("items")
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
)
public class MainController {

    private ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();

    @ModelAttribute("items")
    public List<Categorie> getItems(){

        listeCategorie.add(new Categorie("Amis"));
        listeCategorie.add(new Categorie("Famille"));
        listeCategorie.add(new Categorie("Professionnels"));

        listeCategorie.get(0).ajoutElement(new Element("test"));
        listeCategorie.get(0).ajoutElement(new Element("rgrt"));
        listeCategorie.get(1).ajoutElement(new Element("grtgrt"));
        listeCategorie.get(1).ajoutElement(new Element("ghyt"));
        listeCategorie.get(1).ajoutElement(new Element("ytrhrt"));

        return listeCategorie;
    }

    @GetMapping("/items")
    public String viewListeCategorie(ModelMap model){

        model.put("categorie", listeCategorie);
        return "listeCategorie";
    }


    @GetMapping("/items/{cat}")
    public String viewListeItems(ModelMap model, @PathVariable String cat){
        String liste = "";

        for (Element e : getCat(cat).getListeElement()){
            liste += " Nom : "+e.getNom()+" avec une evaluation de "+e.getEvaluation()+"<br>";
        }

        model.put("liste", liste);
        return "listeItems";
    }

    @GetMapping("items/new")
    public String viewAddItems(){
        return "formulaireAjout";
    }

    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam String cat, @RequestParam String nom) {
        Element e = new Element();
        e.setNom(nom);
        getCat(cat).getListeElement().add(e);
        return new RedirectView("/items");
    }

    @GetMapping("/items/inc/{cat}/{nomElement}")
    public RedirectView viewIncrementation(@PathVariable String cat, @PathVariable String nomElement){

         for (Element e : getCat(cat).getListeElement()){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()+1);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/inc/{cat}/{nomElement}/{nb}")
    public RedirectView viewIncrementationNb(@PathVariable String cat, @PathVariable String nomElement, @PathVariable int nb){

         for (Element e : getCat(cat).getListeElement()){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()+nb);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/dec/{cat}/{nomElement}")
    public RedirectView viewDecrementation(@PathVariable String cat, @PathVariable String nomElement){

         for (Element e : getCat(cat).getListeElement()){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()-1);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/dec/{cat}/{nomElement}/{nb}")
    public RedirectView viewDecrementationNb(@PathVariable String nomElement, @PathVariable String cat, @PathVariable int nb){

        for (Element e : getCat(cat).getListeElement()){
            if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()-nb);
            }
        }

        return new RedirectView("/items");
    }

    @GetMapping("/items/delete/{cat}/{nomElement}")
    public RedirectView viewDelete(@PathVariable String cat, @PathVariable String nomElement){


        getCat(cat).supprimerElement(nomElement);


        return new RedirectView("/items");
    }


    public Categorie getCat(String s){
        for (Categorie c: listeCategorie){
            if(c.getLibelle().equals(s)){
                return c;
            }
        }
        return null;
    }


}
