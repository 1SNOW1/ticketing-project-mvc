package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.CrudService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public ProjectDTO save(ProjectDTO project) {

        if (project.getProjectStatus()==null)
        project.setProjectStatus(Status.OPEN);

        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        if (object.getProjectStatus()==null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }
        super.update(object.getProjectName(),object);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {

        project.setProjectStatus(Status.COMPLETE);
    }

    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll().
                        stream().
                        filter(project -> project.getAssignedManager().equals(manager))
                        .map(project -> {

                            /*
                            So why are we using ".map" here?
                            Here we did a simple stream in which we are gathering all the projects
                            that are assigned to a specific manager.
                            The unsolved issue here is, the two fields we are looking for in the DB,
                            namely the "completeTaskCount" and "unfinishedTaskCount" does not exist in the DB,
                            so the "project" has nothing to retrieve.
                            Hence, the stream is not finished and needs additional steps to be completed
                             */
                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);
                            int completeTaskCounts = (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();
                            int unfinishedTaskCounts = (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);


                            return project;
                        })
                                .collect(Collectors.toList());


        return projectList;
    }

}


/*
   public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll().
                        stream().
                        filter(project -> project.getAssignedManager().equals(manager))
                        .map(project -> {

                            /*
                            So why are we using ".map" here?
                            Here we did a simple stream in which we are gathering all the projects
                            that are assigned to a specific manager.
                            The unsolved issue here is, the two fields we are looking for in the DB,
                            namely the "completeTaskCount" and "unfinishedTaskCount" does not exist in the DB,
                            so the "project" has nothing to retrieve.
                            Hence, the stream is not finished and needs additional steps to be completed
                             */

/*List<TaskDTO> taskList = taskService.findTasksByManager(manager);
int completeTaskCounts = (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();
int unfinishedTaskCounts = (int) taskList.stream().filter(t -> t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);


                            return project;
                        })
                                .collect(Collectors.toList());


        return projectList;
    }
 */