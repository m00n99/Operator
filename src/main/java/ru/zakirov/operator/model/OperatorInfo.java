package ru.zakirov.operator.model;

public class OperatorInfo {
    private String defCode;
    private String between;
    private String operator;

    public OperatorInfo(String defCode, String between, String operator) {
        this.defCode = defCode;
        this.between = between;
        this.operator = operator;
    }

    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public String getBetween() {
        return between;
    }

    public void setBetween(String between) {
        this.between = between;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "OperatorInfo{" +
                "defCode='" + defCode + '\'' +
                ", between='" + between + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
