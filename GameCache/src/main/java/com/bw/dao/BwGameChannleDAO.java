package com.bw.dao;

import com.bw.baseJar.vo.BwGameChannleVO;

import java.util.List;

/**
 * bw_game_channle
 *
 * @author zhYou
 */
public interface BwGameChannleDAO {

    BwGameChannleVO getGameChannleById(int id);

    public List<BwGameChannleVO> selectAll();

    public void update(BwGameChannleVO vo);
}
