package com.scholat.doma.dao;

import com.scholat.doma.entity.TeamMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberDao {

    public Integer Add(TeamMember teamMember);
    //用户退出所有团队
    public Integer DeleteByUserId(String userId);
    //解散团队成员关系
    public Integer DeleteByTeamId(String teamId);
    //用户退出团队
    public Integer quitUniqueTeam(String userId,String teamId);

    public Integer Update(TeamMember teamMember);

    public List<TeamMember> SelectAll();

    //列出用户所在全部团队
    public List<TeamMember> SelectByUserId(String userId);
    //列出团队所有用户
    public List<TeamMember> SelectByTeamId(String TeamId);

}
