package pro.sky.homeWorkByList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeWorkByList.model.Employee;
import pro.sky.homeWorkByList.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class listController {

    private final EmployeeService employeeService;

    public listController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String welcome(){
        return "Welcome";
    }

    @GetMapping(path = "/add")
    public Employee addWorker(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName){

       return employeeService.addWorker(firstName, lastName);
    }

    @GetMapping(path = "remove")
    public Employee delWorker(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName){

        return employeeService.deletWork(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findWorker(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName){

        return employeeService.findWorker(firstName, lastName);
    }
}
