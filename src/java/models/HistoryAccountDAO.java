/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.AccountBanHistory;
import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class HistoryAccountDAO extends DBContext{
    public int upDateUnban(int accid, Date unlockDate, int locktimes) {
        int result = 0;
        try {
            String sql = "UPDATE historylock SET `unlockDate` = ? WHERE (`AccountId` = ?) and (`LockTimes` = ?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, unlockDate);
            st.setInt(2, accid);
            st.setInt(3, locktimes);
            
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int AddToListHistoryBan(int accid, int adminid, Date lockdate, int locktimes, String reason) {
        int result = 0;
        try {
            String sql = "INSERT INTO historylock (AccountId, accountbanid, lockDate,  LockTimes, LockReason)\n"
                    + "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accid);
            st.setInt(2, adminid);
            st.setDate(3, lockdate);
            st.setInt(4, locktimes);
            st.setString(5, reason);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<AccountBanHistory> getHistoryBanByIdAcc(int accid) {
        ArrayList<AccountBanHistory> hislist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM historylock WHERE AccountId = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hislist.add(new AccountBanHistory(rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getString(7)));
            }
        } catch (SQLException e) {
        }
        return hislist;
    }
}
