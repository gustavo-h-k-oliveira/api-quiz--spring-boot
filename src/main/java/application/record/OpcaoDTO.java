package application.record;

import application.model.Opcao;

public record OpcaoDTO(
    long id,
    String descricao,
    Boolean correto,
    QuestaoDTO questao
) {
    public OpcaoDTO(Opcao opcoes) {
        this(
            opcoes.getId(),
            opcoes.getDescricao(),
            opcoes.getCorreto(),
            new QuestaoDTO(opcoes.getQuestao())
        );
    }
}
