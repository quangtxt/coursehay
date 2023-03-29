
package dal;

import java.sql.Date;


public class AccountBanHistory {
    public int AccountId;
    public int AdminId; 
    public Date lockDate; 
    public Date unlockDate; 
    public int LockTimes; 
    public String LockReason ;

    public AccountBanHistory() {
    }

    public AccountBanHistory(int AccountId, int AdminId, Date lockDate, Date unlockDate, int LockTimes, String LockReason) {
        this.AccountId = AccountId;
        this.AdminId = AdminId;
        this.lockDate = lockDate;
        this.unlockDate = unlockDate;
        this.LockTimes = LockTimes;
        this.LockReason = LockReason;
    }

    

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Date getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(Date unlockDate) {
        this.unlockDate = unlockDate;
    }


    public int getLockTimes() {
        return LockTimes;
    }

    public void setLockTimes(int LockTimes) {
        this.LockTimes = LockTimes;
    }

    public String getLockReason() {
        return LockReason;
    }

    public void setLockReason(String LockReason) {
        this.LockReason = LockReason;
    }

    @Override
    public String toString() {
        return "AccountBanHistory{" + "AccountId=" + AccountId + ", AdminId=" + AdminId + ", lockDate=" + lockDate + ", unlockDate=" + unlockDate + ", LockTimes=" + LockTimes + ", LockReason=" + LockReason + '}';
    }

    
    
}
