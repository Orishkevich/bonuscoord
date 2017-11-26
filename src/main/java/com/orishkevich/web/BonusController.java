package com.orishkevich.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.orishkevich.domain.Bonus;
import com.orishkevich.service.BlackListService;
import com.orishkevich.service.BonusService;
import com.orishkevich.web.forms.Error;
import com.orishkevich.web.forms.Result;
import com.orishkevich.web.forms.Success;

import java.util.List;

/**
 * @author orishkevich
 * @since 26.11.2017
 */

@RestController
public class BonusController {

    private final BonusService bonus;

    private final BlackListService blacklists;

    @Autowired
    public BonusController(final BonusService bonus, final BlackListService blacklists) {
        this.bonus = bonus;
        this.blacklists = blacklists;
    }

    @PostMapping("/")
    public Result apply(@RequestBody Bonus bonus) {
        final Result result;
        if (!this.blacklists.isBlackListPerson(bonus.getPerson().getId())) {
            result = new Success<Bonus>(
                    this.bonus.apply(bonus)
            );
        } else {
            result = new Error(String.format("User %s in blacklist", bonus.getPerson().getId()));
        }
        return result;
    }

    @GetMapping("/")
    public List<Bonus> getAll() {
        return this.bonus.getAll();
    }

    @GetMapping("/{personId}")
    public List<Bonus> findByPersonId(@PathVariable int personId) {
        return this.bonus.getByPerson(personId);
    }
}
