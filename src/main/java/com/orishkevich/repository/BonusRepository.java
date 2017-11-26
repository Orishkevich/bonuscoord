package com.orishkevich.repository;

import org.springframework.data.repository.CrudRepository;
import com.orishkevich.domain.Bonus;
import com.orishkevich.domain.Person;

import java.util.List;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
public interface BonusRepository extends CrudRepository<Bonus, Integer> {
    List<Bonus> findByPerson(Person person);
}
