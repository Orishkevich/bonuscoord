package com.orishkevich.repository;

import org.springframework.data.repository.CrudRepository;
import com.orishkevich.domain.Person;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
