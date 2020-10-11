package com.scholat.doma.entity;

public class TeamMember {

    private String teamId;
    private String teamName;
    private String userName;
    private String userId;

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TeamMember(String teamId, String teamName, String userName, String userId) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.userName = userName;
        this.userId = userId;
    }

    public TeamMember() {
    }
}