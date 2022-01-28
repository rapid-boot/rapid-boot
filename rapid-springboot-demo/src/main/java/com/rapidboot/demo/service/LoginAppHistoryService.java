package com.rapidboot.demo.service;

import com.rapidboot.common.persistence.CrudService;
import com.rapidboot.demo.domain.LoginAppHistory;
import com.rapidboot.demo.dao.LoginAppHistoryDao;
import com.rapidboot.demo.dto.LoginAppHistoryQueryDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;

/**
* @description 服务接口
* @author 广峰 <gf@gfzj.us>
* @date 2022-01-29 02:41
**/
@Service
@Transactional(readOnly = true)
public class LoginAppHistoryService extends CrudService<LoginAppHistoryDao, LoginAppHistory> {

        @Override
        public LoginAppHistory get(Long id) {
        return super.get(id);
        }

        public List<LoginAppHistory> findList(LoginAppHistoryQueryDTO queryDTO) {
        return super.findList(queryDTO);
        }

        public PageInfo<LoginAppHistory> findPage(LoginAppHistoryQueryDTO queryDTO) {
        return super.findPage(queryDTO);
        }

        @Transactional(readOnly = false)
        public int batchDelete(LoginAppHistoryQueryDTO queryDTO) {
        int count = this.dao.batchDelete(queryDTO);
        return count;
        }


        @Transactional(readOnly = false)
        @Override
        public void save(LoginAppHistory LoginAppHistory) {
        super.save(LoginAppHistory);
        }


        @Transactional(readOnly = false)
        @Override
        public int delete(LoginAppHistory LoginAppHistory) {
        return super.delete(LoginAppHistory);
        }

}