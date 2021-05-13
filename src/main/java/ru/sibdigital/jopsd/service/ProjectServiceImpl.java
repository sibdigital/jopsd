package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.Project;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl extends SuperServiceImpl implements ProjectService{
    @Override
    public List<Project> getProjectList() {
        return projectRepo.findAll();
    }
}
