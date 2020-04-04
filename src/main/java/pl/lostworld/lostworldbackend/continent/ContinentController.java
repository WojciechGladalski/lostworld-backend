package pl.lostworld.lostworldbackend.continent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/continents")
public class ContinentController {

    private ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/checkAll")
    @ResponseBody
    public List<Continent> checkAllContinents() {
        return continentService.checkAll();
    }

    @GetMapping("/add")
    public String addContinent(Model model) {
        model.addAttribute("continent", new Continent());
        return "adminTemplates/addContinent";
    }

    @PostMapping("/add")
    public String addContinent(@Valid Continent continent, BindingResult result) {
        if (result.hasErrors()) {
            return "adminTemplates/addContinent";
        }
        continentService.addContinent(continent);
        return "redirect:/continents/checkAll";
    }

}