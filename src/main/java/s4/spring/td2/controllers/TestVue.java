package s4.spring.td2.controllers;

import io.github.jeemv.springboot.vuejs.VueJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestVue {
    @Autowired
    private VueJS vue;

    @GetMapping("/testVue")
    public String index(ModelMap model){
        vue.addData("message", "Test de message");
        model.put("vue", this.vue);
        return "index";
    }
}
