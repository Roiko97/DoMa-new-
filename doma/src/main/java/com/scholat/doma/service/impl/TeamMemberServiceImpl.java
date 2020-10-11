package com.scholat.doma.service.impl;

import com.scholat.doma.dao.TeamMemberDao;
import com.scholat.doma.entity.Team;
import com.scholat.doma.entity.TeamMember;
import com.scholat.doma.entity.User;
import com.scholat.doma.service.TeamMemberService;
import com.scholat.doma.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Override
    public Integer Add(TeamMember teamMember) {
        return teamMemberDao.Add(teamMember);
    }

    @Override
    public Integer DeleteByUserId(String userId) {
        return teamMemberDao.DeleteByUserId(userId);
    }

    @Override
    public Integer DeleteByTeamId(String teamId) {
        return teamMemberDao.DeleteByTeamId(teamId);
    }

    @Override
    public Integer quitUniqueTeam(String userId, String teamId) {
        return teamMemberDao.quitUniqueTeam(userId,teamId);
    }

    @Override
    public Integer Update(TeamMember teamMember) {
        return teamMemberDao.Update(teamMember);
    }

    @Override
    public List<TeamMember> SelectAll() {
        return teamMemberDao.SelectAll();
    }

    @Override
    public List<TeamMember> SelectByUserId(String userId) {
        return teamMemberDao.SelectByUserId(userId);
    }

    @Override
    public List<TeamMember> SelectByTeamId(String teamId) {
        return teamMemberDao.SelectByTeamId(teamId);
    }
}
