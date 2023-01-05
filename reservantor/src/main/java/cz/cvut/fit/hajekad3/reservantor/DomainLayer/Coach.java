package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@Entity(name = "Coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_coach", nullable = false)
    private Long id;

    @Column(name = "cost_rate", nullable = false)
    private int costRate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @OneToMany
    @Column(name = "trainings", nullable = true)
    private Collection<Training> trainings;

    public Coach(){
        this.trainings = new ArrayList<Training>();
    }

    public Coach(CreateCoachDto coachDto) {
        this.trainings = new ArrayList<Training>();
        setCostRate(coachDto.getCostRate());
        setEmail(coachDto.getEmail());
        setPassword(coachDto.getPassword());
        setFirstName(coachDto.getFirstName());
        setSecondName(coachDto.getSecondName());
    }

    public Coach(CoachDto coachDto) {
        if(coachDto.getId() == null)
            throw new NoSuchElementException();

        this.trainings = new ArrayList<Training>();
        setId(coachDto.getId());
        setCostRate(coachDto.getCostRate());
        setEmail(coachDto.getEmail());
        setPassword(coachDto.getPassword());
        setFirstName(coachDto.getFirstName());
        setSecondName(coachDto.getSecondName());
    }

    public CoachDto convertToDto() {
        CoachDto ret = new CoachDto();

        ArrayList<Long> tmp = new ArrayList<>();
        for(Training i : this.trainings) {
            tmp.add(i.getId());
            System.out.println(i.getId());
        }
        ret.setTrainings(tmp);

        ret.setId(getId());
        ret.setCostRate(getCostRate());
        ret.setEmail(getEmail());
        ret.setPassword(getPassword());
        ret.setFirstName(getFirstName());
        ret.setSecondName(getSecondName());

        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCostRate() {
        return costRate;
    }

    public void setCostRate(int costRate) {
        this.costRate = costRate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Collection<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Training> trainings) {
        this.trainings = trainings;
    }
}
