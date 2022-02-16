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

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "T_PROCESSO_DISCIPLINA")
@Audited(targetAuditMode = NOT_AUDITED)
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROCESSO_DISCIPLINA")
    private Long id;

//    @OneToOne(mappedBy = "processoDisciplina")
//    private ModeloDeAvaliacao modeloDeAvaliacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "ID_MODELO", referencedColumnName = "ID_MODELO")
    private ModeloDeAvaliacao modeloDeAvaliacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "ID_PROCESSO_AVALIATIVO", referencedColumnName = "ID_PROCESSO_AVALIATIVO")
    private ProcessoAvaliativo processoAvaliativo;

    @ManyToOne
    @JoinColumn(nullable = false, name = "ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    private Disciplina disciplina;

}
