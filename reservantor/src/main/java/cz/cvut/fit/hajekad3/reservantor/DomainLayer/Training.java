package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import jakarta.persistence.*;

import java.sql.Timestamp;
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
    private Timestamp dateOfTraining;

    @Column(name = "description", nullable = false)
    private String description;

    public Training() {}

    public Training(CreateTrainingDto trainingDto) {
        setDateOfTraining(Timestamp.valueOf(trainingDto.getDateOfTraining()));
        setDescription(trainingDto.getDescription());
        setIdCoach(trainingDto.getIdCoach());
        setIdPlace(trainingDto.getIdPlace());
    }

    public TrainingDto convertToDto() {
        TrainingDto ret = new TrainingDto();

        ret.setId(getId());
        ret.setDateOfTraining(getDateOfTraining().toString());
        ret.setDescription(getDescription());
        ret.setIdCoach(getIdCoach());
        ret.setIdPlace(getIdPlace());

        return ret;
    }

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

    public Timestamp getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(Timestamp dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
