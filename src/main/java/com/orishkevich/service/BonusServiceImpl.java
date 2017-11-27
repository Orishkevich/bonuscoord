package com.orishkevich.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orishkevich.domain.Bonus;
import com.orishkevich.domain.Person;
import com.orishkevich.repository.BonusRepository;

import java.util.List;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
@Service
public class BonusServiceImpl implements BonusService {
    private final BonusRepository repository;

    @Autowired
    public BonusServiceImpl(final BonusRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bonus apply(final Bonus bonus) {
        return this.repository.save(bonus);
    }

    @Override
    public List<Bonus> getAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    public List<Bonus> getByPerson(int personId) {
        return this.repository.findByPerson(new Person(personId));
    }
}
