package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import application.record.OpcaoDTO;
import application.record.OpcaoInsertDTO;

@Entity
@Table(name = "opções")
@Getter
@Setter
@NoArgsConstructor
public class Opcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private Boolean correto;

    @ManyToOne
    @JoinColumn(name = "id_questao", nullable = false)
    private Questao questao;

    public Opcao(OpcaoDTO record) {
        this.id = record.id();
        this.descricao = record.descricao();
        this.correto = record.correto();
        this.questao = new Questao(record.questao());
    }

    public Opcao(OpcaoInsertDTO record) {
        this.descricao = record.descricao();
        this.correto = record.correto();
        this.questao = new Questao(record.questao());
    }
}
