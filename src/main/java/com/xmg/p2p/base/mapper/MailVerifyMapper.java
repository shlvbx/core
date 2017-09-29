package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.MailVerify;
import java.util.List;

public interface MailVerifyMapper {
  

    int insert(MailVerify record);

    MailVerify selectByUuid(Long id);

    List<MailVerify> selectAll();

   
}