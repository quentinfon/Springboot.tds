package s4.spring.quentin.td1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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

    private ArrayList<Element> listElem = new ArrayList<Element>();

    @ModelAttribute("items")
    public List<Element> getItems(){
        return listElem;
    }


    @GetMapping("/items")
    public String viewListeItems(ModelMap model){
        String liste = "";

        for (Element e : listElem){
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
    public RedirectView addNew(@RequestParam String nom) {
        Element e = new Element();
        e.setNom(nom);
        listElem.add(e);
        return new RedirectView("/items");
    }

    @GetMapping("/items/inc/{nomElement}")
    public RedirectView viewIncrementation(@PathVariable String nomElement){

         for (Element e : listElem){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()+1);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/inc/{nomElement}/{nb}")
    public RedirectView viewIncrementationNb(@PathVariable String nomElement, @PathVariable int nb){

         for (Element e : listElem){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()+nb);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/dec/{nomElement}")
    public RedirectView viewDecrementation(@PathVariable String nomElement){

         for (Element e : listElem){
             if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()-1);
             }
         }

        return new RedirectView("/items");
    }

    @GetMapping("/items/dec/{nomElement}/{nb}")
    public RedirectView viewDecrementationNb(@PathVariable String nomElement, @PathVariable int nb){

        for (Element e : listElem){
            if(e.getNom().equals(nomElement)){
                e.setEvaluation(e.getEvaluation()-nb);
            }
        }

        return new RedirectView("/items");
    }

    @GetMapping("/items/delete/{nomElement}")
    public RedirectView viewDelete(@PathVariable String nomElement){

        ArrayList<Element> aSupp = new ArrayList<Element>();

        for (Element e : listElem){
            if(e.getNom().equals(nomElement)){
                aSupp.add(e);
            }
        }

        for (Element e: aSupp){
            listElem.remove(e);
        }

        return new RedirectView("/items");
    }




}
