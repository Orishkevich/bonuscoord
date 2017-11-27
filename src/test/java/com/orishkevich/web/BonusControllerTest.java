package com.orishkevich.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.orishkevich.domain.Coordinates;
import com.orishkevich.domain.Bonus;
import com.orishkevich.domain.Person;
import com.orishkevich.service.BlackListService;
import com.orishkevich.service.LimitService;
import com.orishkevich.service.BonusService;
import com.orishkevich.web.forms.Error;
import com.orishkevich.web.forms.Success;


import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * //TODO add comments
 *
 * @author orishkevich
 * @since 26.11.2017
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BonusController.class)
public class BonusControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlackListService blacks;

    @MockBean
    private BonusService bonuses;

    @MockBean
    private LimitService limit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time=LocalTime.now();
    String date=time.toString();

    @Test
    public void whenPersonNotInBlackListThenApplyBonus() throws Exception {
        List<Bonus> list = Collections.singletonList(
                new Bonus(date, 1D, new Coordinates(0.0, 0.0), new Person("Vasya", "Pupkin")));
        ObjectMapper mapper = new ObjectMapper();
        given(this. bonuses.getAll()).willReturn(list);
        this.mvc.perform(
                get("/").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenLoadThenApplyBonus() throws Exception {
        List<Bonus> list = Collections.singletonList(
                new Bonus(date, 1D, new Coordinates(0.0, 0.0), new Person("Vasya", "Pupkin")));
        ObjectMapper mapper = new ObjectMapper();
        given(this. bonuses.getByPerson(0)).willReturn(list);
        this.mvc.perform(
                get("/0").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenApplyThenSave() throws Exception {
        Bonus bonus = new Bonus(date, 1D, new Coordinates(0.0, 0.0), new Person("Vasya", "Pupkin"));
        ObjectMapper mapper = new ObjectMapper();
        given(this.blacks.isBlackListPerson(0)).willReturn(false);
        given(this.bonuses.apply(bonus)).willReturn(bonus);
        this.mvc.perform(
                post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(
                                bonus
                        )
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Success<Bonus>(bonus)))
        );
    }

    @Test
    public void whenInBlacklistThenError() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given(this.blacks.isBlackListPerson(0)).willReturn(true);
        this.mvc.perform(
                post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(
                                new Bonus(date, 1D, new Coordinates(0.0, 0.0), new Person("Vasya", "Pupkin"))
                        )
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Error("User 0 in blacklist")))
        );
    }

}