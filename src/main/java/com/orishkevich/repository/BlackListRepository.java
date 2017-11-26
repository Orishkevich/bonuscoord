package com.orishkevich.repository;

import org.springframework.data.repository.CrudRepository;
import com.orishkevich.domain.BlackList;
import com.orishkevich.domain.Person;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
public interface BlackListRepository extends CrudRepository<BlackList, Integer> {

    BlackList findByPerson(Person person);
}
