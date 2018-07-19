package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class SalvoController {

        @Autowired
        private GameRepository repo;

        @RequestMapping("/games")
        public List<Object> getGamesIds() {
            return repo
             .findAll()
                    .stream()
                    .map(game -> makeGamesDTO(game))
                    .collect(Collectors.toList());

//            return repo.findAll().stream().map(b->b.getId()).collect(toList());
         }
    private Map<String, Object> makeGamesDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("created", game.getStartDate());
        return dto;
    }
    }

