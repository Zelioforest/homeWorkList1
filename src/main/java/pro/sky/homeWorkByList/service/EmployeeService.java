package pro.sky.homeWorkByList.service;

import org.springframework.stereotype.Service;
import pro.sky.homeWorkByList.model.Employee;
import pro.sky.homeWorkByList.exception.EmployeeAlreadyAddedException;
import pro.sky.homeWorkByList.exception.EmployeeNotFoundException;
import pro.sky.homeWorkByList.exception.EmployeeStorageIsFullException;

@Service
public class EmployeeService {
    public static final int COUNT_ID = 10;
    private final Employee[] employes = new Employee[COUNT_ID];
    private int indOfAdd = 0;

    public Employee addWorker(String firstName, String lastName){
        if (indOfAdd >= COUNT_ID){
            throw new EmployeeStorageIsFullException();
        }
        Employee employe = new Employee(firstName, lastName);
        if (findWorker(employe) >= 0){
            throw new EmployeeAlreadyAddedException();
        }
        employes[indOfAdd] = employe;
        indOfAdd += 1;
        return employe;
    }

    public Employee deletWork(String firstName, String lastName){
        Employee delWorker = new Employee(firstName, lastName);
        int delId = findWorker(delWorker);
        for (int i = 0; i <= indOfAdd; i++) {
            if (employes[i] != null && employes[i].equals(delWorker)) {
                delId = i;
                break;
            }
        }
        if (delId < 0) {
            throw new EmployeeNotFoundException();
        }

        employes[delId] = null;
        if (delId != employes.length - 1){
            System.arraycopy(employes, delId + 1,
                    employes, delId, employes.length - delId);
        }
        indOfAdd += 1;
        return delWorker;
    }


    public Employee findWorker(String firstName, String lastName){
        Employee delWorker = new Employee(firstName, lastName);
        int delId = findWorker(delWorker);
        if (delId < 0){
            throw new EmployeeNotFoundException();
        }
        return delWorker;
    }

    private int findWorker(Employee delWorker){
        int delId = -1;
        int minFindIndex = Math.min(indOfAdd, employes.length - 1);
        for (int i = 0; i <= minFindIndex; i++){
            if (employes[i] != null && employes[i].equals(delWorker)){
                delId = i;
            }
        }
        return delId;
    }
}
