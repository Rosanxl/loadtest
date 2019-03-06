package cn.itheima.service;

import cn.itheima.domain.ZhangWu;

import java.util.List;


public interface ZhangWuService {
    boolean selectUser(String username, String password);
    List<ZhangWu> selectAll(String flname, int pageSize, int pageNum);
    void addZhangWu(ZhangWu zw);
    void editZhangWu(ZhangWu zw);
    void deleteZhangWu(Integer zwid);
    int queryTotalPage();
}
