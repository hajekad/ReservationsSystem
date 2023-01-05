package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_trainee")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "credit", nullable = true)
    private int credit;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "skill_cap", nullable = false)
    private int skillCap;

    @ManyToMany
    @Column(name = "trainings", nullable = true)
    private Collection<Training> trainings;

    public Trainee(){
        trainings = new ArrayList<Training>();
    }

    public Trainee(CreateTraineeDto traineeDto)
    {
        trainings = new ArrayList<Training>();
        setEmail(traineeDto.getEmail());
        setPassword(traineeDto.getPassword());
        setFirstName(traineeDto.getFirstName());
        setSecondName(traineeDto.getSecondName());
        setUsername(traineeDto.getUsername());
        setCredit(0);
        setSkillCap(50);
    }

    public Trainee(TraineeDto traineeDto)
    {
        trainings = new ArrayList<Training>();
        setId(traineeDto.getId());
        setEmail(traineeDto.getEmail());
        setPassword(traineeDto.getPassword());
        setFirstName(traineeDto.getFirstName());
        setSecondName(traineeDto.getSecondName());
        setUsername(traineeDto.getUsername());
        setSkillCap(traineeDto.getSkillCap());
        setCredit(traineeDto.getCredit());
    }

    public TraineeDto convertToDto()
    {
        TraineeDto ret = new TraineeDto();

        ArrayList<Long> tmp = new ArrayList<Long>();
        for (Training i: trainings) {
            tmp.add(i.getId());
        }
        ret.setTrainings(tmp);

        ret.setId(getId());
        ret.setEmail(getEmail());
        ret.setPassword(getPassword());
        ret.setFirstName(getFirstName());
        ret.setSecondName(getSecondName());
        ret.setUsername(getUsername());
        ret.setSkillCap(getSkillCap());
        ret.setCredit(getCredit());

        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getSkillCap() {
        return skillCap;
    }

    public void setSkillCap(int skillCap) {
        this.skillCap = skillCap;
    }

    public Collection<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Training> trainings) {
        this.trainings = trainings;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
