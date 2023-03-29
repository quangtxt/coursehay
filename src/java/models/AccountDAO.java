/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AccountDAO extends DBContext {
    
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accList = new ArrayList<>();
        try {
            String sql = "SELECT AccountId,Email,Password,status,RegisterDate,RankId FROM swp_dtb.accounts;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean Status = rs.getBoolean("Status");
                Date RegisterDate = rs.getDate("RegisterDate");
                int RankId = rs.getInt("RankId");
                int RoleId = rs.getInt("RoleId");
                accList.add(new Account(AccountId, Email, Password, Status, RegisterDate, RankId, RoleId));
            }
        } catch (SQLException e) {
        }
        return accList;
    }
    
    public Account getAccount(String emailCheck, String passCheck) {
        Account ac = null;
        try {
            String sql = "select * from Accounts where Email=? and Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, emailCheck);
            ps.setString(2, passCheck);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean Status = rs.getBoolean("Status");
                Date RegisterDate = rs.getDate("RegisterDate");
                int RankId = rs.getInt("RankId");
                int RoleId = rs.getInt("RoleId");
                ac = new Account(AccountId, Email, Password, Status, RegisterDate, RankId, RoleId);
            }
        } catch (SQLException e) {
        }
        return ac;
    }

    public Account getAccountById(int accId) {
        Account ac = null;
        try {
            String sql = "select * from Accounts where accountid=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean Status = rs.getBoolean("Status");
                Date RegisterDate = rs.getDate("RegisterDate");
                int RankId = rs.getInt("RankId");
                int RoleId = rs.getInt("RoleId");
                ac = new Account(AccountId, Email, Password, Status, RegisterDate, RankId, RoleId);
            }
        } catch (SQLException e) {
        }
        return ac;
    }
    
    public int changePass(int accId, String newPass) {
        int result = 0;
        String sql = "UPDATE Accounts SET `Password` = ? WHERE (`AccountId` = ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setInt(2, accId);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public Account getAccountByEmail(String email) {
        Account ac = null;
        try {
            String sql = "select * from Accounts where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean Status = rs.getBoolean("Status");
                Date RegisterDate = rs.getDate("RegisterDate");
                int RankId = rs.getInt("RankId");
                int RoleId = rs.getInt("RoleId");
                ac = new Account(AccountId, Email, Password, Status, RegisterDate, RankId, RoleId);
            }
        } catch (SQLException e) {
        }
        return ac;
    }

    public List<Integer> getAccountIdByRole(int role) {
        List<Integer> list = new ArrayList<>();
        try {
            String sql = "select AccountId from Accounts where roleid=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                list.add(AccountId);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Account getLastAccount() {
        try {
            String sql = "SELECT * FROM Accounts order by accountId desc limit 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean Status = rs.getBoolean("Status");
                Date RegisterDate = rs.getDate("RegisterDate");
                int RankId = rs.getInt("RankId");
                int RoleId = rs.getInt("RoleId");
                return new Account(AccountId, Email, Password, Status, RegisterDate, RankId, RoleId);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int addAccount(Account acc,String fullName) {
        int result = 0,result1=0;
        try {
            String sql = "insert into accounts(Email,Password,RegisterDate) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getEmail());
            ps.setString(2, acc.getPassword());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            result = ps.executeUpdate();
            Contact c = new Contact(getLastAccount().getAccountID(), fullName, false, null, "", "");
            result1 = new ContactDAO().addContact(c);
        } catch (SQLException e) {
        }
        if (result > 0 && result1>0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int SetSatus(boolean status, int accid) {
        int result = 0;
        String sql = "UPDATE accounts SET status = ? WHERE (`AccountId` = ?) ;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, accid);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

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
    public List<Integer> getListAccountIdByCourseID(int courseId) {
        List<Integer> listac = new ArrayList<>();
        try {
            String sql = "SELECT AccountId FROM author_course where courseid = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                listac.add(AccountId);
            }
        } catch (SQLException e) {
        }
        return listac;
    }
    public List<Integer> getAccountsBySql(String sql) {
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                list.add(AccountId);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
