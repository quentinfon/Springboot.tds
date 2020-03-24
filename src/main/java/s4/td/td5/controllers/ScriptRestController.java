package s4.td.td5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s4.td.td5.models.Script;
import s4.td.td5.repositories.ScriptRepository;

import java.util.List;

@RestController
@RequestMapping(
        method={RequestMethod.POST, RequestMethod.GET}
)
public class ScriptRestController {


    @Autowired
    private ScriptRepository repoScript;


    @GetMapping("/rest/script/search/{s}")
    public List<Script> viewListeCategorie(@PathVariable String s){
        return repoScript.recherche(s.toUpperCase());
    }

    @GetMapping("/rest/script/search/")
    public List<Script> viewSearchScripts(){
        return repoScript.findAll();
    }


}
