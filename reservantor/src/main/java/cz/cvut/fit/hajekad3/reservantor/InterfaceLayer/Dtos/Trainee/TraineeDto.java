package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee;

import java.util.Collection;

public class TraineeDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private int skillCap;
    private int credit;
    private Collection<Long> trainings;
    public TraineeDto() {}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Long> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Long> trainings) {
        this.trainings = trainings;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
