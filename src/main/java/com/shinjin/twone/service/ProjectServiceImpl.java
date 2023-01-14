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

        int num = projectDAO.createProject(projectDTO);
        return num;
    }

    @Override
    public List<ProjectDTO> getList(int memSeq) {
        return projectDAO.getList(memSeq);
    }
}
