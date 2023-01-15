package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    public int createProject(ProjectDTO projectDTO);
    public int insertMasterTeam(ProjectDTO projectDTO);
    public List<ProjectDTO> getList(int memSeq);
    public ProjectDTO selectOne(int projectSeq);
    public int deleteOne(int projectSeq);
    public int checkSetting(Map<String, Integer> map);

}
