package pl.lostworld.lostworldbackend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.city.CityService;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.continent.ContinentService;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.country.CountryService;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.relic.RelicService;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.UserService;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;
import pl.lostworld.lostworldbackend.user.additionalResources.report.ReportService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Override
    public void initialize(Exists constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        if (objectToValidate instanceof User) {
            return userService.checkIfExists(((User) objectToValidate).getId());
        } else if (objectToValidate instanceof Report) {
            return reportService.checkIfExists(((Report) objectToValidate).getId());
        }
        return true;
    }
}
