package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

public class CGUserVO implements Serializable {

    private static final long serialVersionUID = -3723252459034727930L;

    private long id;

    private String mailAddress;

    private String localArea;

    private int cmIslandDataId;

    private String nickName;

    private String imageName;

    private Date lastLogin;

    private String movePassword;

    private String machineNum;

    private int screenWidth;

    private int screenHeight;

    private long exp;

    private int levels;

    private int goldenCount;

    private int praisesCount;

    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getGoldenCount() {
        return goldenCount;
    }

    public void setGoldenCount(int goldenCount) {
        this.goldenCount = goldenCount;
    }

    public int getPraisesCount() {
        return praisesCount;
    }

    public void setPraisesCount(int praisesCount) {
        this.praisesCount = praisesCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getLocalArea() {
        return localArea;
    }

    public void setLocalArea(String localArea) {
        this.localArea = localArea;
    }

    public int getCmIslandDataId() {
        return cmIslandDataId;
    }

    public void setCmIslandDataId(int cmIslandDataId) {
        this.cmIslandDataId = cmIslandDataId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMovePassword() {
        return movePassword;
    }

    public void setMovePassword(String movePassword) {
        this.movePassword = movePassword;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cmIslandDataId;
        result = prime * result + (int) (exp ^ (exp >>> 32));
        result = prime * result + goldenCount;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
        result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result + levels;
        result = prime * result + ((localArea == null) ? 0 : localArea.hashCode());
        result = prime * result + ((machineNum == null) ? 0 : machineNum.hashCode());
        result = prime * result + ((mailAddress == null) ? 0 : mailAddress.hashCode());
        result = prime * result + ((movePassword == null) ? 0 : movePassword.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + praisesCount;
        result = prime * result + screenHeight;
        result = prime * result + screenWidth;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CGUserVO other = (CGUserVO) obj;
        if (cmIslandDataId != other.cmIslandDataId) {
            return false;
        }
        if (exp != other.exp) {
            return false;
        }
        if (goldenCount != other.goldenCount) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (imageName == null) {
            if (other.imageName != null) {
                return false;
            }
        } else if (!imageName.equals(other.imageName)) {
            return false;
        }
        if (lastLogin == null) {
            if (other.lastLogin != null) {
                return false;
            }
        } else if (!lastLogin.equals(other.lastLogin)) {
            return false;
        }
        if (levels != other.levels) {
            return false;
        }
        if (localArea == null) {
            if (other.localArea != null) {
                return false;
            }
        } else if (!localArea.equals(other.localArea)) {
            return false;
        }
        if (machineNum == null) {
            if (other.machineNum != null) {
                return false;
            }
        } else if (!machineNum.equals(other.machineNum)) {
            return false;
        }
        if (mailAddress == null) {
            if (other.mailAddress != null) {
                return false;
            }
        } else if (!mailAddress.equals(other.mailAddress)) {
            return false;
        }
        if (movePassword == null) {
            if (other.movePassword != null) {
                return false;
            }
        } else if (!movePassword.equals(other.movePassword)) {
            return false;
        }
        if (nickName == null) {
            if (other.nickName != null) {
                return false;
            }
        } else if (!nickName.equals(other.nickName)) {
            return false;
        }
        if (praisesCount != other.praisesCount) {
            return false;
        }
        if (screenHeight != other.screenHeight) {
            return false;
        }
        if (screenWidth != other.screenWidth) {
            return false;
        }
        return true;
    }

}
