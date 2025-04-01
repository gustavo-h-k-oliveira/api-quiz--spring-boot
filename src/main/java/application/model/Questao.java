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

import application.record.QuestaoDTO;
import application.record.QuestaoInsertDTO;


@Entity
@Table(name = "questões")
@Getter
@Setter
@NoArgsConstructor
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String enunciado;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public Questao(QuestaoDTO record) {
        this.id = record.id();
        this.enunciado = record.enunciado();
        this.categoria = new Categoria(record.categorias());
    }

    public Questao(QuestaoInsertDTO record) {
        this.enunciado = record.enunciado();
        this.categoria = new Categoria(record.categorias());
    }
}
