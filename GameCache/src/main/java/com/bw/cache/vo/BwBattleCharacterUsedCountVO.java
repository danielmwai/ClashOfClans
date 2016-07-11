package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @author denny zhao
 *
 */
public class BwBattleCharacterUsedCountVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1568050561602241043L;
    public int characterIdOrSpellId;
    public int count;
    //0兵ID，1魔法ID
    public int usedType;

    public int getCharacterIdOrSpellId() {
        return characterIdOrSpellId;
    }

    public void setCharacterIdOrSpellId(int characterIdOrSpellId) {
        this.characterIdOrSpellId = characterIdOrSpellId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUsedType() {
        return usedType;
    }

    public void setUsedType(int usedType) {
        this.usedType = usedType;
    }

}
