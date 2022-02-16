package br.mil.ccarj.baseapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_DISCIPLINA")
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCIPLINA")
    private Long id;

    @Column(name = "NM_DISCIPLINA")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "disciplina")
    private List<ProcessoDisciplina> processoDisciplina;

//    @ManyToMany
//    @JoinTable(
//            name = "disciplina_processo",
//            joinColumns = @JoinColumn(name = "disciplinaId"),
//            inverseJoinColumns = @JoinColumn(name = "processoId")
//    )
//    private List<ProcessoAvaliativo> processosAvaliativos;

}
