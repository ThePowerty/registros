package com.Nerea.regis.rest;

import com.Nerea.regis.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FimlsController {

    private final Logger log = LoggerFactory.getLogger(FimlsController.class);

    FilmService filmService;

    public FimlsController(FilmService filmService) {
        this.filmService = filmService;
    }
}
