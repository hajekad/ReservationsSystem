package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach;

import jakarta.persistence.Column;

public class CoachDto {
    private Long id;
    private int costRate;
    private String sport;
    private String password;
    private String email;
    private String firstName;
    private String secondName;

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
