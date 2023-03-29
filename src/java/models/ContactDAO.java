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
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ContactDAO extends DBContext {

    public Contact getAccContactById(int accId) {
        try {
            String sql = "SELECT AccountID,FullName,Gender,Dob,Phone,Address FROM contact where AccountID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String FullName = rs.getString("FullName");
                boolean Gender = rs.getBoolean("Gender");
                Date Dob = rs.getDate("Dob");
                String Phone = rs.getString("Phone");
                String Address = rs.getString("Address");
                return new Contact(AccountId, FullName, Gender, Dob, Phone, Address);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProfile(Account acc, Contact con) {
        String sql = "update contact set FullName = ?, Gender = ?, Dob = ?, Phone = ?, Address = ? where AccountId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, con.getFullName());
            ps.setBoolean(2, con.isGender());
            ps.setDate(3, con.getDob());
            ps.setString(4, con.getPhone());
            ps.setString(5, con.getAddress());
            ps.setInt(6, acc.getAccountID());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int addContact(Contact c) {
        int result = 0;
        try {
            String sql = "INSERT INTO `swp_dtb`.`contact` (`AccountId`, `FullName`, `Gender`, `Dob`, `Phone`, `Address`) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getAccountID());
            ps.setString(2, c.getFullName());
            ps.setBoolean(3, c.isGender());
            ps.setDate(4, c.getDob());
            ps.setString(5, c.getPhone());
            ps.setString(6, c.getAddress());
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> list = new ArrayList<>();
        try {
            String sql = "SELECT AccountID,FullName,Gender,Dob,Phone,Address FROM contact";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountId = rs.getInt("AccountId");
                String FullName = rs.getString("FullName");
                boolean Gender = rs.getBoolean("Gender");
                Date Dob = rs.getDate("Dob");
                String Phone = rs.getString("Phone");
                String Address = rs.getString("Address");
                Contact c = new Contact(AccountId, FullName, Gender, Dob, Phone, Address);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
