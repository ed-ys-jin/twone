package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDAO {

    public int createProject(ProjectDTO projectDTO);
    public int insertMasterTeam(ProjectDTO projectDTO);
    public List<ProjectDTO> getList(int memSeq);
    public ProjectDTO selectOne(int projectSeq);
    public int deleteOne(int projectSeq);
    public int checkSetting(Map<String, Integer> map);

}
