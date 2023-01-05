package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@Entity(name = "Training")
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

    @ManyToMany
    @Column(name = "trainees", nullable = true)
    private Collection<Trainee> participatingTrainees;

    public Training() {
        participatingTrainees = new ArrayList<Trainee>();
    }

    private boolean validateTimestamp(String timestampString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            sdf.parse(timestampString);
        }
        catch (ParseException e) {
            return false;
        }

        return true;
    }

    public Training(CreateTrainingDto trainingDto) {
        if(!validateTimestamp(trainingDto.getDateOfTraining()))
            throw new NoSuchElementException();

        participatingTrainees = new ArrayList<Trainee>();
        setDateOfTraining(Timestamp.valueOf(trainingDto.getDateOfTraining()));
        setDescription(trainingDto.getDescription());
    }

    public Training(TrainingDto trainingDto) {
        if(trainingDto.getId() == null || !validateTimestamp(trainingDto.getDateOfTraining()))
            throw new NoSuchElementException();

        participatingTrainees = new ArrayList<Trainee>();
        setId(trainingDto.getId());
        setDateOfTraining(Timestamp.valueOf(trainingDto.getDateOfTraining()));
        setDescription(trainingDto.getDescription());
    }

    public TrainingDto convertToDto() {
        TrainingDto ret = new TrainingDto();

        ArrayList<Long> tmp = new ArrayList<Long>();
        for (Trainee i: participatingTrainees) {
            tmp.add(i.getId());
        }
        ret.setParticipatingTraineesIds(tmp);

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

    public Collection<Trainee> getParticipatingTrainees() {
        return participatingTrainees;
    }

    public void setParticipatingTrainees(Collection<Trainee> participatingTrainees) {
        this.participatingTrainees = participatingTrainees;
    }
}
