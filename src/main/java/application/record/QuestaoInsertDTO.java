package application.record;

import java.util.HashSet;

public record QuestaoInsertDTO(String enunciado, HashSet<CategoriaDTO> categorias) {

}

