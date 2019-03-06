package cn.itheima.service.impl;

import cn.itheima.dao.ZhangWuDao;
import cn.itheima.dao.impl.ZhangWuDaoImpl;
import cn.itheima.domain.User;
import cn.itheima.domain.ZhangWu;
import cn.itheima.service.ZhangWuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("zhangWuService")
public class ZhangWuServiceImpl implements ZhangWuService {
//    private ZhangWuDao zhangWuDao =new ZhangWuDaoImpl();
    @Autowired
    private ZhangWuDao zhangWuDao;

    //查询总记录数
    @Override
    public int queryTotalPage() {
        return zhangWuDao.queryTotalPage();
    }

//private ZhangWuDao zhangWuDao;
//
//    public void setZhangWuDao(ZhangWuDao zhangWuDao) {
//        this.zhangWuDao = zhangWuDao;
//    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    public boolean selectUser(String username, String password) {
        System.out.println("参数"+zhangWuDao);
        List<User> list=zhangWuDao.selectUser(username,password);
        if (list.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 有参查询
     * @param flname
     * @return
     */
    public List<ZhangWu> selectAll(String flname,int pageSize,int pageNum) {
        if (""!=flname&&null!=flname){
            return zhangWuDao.selectAll(flname,pageSize,pageNum);
        }else{
            return zhangWuDao.selectAll(pageSize,pageNum);
        }
    }

    /**
     * 添加
     * @param zw
     */
    public void addZhangWu(ZhangWu zw) {
        Integer integer = zhangWuDao.addZhangWu(zw);
        if (integer>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    /**
     * 编辑
     * @param zw
     */
    public void editZhangWu(ZhangWu zw) {
       Integer i= zhangWuDao.editZhangWu(zw);
       if (i>0){
           System.out.println("编辑账务成功");
       }else {
           System.out.println("编辑账务失败");
       }
    }


    public void deleteZhangWu( Integer zwid) {
        zhangWuDao.deleteZhangWu(zwid);
    }
}
