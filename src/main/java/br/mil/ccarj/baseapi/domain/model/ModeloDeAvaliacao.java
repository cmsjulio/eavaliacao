package br.mil.ccarj.baseapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_MODELO_DE_AVALIACAO")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDeAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODELO")
    private Long id;

    @Column(name = "SG_MODELO", nullable = false)
    private String sigla;

    @Column(name = "NM_MODELO", nullable = false)
    private String nome;

    @Column(name = "DESC_MODELO", nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "modeloDeAvaliacao")
    private List<Periodo> periodos;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false, name= "ID_PROCESSO_DISCIPLINA", unique = true)
    private ProcessoDisciplina processoDisciplina;

}
