package br.mil.ccarj.baseapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "T_TIPO_DE_PROVA")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeProva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_DE_PROVA")
    private Long id;

    @Column(name = "NM_TIPO_DE_PROVA", nullable = false)
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "ID_PERIODO", referencedColumnName = "ID_PERIODO")
    private Periodo periodo;

}
