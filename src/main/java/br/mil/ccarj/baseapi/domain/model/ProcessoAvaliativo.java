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
import java.util.Set;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "T_PROCESSO_AVALIATIVO")
@Audited(targetAuditMode = NOT_AUDITED)
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAvaliativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROCESSO_AVALIATIVO")
    private Long id;

    @Column(name = "NM_PROCESSO_AVALIATIVO", nullable = false)
    private String nome;

    @Column(name = "DESC_PROCESSO_AVALIATIVO")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "processoAvaliativo")
    private List<ProcessoDisciplina> processoDisciplina;

    @JsonIgnore
    @OneToMany(mappedBy = "processoAvaliativo")
    private List<ProcessoDiscente> processoDiscente;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "processosAvaliativos")
//    private List<Disciplina> disciplinas;
}
