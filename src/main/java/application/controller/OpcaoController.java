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

import application.record.OpcaoDTO;
import application.record.OpcaoInsertDTO;
import application.service.OpcaoService;

@RestController
@RequestMapping("/opcoes")
public class OpcaoController {
    @Autowired
    private OpcaoService opcaoService;

    @GetMapping
    public Iterable<OpcaoDTO> list() {
        return opcaoService.getAll();
    }

    @GetMapping("/{id}")
    public OpcaoDTO getOne(@PathVariable long id) {
        return opcaoService.getOne(id);
    }

    @PostMapping
    public OpcaoDTO insert(@RequestBody OpcaoInsertDTO opcao) {
        return opcaoService.insert(opcao);
    }

    @PutMapping("/{id}")
    public OpcaoDTO update(@PathVariable long id, @RequestBody OpcaoDTO opcao) {
        return opcaoService.update(id, opcao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        opcaoService.delete(id);
    }
}
