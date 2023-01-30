package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;

import java.util.List;
import java.util.Map;

public class ProjectDAOImpl implements ProjectDAO{

    @Override
    public int createProject(ProjectDTO projectDTO) {
        createProject(projectDTO);
        // selectkey 를 활용하여 인서트 한 project_seq 바로 가져오기
        return projectDTO.getProjectSeq();
    }

    @Override
    public int insertMasterTeam(ProjectDTO projectDTO) {
        return insertMasterTeam(projectDTO);
    }

    @Override
    public List<ProjectDTO> getList(int memSeq) {
        return getList(memSeq);
    }

    @Override
    public ProjectDTO selectOne(int projectSeq) {
        return selectOne(projectSeq);
    }

    @Override
    public int checkKey(ProjectDTO pdto) {
        return checkKey(pdto);
    }

    @Override
    public String checkLeaderPw(int memSeq) {
        return checkLeaderPw(memSeq);
    }

    @Override
    public int deleteOne(int projectSeq) {
        return deleteOne(projectSeq);
    }

    @Override
    public int checkSetting(Map<String, Integer> map) {
        return checkSetting(map);
    }
}
