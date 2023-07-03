package Modelo;

public class Usuario {
    int IdUser;
    String UserName;
    int AgeUser;
    int IdCampus;
    String passwords;

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getAgeUser() {
        return AgeUser;
    }

    public void setAgeUser(int ageUser) {
        AgeUser = ageUser;
    }

    public int getIdCampus() {
        return IdCampus;
    }

    public void setIdCampus(int idCampus) {
        IdCampus = idCampus;
    }
}
