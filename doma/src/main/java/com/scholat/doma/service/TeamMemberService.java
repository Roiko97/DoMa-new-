package com.scholat.doma.service;

import com.scholat.doma.entity.TeamMember;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamMemberService {
    public Integer Add(TeamMember teamMember);
    public Integer DeleteByUserId(String userId);
    public Integer DeleteByTeamId(String teamId);
    public Integer quitUniqueTeam(String userId,String teamId);
    public Integer Update(TeamMember teamMember);
    public List<TeamMember> SelectAll();
    public List<TeamMember> SelectByUserId(String userId);
    public List<TeamMember> SelectByTeamId(String TeamId);
}
