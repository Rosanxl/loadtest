package cn.itheima.dao;

import cn.itheima.domain.User;
import cn.itheima.domain.ZhangWu;

import java.util.List;

public interface ZhangWuDao {
    List<ZhangWu>  selectAll(int startNum, int pageNum);
    List<ZhangWu>  selectAll(String flname, int startNum, int pageNum);
    Integer addZhangWu(ZhangWu zw);
    Integer editZhangWu(ZhangWu zw);
    int deleteZhangWu(int num);
    List<ZhangWu> selectOne(int num);
    List<User> selectUser(String username, String password);
    int queryTotalPage();
}
