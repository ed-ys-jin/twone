package com.shinjin.twone.service;

import com.shinjin.twone.dto.ProjectDTO;
import org.springframework.validation.Errors;

public interface ProjectService {

    public int createProject(ProjectDTO projectDTO);
}
