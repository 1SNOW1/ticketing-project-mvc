package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        //attributes --> You see what you need to add by looking through the html code, for instance -> if it needs an empty object, you pass it
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findAll());
        model.addAttribute("projects", projectService.findAll());

        return "/project/create";
    }
}
