package com.scholat.doma.dao;

import com.scholat.doma.entity.TeamMember;
import com.scholat.doma.entity.Team;
import com.scholat.doma.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDao {

    public Team SelectById(String teamId);

    public Team SelectByName(String teamName);

    public List<User> SelectAllUserFromTeam(String teamId);

    public Integer insertTeam(Team team);

    public Integer updateTeam(Team team);

    public Integer DeleteTeam(String teamId);


}
