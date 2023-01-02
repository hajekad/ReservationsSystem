package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import jakarta.persistence.*;

@Entity
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "skill_cap", nullable = false)
    private int skillCap;

    public Trainee(){}

    public Trainee(CreateTraineeDto traineeDto)
    {
        setEmail(traineeDto.getEmail());
        setPassword(traineeDto.getPassword());
        setFirstName(traineeDto.getFirstName());
        setSecondName(traineeDto.getSecondName());
        setUsername(traineeDto.getUsername());
        setSkillCap(50);
    }

    public Trainee(TraineeDto traineeDto)
    {
        setId(traineeDto.getId());
        setEmail(traineeDto.getEmail());
        setPassword(traineeDto.getPassword());
        setFirstName(traineeDto.getFirstName());
        setSecondName(traineeDto.getSecondName());
        setUsername(traineeDto.getUsername());
        setSkillCap(traineeDto.getSkillCap());
    }

    public TraineeDto convertToDto()
    {
        TraineeDto ret = new TraineeDto();

        ret.setId(getId());
        ret.setEmail(getEmail());
        ret.setPassword(getPassword());
        ret.setFirstName(getFirstName());
        ret.setSecondName(getSecondName());
        ret.setUsername(getUsername());
        ret.setSkillCap(getSkillCap());

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
}
