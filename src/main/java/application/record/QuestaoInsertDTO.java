package application.record;

import java.util.HashSet;

public record QuestaoInsertDTO(String enunciado, long id_categoria, HashSet<CategoriaDTO> categorias) {

}

