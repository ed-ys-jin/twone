package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import org.springframework.validation.Errors;

import java.util.List;

public interface ProjectService {

    public int createProject(ProjectDTO projectDTO);
    public List<ProjectDTO> getList(int memSeq);

}
