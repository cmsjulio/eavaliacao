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
@Table(name = "T_DISCENTE")
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCENTE")
    private Long id;

    @Column(name = "NM_DISCENTE")
    private String nome;

//    @JsonIgnore
//    @OneToMany(mappedBy = "discente")
//    private List<ProcessoDiscente> processoDiscentes;

//    @ManyToMany
//    @JoinTable(
//            name = "disciplina_processo",
//            joinColumns = @JoinColumn(name = "disciplinaId"),
//            inverseJoinColumns = @JoinColumn(name = "processoId")
//    )
//    private List<ProcessoAvaliativo> processosAvaliativos;

}
