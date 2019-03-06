package cn.itheima.dao.impl;

import cn.itheima.dao.ZhangWuDao;
import cn.itheima.domain.User;
import cn.itheima.domain.ZhangWu;
import cn.itheima.tools.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ZhangWuDaoImpl implements ZhangWuDao {
//    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 查询所有
     * @return
     */
    public List<ZhangWu> selectAll(int startNum,int pageNum) {
        String sql="select * from gjp_zhangwu limit ?,? ";
        List<ZhangWu> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ZhangWu.class),startNum,pageNum);
        return list;
    }
    /**
     * 条件查询
     * @return
     */
    public List<ZhangWu> selectAll(String flname,int startNum,int pageNum) {
        String sql="select * from gjp_zhangwu where flname like '%"+flname+"%' limit ?,?";
        List<ZhangWu> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ZhangWu.class),startNum,pageNum);
        return list;
    }

    /**
     * 添加
     * @param zw
     * @return
     */
    public Integer addZhangWu(ZhangWu zw){
        String sql="insert into gjp_zhangwu(flname,money,zhanghu,createtime,description) values (?,?,?,?,?)";
        Object[] params={zw.getFlname(), zw.getMoney(), zw.getZhangHu(), zw.getCreatetime(), zw.getDescription()};
        int update = jdbcTemplate.update(sql, params);
        return update;
    }

    public Integer editZhangWu(ZhangWu zw) {
        String sql="update gjp_zhangwu set flname=?, money=?,zhanghu=?,createtime=?,description=? where zwid=?";
        Object[] params={zw.getFlname(), zw.getMoney(), zw.getZhangHu(), zw.getCreatetime(), zw.getDescription(),zw.getZwid()};
        int update = jdbcTemplate.update(sql, params);
        return update;
    }

    public int deleteZhangWu(int num) {
        String sql="delete from gjp_zhangwu where zwid=?";
        int update = jdbcTemplate.update(sql, num);
        return update;
    }

    public List<ZhangWu> selectOne(int num) {
        String sql="select* from gjp_zhangwu where zwid=?";
        List<ZhangWu> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ZhangWu.class), num);
        return list;
    }

    public List<User> selectUser(String username, String password) {
        String sql="select* from user_info where username=? and password=?";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        return list;
    }
    //查询总记录数
    @Override
    public int queryTotalPage() {
        String sql="select count(*) from gjp_zhangwu";
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }

    }
}
