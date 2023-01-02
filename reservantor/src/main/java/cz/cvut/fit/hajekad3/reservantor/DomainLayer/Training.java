package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.IPlaceRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_training", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_coach", nullable = false)
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "id_place", nullable = false)
    private Place place;

    @Column(name = "date_of_training", nullable = false)
    private Timestamp dateOfTraining;

    @Column(name = "description", nullable = false)
    private String description;

    public Training() {}

    public Training(CreateTrainingDto trainingDto) {
        setDateOfTraining(Timestamp.valueOf(trainingDto.getDateOfTraining()));
        setDescription(trainingDto.getDescription());
        Long idCoach = trainingDto.getIdCoach();
    }

    public Training(TrainingDto trainingDto) {
        setId(trainingDto.getId());
        setDateOfTraining(Timestamp.valueOf(trainingDto.getDateOfTraining()));
        setDescription(trainingDto.getDescription());
    }

    public TrainingDto convertToDto() {
        TrainingDto ret = new TrainingDto();

        ret.setId(getId());
        ret.setDateOfTraining(getDateOfTraining().toString());
        ret.setDescription(getDescription());
        ret.setIdCoach(getCoach().getId());
        ret.setIdPlace(getPlace().getId());

        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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
