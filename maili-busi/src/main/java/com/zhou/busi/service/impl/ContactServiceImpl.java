package com.zhou.busi.service.impl;

import com.zhou.busi.entity.Contact;
import com.zhou.busi.mapper.ContactMapper;
import com.zhou.busi.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预约 服务实现类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2019-01-04
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

}
