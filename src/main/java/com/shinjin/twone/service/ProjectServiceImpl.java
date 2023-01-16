package com.shinjin.twone.service;

import com.shinjin.twone.dao.MemDAO;
import com.shinjin.twone.dao.ProjectDAO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public int createProject(ProjectDTO projectDTO) {
        projectDAO.createProject(projectDTO);
        return projectDTO.getProjectSeq();
    }

    @Override
    public int insertMasterTeam(ProjectDTO projectDTO) {
        return projectDAO.insertMasterTeam(projectDTO);
    }

    @Override
    public List<ProjectDTO> getList(int memSeq) {
        return projectDAO.getList(memSeq);
    }

    @Override
    public ProjectDTO selectOne(int projectSeq) {
        return projectDAO.selectOne(projectSeq);
    }

    @Override
    public int deleteOne(int projectSeq) {
        return projectDAO.deleteOne(projectSeq);
    }

    @Override
    public int checkKey(ProjectDTO pdto) {
        return projectDAO.checkKey(pdto);
    }

    @Override
    public String checkLeaderPw(int memSeq) {
        return projectDAO.checkLeaderPw(memSeq);
    }

    @Override
    public int checkSetting(Map<String, Integer> map) {
        return projectDAO.checkSetting(map);
    }
}
