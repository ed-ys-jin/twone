package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDAO {

    public int createProject(ProjectDTO projectDTO);
    public List<ProjectDTO> getList(int memSeq);
}