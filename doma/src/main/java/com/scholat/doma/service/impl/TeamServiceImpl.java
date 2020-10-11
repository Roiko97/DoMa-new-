package com.scholat.doma.service.impl;

import com.scholat.doma.dao.TeamDao;


import com.scholat.doma.entity.Team;
import com.scholat.doma.entity.User;
import com.scholat.doma.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;

//    @Override
//    public Team SelectById(String teamId) {
//        return teamDao.SelectById(teamId);
//    }

    @Override
    public Team SelectByName(String teamName) {


        return teamDao.SelectByName(teamName.replace(" ",""));
    }

    @Override
    public List<User> SelectAllUserFromTeam(String teamId) {
        return teamDao.SelectAllUserFromTeam(teamId);
    }

    @Override
    public Team SelectById(String teamId) {
        return teamDao.SelectById(teamId);
    }

    @Override
    public Integer insertTeam(Team team) {
        return teamDao.insertTeam(team);
    }

    @Override
    public Integer updateTeam(Team team) {
        return teamDao.updateTeam(team);
    }

    @Override
    public Integer DeleteTeam(String teamId) {
        return teamDao.DeleteTeam(teamId);
    }


}
