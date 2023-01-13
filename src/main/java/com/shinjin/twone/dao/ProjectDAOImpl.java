package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;

public class ProjectDAOImpl implements ProjectDAO{

    @Override
    public int createProject(ProjectDTO projectDTO) {
        int num = createProject(projectDTO);
        return num;
    }
}
