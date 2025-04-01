package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.QuestaoDTO;
import application.record.QuestaoInsertDTO;
import application.service.QuestaoService;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    @Autowired
    private QuestaoService questaoService;

    @GetMapping
    public Iterable<QuestaoDTO> list() {
        return questaoService.getAll();
    }

    @GetMapping("/{id}")
    public QuestaoDTO getOne(@PathVariable long id) {
        return questaoService.getOne(id);
    }

    @PostMapping
    public QuestaoDTO insert(@RequestBody QuestaoInsertDTO questao) {
        return questaoService.insert(questao);
    }

    @PutMapping("/{id}")
    public QuestaoDTO update(@PathVariable long id, @RequestBody QuestaoDTO questao) {
        return questaoService.update(id, questao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        questaoService.delete(id);
    }
}
