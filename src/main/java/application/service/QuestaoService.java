package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import application.model.Questao;
import application.record.QuestaoDTO;
import application.record.QuestaoInsertDTO;
import application.repository.CategoriaRepository;
import application.repository.QuestaoRepository;

@Service
public class QuestaoService {
    @Autowired
    private QuestaoRepository questaoRepo;
    @Autowired
    private CategoriaRepository categoriaRepo;
    
    public Iterable<QuestaoDTO> getAll() {
        return questaoRepo.findAll().stream().map(QuestaoDTO::new).toList();
    }

    public QuestaoDTO getOne(long id) {
        Optional<Questao> resultado = questaoRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Questao Não Encontrada"
            );
        }
        
        return new QuestaoDTO(resultado.get());
    }

    public QuestaoDTO insert(QuestaoInsertDTO questao) {
        if (!categoriaRepo.existsById(questao.id_categoria())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Categoria Não Encontrada"
            );
        }

        // for (AutorDTO a : questao.autores()) {
        //     if (!autorRepo.existsById(a.id())) {
        //         throw new ResponseStatusException(
        //             HttpStatus.NOT_FOUND, "Autor Não Encontrado"
        //         );
        //     }
        // }

        Questao newQuestao = new Questao(questao);
        Questao savedQuestao = questaoRepo.save(newQuestao);
        QuestaoDTO response = new QuestaoDTO(savedQuestao);
        return response;
    }

    public QuestaoDTO update(long id, QuestaoDTO questao) {
        Optional<Questao> resultado = questaoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Questao Não Encontrada"
            );
        }

        resultado.get().setEnunciado(questao.enunciado());
        // resultado.get().setAutores(questao.autores()
        //     .stream().map(Autor::new)
        //     .collect(Collectors.toCollection(HashSet::new))
        // );

        return new QuestaoDTO(questaoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!questaoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Questao Não Encontrada"
            );
        }

        questaoRepo.deleteById(id);
    }
}
