package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach;

import java.util.Collection;

public class CoachDto {
    private Long id;
    private int costRate;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private Collection<Long> trainings;

    public CoachDto() {}

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

    public Collection<Long> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Long> trainings) {
        this.trainings = trainings;
    }
}
