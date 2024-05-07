package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


/*
This CommandLineRunner interface makes sure that whenever you run the application
the "run" method is the first to be executed.
 */

@Service
public class DataGenerator implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DataGenerator(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        //create some roles and put in the DB(map, in my case)

        /*normally we would have used "entity" and not "DTO" to save roles in DB,
         but at the moment we are not using data layer in our application
         */
        RoleDTO adminRole = new RoleDTO(1L, "Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L, "Employee");

        /*
        the code below is tight coupling, and spring does not like it

        RoleServiceImpl roleService = new RoleServiceImpl();
        roleService.save(adminRole);
         */

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John", "Kesy", "john@cydeo.com", "Abc1", true, "3344243645241", managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Mike", "Smith", "Mike@cydeo.com", "Abc2", true, "939525208441", adminRole, Gender.MALE);
        UserDTO user3 = new UserDTO("Delisa", "Norre", "delisa@cydeo.com", "Abc3", true, "4134335251414", managerRole, Gender.FEMALE);
        UserDTO user4 = new UserDTO("Craig", "Jark", "craig@cydeo.com", "Abc4", true, "41435246463462", employeeRole, Gender.MALE);
        UserDTO user5 = new UserDTO("Shaun", "Hayns", "shaun@cydeo.com", "Abc5", true, "4657476846321", managerRole, Gender.MALE);
        UserDTO user6 = new UserDTO("Elizabeth", "Loren", "elizabeth@cydeo.com", "Abc6", true, "536365478746342", employeeRole, Gender.FEMALE);
        UserDTO user7 = new UserDTO("Maria", "Ada", "maria@cydeo.com", "Abc7", true, "23434353647575", employeeRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("Bill", "Matt", "bill@cydeo.com", "Abc8", true, "94352133235335", employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);

    }
}
