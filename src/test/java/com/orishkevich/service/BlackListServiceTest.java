package com.orishkevich.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.orishkevich.domain.BlackList;
import com.orishkevich.domain.Person;
import com.orishkevich.repository.BlackListRepository;
import com.orishkevich.repository.PersonRepository;

import static org.junit.Assert.*;

/**
 * @author orishkevich
 * @since 26.11.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackListServiceTest {

    @Autowired
    private PersonRepository persons;

    @Autowired
    private BlackListRepository blacklists;

    @Autowired
    private BlackListService service;

    @Test
    public void whenPersonInBlackListThenReturnTrue() {
        Person person = this.persons.save(new Person("Veronika", "Sadovskaya"));
        this.blacklists.save(new BlackList(person));
        boolean result = this.service.isBlackListPerson(person.getId());
        assertTrue(result);
    }

    @Test
    public void whenBlackListEmptyThenAnyPersonNotIn() {
        Person person = this.persons.save(new Person("Veronika", "Sadovskaya"));
        boolean result = this.service.isBlackListPerson(person.getId());
        assertFalse(result);
    }
}