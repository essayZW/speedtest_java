package cn.imessay.speedtest.manager.cidr;

import cn.imessay.speedtest.dao.cidr.CidrDO;

import java.util.List;

public interface CidrManager {

    CidrDO getSingle(int id);

    /**
     * 查询CIDR信息，支持分页
     * @param page 页号，若为-1，代表选取所有的数据
     * @param pageSize 一个页的大小
     * @param order 是否根据优先级排序
     * @return CIDR信息
     */
    List<CidrDO> get(int page, int pageSize, boolean order);

    List<CidrDO> getMatched(String ip);
    CidrDO getFirstMatched(String ip);

}
