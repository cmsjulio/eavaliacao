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
@Table(name = "T_PERIODO")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERIODO")
    private Long id;

    @Column(name = "NM_PERIODO", nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "ID_MODELO", referencedColumnName = "ID_MODELO")
    private ModeloDeAvaliacao modeloDeAvaliacao;

    @OneToMany(mappedBy = "periodo")
    private List<TipoDeProva> tiposDeProvas;
}
