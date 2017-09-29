package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.Userinfo;
import java.util.List;

public interface UserinfoMapper {

    int insert(Userinfo record);

    Userinfo selectByPrimaryKey(Long id);

    List<Userinfo> selectAll();

    int updateByPrimaryKey(Userinfo record);
}