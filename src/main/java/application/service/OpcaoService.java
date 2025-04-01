package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import application.model.Opcao;
import application.record.OpcaoDTO;
import application.record.OpcaoInsertDTO;
import application.repository.QuestaoRepository;
import application.repository.OpcaoRepository;

@Service
public class OpcaoService {
    @Autowired
    private OpcaoRepository opcaoRepo;
    @Autowired
    private QuestaoRepository questaoRepo;
    
    public Iterable<OpcaoDTO> getAll() {
        return opcaoRepo.findAll().stream().map(OpcaoDTO::new).toList();
    }

    public OpcaoDTO getOne(long id) {
        Optional<Opcao> resultado = opcaoRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }
        
        return new OpcaoDTO(resultado.get());
    }

    public OpcaoDTO insert(OpcaoInsertDTO opcao) {
        if (!questaoRepo.existsById(opcao.id_questao())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }

        // for (AutorDTO a : questao.autores()) {
        //     if (!autorRepo.existsById(a.id())) {
        //         throw new ResponseStatusException(
        //             HttpStatus.NOT_FOUND, "Autor Não Encontrado"
        //         );
        //     }
        // }

        Opcao newOpcao = new Opcao(opcao);
        Opcao savedOpcao = opcaoRepo.save(newOpcao);
        OpcaoDTO response = new OpcaoDTO(savedOpcao);
        return response;
    }

    public OpcaoDTO update(long id, OpcaoDTO opcao) {
        Optional<Opcao> resultado = opcaoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }

        resultado.get().setDescricao(opcao.descricao());
        // resultado.get().setAutores(questao.autores()
        //     .stream().map(Autor::new)
        //     .collect(Collectors.toCollection(HashSet::new))
        // );

        return new OpcaoDTO(opcaoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!opcaoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opcao Não Encontrada"
            );
        }

        opcaoRepo.deleteById(id);
    }
}
