package pl.lostworld.lostworldbackend.country;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;
import pl.lostworld.lostworldbackend.user.additionalResources.article.Article;
import pl.lostworld.lostworldbackend.validator.country.UniqueCountryField;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "countries")
public class Country {

    public Country(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @UniqueCountryField(column = "name")
    private String name;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
    @NotEmpty
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Continent> continents = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "country")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<CountryRating> countryRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    private String description;

    @ManyToMany(mappedBy = "countries")
    private List<Article> articles = new ArrayList<>();
}
