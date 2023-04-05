package ru.zakirov.operator.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.zakirov.operator.dao.OperatorDAO;
import ru.zakirov.operator.model.OperatorInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OperatorService {

    private final OperatorDAO operatorDAO;
    private List<OperatorInfo> operators;

    public OperatorService(OperatorDAO operatorDAO) {
        this.operatorDAO = operatorDAO;
    }

    @PostConstruct
    public void init(){
        long before = System.currentTimeMillis();
        operators = operatorDAO.allOperators();
        System.out.println("Time init method: " + (System.currentTimeMillis() - before));
    }

    public String getOperator(String numberPhone){
        long before = System.currentTimeMillis();
        for (int i = 0; i < operators.size(); i++) {
            if (numberPhone.substring(1,4).equals(operators.get(i).getDefCode())){
                String[] parts = operators.get(i).getBetween().split("-");
                int numberKod = Integer.parseInt(numberPhone.substring(4));
                if (Arrays.stream(parts).anyMatch(t->
                        (Integer.parseInt(parts[0]) <= numberKod && numberKod <= Integer.parseInt(parts[1])))){
                    System.out.println("Time getOperator method: " + (System.currentTimeMillis() - before));
                    return operators.get(i).getOperator();
                }
            }
        }

        return null;
    }
}
