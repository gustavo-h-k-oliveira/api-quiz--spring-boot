package application.record;

import application.model.Questao;

public record QuestaoDTO (
    long id,
    String enunciado,
    CategoriaDTO categorias
) {
    public QuestaoDTO(Questao questoes) {
        this(
            questoes.getId(),
            questoes.getEnunciado(),
            new CategoriaDTO(questoes.getCategoria())
        );
    }
}
