package ru.zakirov.operator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zakirov.operator.model.OperatorInfo;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class OperatorDAO extends JdbcDaoSupport {

    @Autowired
    public OperatorDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }
    public int countRow(){
        return this.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM def", Integer.class);
    }

    public List<OperatorInfo> allOperators(){
        return this.getJdbcTemplate().query("SELECT * FROM def", new OperatorInfoMapper());
    }
}
