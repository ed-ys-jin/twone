package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;

import java.util.List;

public class ProjectDAOImpl implements ProjectDAO{

    @Override
    public int createProject(ProjectDTO projectDTO) {
        int num = createProject(projectDTO);
        return num;
    }

    @Override
    public List<ProjectDTO> getList(int memSeq) {
        return getList(memSeq);
    }
}
