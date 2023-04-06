package ru.zakirov.operator.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.zakirov.operator.dao.OperatorDAO;
import ru.zakirov.operator.model.OperatorInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OperatorService {

    private final OperatorDAO operatorDAO;
    private List<OperatorInfo> operators;
    private List<Integer> defCodes;

    public OperatorService(OperatorDAO operatorDAO) {
        this.operatorDAO = operatorDAO;
    }

    @PostConstruct
    public void init(){
        long before = System.currentTimeMillis();

        operators = operatorDAO.allOperators();

        defCodes = new ArrayList<>();

        for (int i = 0; i < operators.size(); i++) {
            defCodes.add(Integer.parseInt(operators.get(i).getDefCode()));
        }

        System.out.println("Time init method: " + (System.currentTimeMillis() - before));
    }

    public String getOperator(String numberPhone){

        int defCodeNumberPhone = Integer.parseInt(numberPhone.substring(1, 4));

        int findIndexDefCode = Collections.binarySearch(defCodes, defCodeNumberPhone);

        String operator;

        int betweenCodeNumberPhone = Integer.parseInt(numberPhone.substring(4));

        if (betweenCodeNumberPhone >= Integer.parseInt(operators.get(findIndexDefCode).getBetween().substring(0,7))) {

            for (int i = findIndexDefCode; operators.get(i).getDefCode().equals(operators.get(findIndexDefCode).getDefCode()); i++) {
                operator = findOperatorBetweenRanges(numberPhone, i);
                if (operator != null)
                    return findOperatorBetweenRanges(numberPhone, i);

                if (i >= operators.size())
                    break;
            }
        } else {
            for (int i = findIndexDefCode - 1; operators.get(i).getDefCode().equals(operators.get(findIndexDefCode).getDefCode()); i--) {
                operator = findOperatorBetweenRanges(numberPhone, i);
                if (operator != null)
                    return findOperatorBetweenRanges(numberPhone, i);
            }
        }

        return null;
    }

    public String findOperatorBetweenRanges(String numberPhone, int i){
        String[] parts = operators.get(i).getBetween().split("-");
        int numberKod = Integer.parseInt(numberPhone.substring(4));

        if (Arrays.stream(parts).anyMatch(t->
                (Integer.parseInt(parts[0]) <= numberKod && numberKod <= Integer.parseInt(parts[1])))){

            return operators.get(i).getOperator();
        }

        return null;
    }
}
