package pl.lostworld.lostworldbackend.country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.continent.ContinentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private ContinentService continentService;
    private CountryService countryService;

    public CountryController(ContinentService continentService, CountryService countryService) {
        this.continentService = continentService;
        this.countryService = countryService;
    }

    @GetMapping("/checkAll")
    public List<Country> checkAllCountries() {
        return countryService.checkAll();
    }

    @GetMapping("/add")
    public Country addCountry() {
        return new Country();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@Valid @RequestBody Country country) {
        return countryService.save(country);
    }

    ///////////////DEPRECATED/////////////////
//    @GetMapping("/add")
//    public String addCountry(Model model) {
//        Country country = new Country();
//        model.addAttribute("country", country);
//        model.addAttribute("continents", continentService.checkAll());
//        return "adminTemplates/addCountry";
//    }
//
//    @PostMapping("/add")
//    public String addCountry(Model model, @Valid Country country, BindingResult result) {
//        if (result.hasErrors()) {
//            model.addAttribute("continents", continentService.checkAll());
//            return "adminTemplates/addCountry";
//        }
//        countryService.addCountry(country);
//        return "redirect:/countries/checkAll";
//    }
}
