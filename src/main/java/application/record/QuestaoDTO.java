package application.record;

import application.model.Questao;
import java.util.HashSet;

import java.util.stream.Collectors;

public record QuestaoDTO (
    long id,
    String enunciado,
    HashSet<CategoriaDTO> categorias
) {
    public QuestaoDTO(Questao questoes) {
        this(
            questoes.getId(),
            questoes.getEnunciado(),
            new CategoriaDTO(questoes.getCategoria()),
            questoes.getCategoria()
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toCollection(HashSet::new))
        );
    }
}
