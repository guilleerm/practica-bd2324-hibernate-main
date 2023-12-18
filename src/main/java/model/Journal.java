package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// @TODO Realiza todas las anotaciones necesarias en esta clase para que
// que sus instancias sean guardadas en la base de datos utilizando
// Hibernate. Respecta las restricciones de modelado impuestas en el
// enunciado de la práctica. No es necesario modificar el código de esta
// clase, únicamente debes hacer las anotaciones que consideres
// necesarias.

@Entity
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journal_id;

    @Column(name = "journal_name")
    private String journal_name;

    @Column(name = "issn")
    private String issn;

    @Column(name = "jif")
    private Double jif;

    @Column(name = "jif_quartile")
    private String jif_quartile;

    @OneToMany(mappedBy = "journal")
    private Set<Article> articles;

    public Journal() {

    }

    public Journal (Long journal_id, String journal_name, String issn, Double jif, String jif_quartile) {
        this.journal_id = journal_id;
        this.journal_name = journal_name;
        this.issn = issn;
        this.jif = jif;
        this.jif_quartile = jif_quartile;
        this.articles = new HashSet<>();
    }

    public Long getJournal_id() {
        return journal_id;
    }

    public String getJournal_name() {
        return journal_name;
    }

    public String getIssn() {
        return issn;
    }

    public Double getJif() {
        return jif;
    }

    public String getJif_quartile() {
        return jif_quartile;
    }

    public Set<Article> getArticles() {
        return articles;
    }
}
