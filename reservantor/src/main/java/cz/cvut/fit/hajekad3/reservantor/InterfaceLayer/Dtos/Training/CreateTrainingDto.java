package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training;

import java.util.Date;

public class CreateTrainingDto {
    private Long idCoach;

    private Long idPlace;

    private Date dateOfTraining;

    private Date description;

    public CreateTrainingDto() {}

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
