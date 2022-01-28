package com.rapidboot.demo.dao;

import com.rapidboot.demo.domain.LoginAppHistory;
import org.springframework.stereotype.Repository;
import com.rapidboot.common.persistence.CrudDao;


/**
* DAO接口
* @author 广峰 <gf@gfzj.us>
* @date 2022-01-29 02:17
**/
@Repository
public interface LoginAppHistoryDao extends CrudDao<LoginAppHistory> {

}