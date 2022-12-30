package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_training", nullable = false)
    private Long id;

    @Column(name = "id_coach", nullable = false)
    private Long idCoach;

    @Column(name = "id_place", nullable = false)
    private Long idPlace;

    @Column(name = "date_of_training", nullable = false)
    private Date dateOfTraining;

    @Column(name = "description", nullable = false)
    private Date description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Long idCoach) {
        this.idCoach = idCoach;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public Date getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(Date dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }

    public Date getDescription() {
        return description;
    }

    public void setDescription(Date description) {
        this.description = description;
    }
}
