package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import jakarta.persistence.*;

@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_coach", nullable = false)
    private Long id;

    @Column(name = "cost_rate", nullable = false)
    private int costRate;

    @Column(name = "sport", nullable = false)
    private String sport;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    public Coach(){}

    public Coach(CreateCoachDto coachDto) {
        setCostRate(coachDto.getCostRate());
        setEmail(coachDto.getEmail());
        setPassword(coachDto.getPassword());
        setFirstName(coachDto.getFirstName());
        setSecondName(coachDto.getSecondName());
        setSport(coachDto.getSport());
    }

    public CoachDto convertToDto() {
        CoachDto ret = new CoachDto();

        ret.setId(getId());
        ret.setCostRate(getCostRate());
        ret.setEmail(getEmail());
        ret.setPassword(getPassword());
        ret.setFirstName(getFirstName());
        ret.setSecondName(getSecondName());
        ret.setSport(getSport());

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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
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
}