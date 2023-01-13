package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectDAO {

    public int createProject(ProjectDTO projectDTO);
}
