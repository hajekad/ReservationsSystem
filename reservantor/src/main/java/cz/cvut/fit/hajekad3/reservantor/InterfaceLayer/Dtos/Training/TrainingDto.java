package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training;

import java.util.Date;

public class TrainingDto {
    private Long id;

    private Long idCoach;

    private Long idPlace;

    private String dateOfTraining;

    private String description;

    public TrainingDto() {}

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

    public String getDateOfTraining() {
        return dateOfTraining;
    }

    public void setDateOfTraining(String dateOfTraining) {
        this.dateOfTraining = dateOfTraining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
