package com.orishkevich.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;
import com.orishkevich.domain.Coordinates;
import com.orishkevich.domain.Bonus;
import com.orishkevich.domain.Person;
import com.orishkevich.repository.CoordinatesRepository;
import com.orishkevich.repository.PersonRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author orishkevich
 * @since 26.11.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BonusServiceTest {

    @Autowired
    private PersonRepository persons;

    @Autowired
    private CoordinatesRepository coords;

    @Autowired
    private BonusService service;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime time=LocalTime.now();
    String date=time.toString();

    @Test
    public void whenApplyBonusThenSaveInDb() {
        Person person = this.persons.save(new Person("Stanislav", "Orishkevich"));
        Coordinates coord = this.coords.save(new Coordinates(1.0, 2.0));
        Bonus bonus = this.service.apply(new Bonus(date, 1000d, coord, person));
        List<Bonus> result = this.service.getAll();
        assertTrue(result.contains(bonus));
    }

    @Test
    public void whenFindByPersonThenReturnListOnlyForPerson() {
        Person person = this.persons.save(new Person("Stanislav", "Orishkevich"));
        Coordinates coord = this.coords.save(new Coordinates(3.0, 4.0));
        Bonus bonus = this.service.apply(new Bonus(date, 1000d, coord, person));
        List<Bonus> result = this.service.getByPerson(person.getId());
        assertThat(result.iterator().next(), is(bonus));
    }

}