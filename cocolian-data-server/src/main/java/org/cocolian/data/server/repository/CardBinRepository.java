package org.cocolian.data.server.repository;

import org.cocolian.data.CardBin;
import org.cocolian.mysql.JdbcProtobufTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


/**
 * @author qiaiduo@163.com
 * @version 1.0.0
 * @date 2018-06-10
 */
@Service
public class CardBinRepository  {

    private static final Logger logger = LoggerFactory.getLogger(CardBinRepository.class);
    private JdbcProtobufTemplate jdbcProtobufTemplate;

    @Autowired
    public CardBinRepository(JdbcTemplate jdbcTemplate) {
        jdbcProtobufTemplate = new JdbcProtobufTemplate<CardBin>(jdbcTemplate,CardBin.class);
    }
    @Transactional(readOnly = true)
    public List<CardBin>  getAllCardBin(){
        try{
            return jdbcProtobufTemplate.query("SELECT * FROM cardbin_t");
        }catch (DataAccessException e){
            SQLException exception = (SQLException)e.getCause();
            logger.error("Get card bin data failed, ErrorCode: "+ exception.getErrorCode()+", Message: "+exception.getMessage());
            return null;
        }
    }



}
