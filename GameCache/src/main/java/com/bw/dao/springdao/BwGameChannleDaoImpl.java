package com.bw.dao.springdao;

import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.dao.BwGameChannleDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BwGameChannleDaoImpl extends BaseSpringDao implements BwGameChannleDAO {

    private static final String sqlGetGameChannleById = "select  id, channle_name, service_url, service_interface, max_user_count, address, status, area_id from bw_game_channle where id = ? and status = 1";
    private static final String SQL_SELECT_ALL = "select id, channle_name, service_url, service_interface, max_user_count, address, status area_id from bw_game_channle where status = 1";
    private static final String SQL_UPDATE = "UPDATE bw_game_channle SET channle_name=?, service_url=?, service_interface=?, max_user_count=?, address=?, `status`=?, area_id=? WHERE id= ? and status = 1";

    private static final RowMapper<BwGameChannleVO> rowMapperGameChannle = new RowMapper<BwGameChannleVO>() {
        @Override
        public BwGameChannleVO mapRow(ResultSet rs, int i) throws SQLException {
            BwGameChannleVO vo = new BwGameChannleVO();
            vo.setId(rs.getInt("id"));
            vo.setChannlename(rs.getString("channle_name"));
            vo.setServiceurl(rs.getString("service_url"));
            vo.setServiceinterface(rs.getString("service_interface"));
            vo.setMaxusercount(rs.getLong("max_user_count"));
            vo.setAddress(rs.getString("address"));
            vo.setStatus(rs.getInt(7));
            vo.setAreaId(rs.getInt("area_id"));
            return vo;
        }
    };

    @Override
    public BwGameChannleVO getGameChannleById(int id) {
        return getJdbcTemplate().queryForObject(sqlGetGameChannleById, new Object[]{id}, rowMapperGameChannle);
    }

    @Override
    public List<BwGameChannleVO> selectAll() {
        return getJdbcTemplate().query(SQL_SELECT_ALL, rowMapperGameChannle);
    }

    @Override
    public void update(BwGameChannleVO vo) {
        getJdbcTemplate().update(SQL_UPDATE, vo.getChannlename(), vo.getServiceurl(), vo.getServiceinterface(),
                vo.getMaxusercount(), vo.getAddress(), vo.getStatus(), vo.getAreaId(), vo.getId());
    }
}
