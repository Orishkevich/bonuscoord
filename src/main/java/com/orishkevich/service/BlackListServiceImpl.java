package com.orishkevich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orishkevich.domain.Person;
import com.orishkevich.repository.BlackListRepository;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
@Service
public class BlackListServiceImpl implements BlackListService {
    private final BlackListRepository repository;

    @Autowired
    public BlackListServiceImpl(final BlackListRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isBlackListPerson(int personId) {
        return this.repository.findByPerson(new Person(personId)) != null;
    }
}
