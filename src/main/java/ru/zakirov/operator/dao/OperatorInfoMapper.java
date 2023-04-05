package ru.zakirov.operator.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.zakirov.operator.model.OperatorInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorInfoMapper implements RowMapper<OperatorInfo> {
    @Override
    public OperatorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OperatorInfo(rs.getString("abcdef"),
                        rs.getString("От") + "-" + rs.getString("До"),
                        rs.getString("Оператор"));
    }
}
