package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwLogVO;
import com.bw.dao.BwLogDAO;
import com.bw.log.Action;

public class BwLogDaoImpl extends BaseSpringDao implements BwLogDAO {

    private static final Logger logger = Logger.getLogger(BwLogDaoImpl.class);

    /**
     * 插入日志
     *
     * @param log
     */
    public void insert(BwLogVO log) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(log.getAction().name().toLowerCase());
        sql.append(" values(");
        sql.append(log.getRoleId()).append(",");
        sql.append(log.getTime()).append(",");
        sql.append(log.getAction().getId()).append(",");
        sql.append(log.getArgs());
        sql.append(")");
        logger.info("insert sql: " + sql.toString());
        getJdbcTemplate().execute(sql.toString());
    }

    /**
     * 批量插入用户日志
     *
     * @param logs
     */
    public void batchInsert(List<BwLogVO> logs) {
        String[] sqlArr = new String[logs.size()];
        for (int i = 0; i < sqlArr.length; i++) {
            sqlArr[i] = generateInsertSql(logs.get(i));
        }
        getJdbcTemplate().batchUpdate(sqlArr);
    }

    /**
     * 根据log对象生成sql insert语句
     *
     * @param log 日志对象
     * @return 插入该日志的sql语句
     */
    private String generateInsertSql(BwLogVO log) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(log.getAction().name().toLowerCase());
        sql.append(" values(");
        sql.append(log.getRoleId()).append(",");
        sql.append(log.getTime()).append(",");
        sql.append(log.getAction().getId()).append(",");
        sql.append(log.getArgs());
        sql.append(")");
        return sql.toString();
    }

    /**
     * @param roleId
     * @param action
     * @param startTime
     * @param endTime
     * @return
     */
    public List<BwLogVO> selectByRoleIdAndActionAndTime(long roleId, Action action, long startTime, long endTime) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(action.name().toLowerCase()).append(" ");
        sql.append("where role_id = ? and action = ? and time >= ? and time <= ? order by time desc");
        return getJdbcTemplate().query(sql.toString(), ROW_MAPPER_LOG, roleId, action.getId(), startTime, endTime);
    }

    private static final RowMapper<BwLogVO> ROW_MAPPER_LOG = new RowMapper<BwLogVO>() {
        @Override
        public BwLogVO mapRow(ResultSet rs, int idx) throws SQLException {
            long roleId = rs.getLong("role_id");
            Action action = Action.valueOf(rs.getInt("action"));
            long time = rs.getLong("time");
            StringBuilder args = new StringBuilder();
            for (int i = 3; i < idx; i++) {
                args.append(rs.getString(i));
                if (i > 3) {
                    args.append(",");
                }
            }
            return new BwLogVO(roleId, time, action, args.toString());
        }
    };
}
