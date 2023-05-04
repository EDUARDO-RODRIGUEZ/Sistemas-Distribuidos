package com.eduardo.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.entity.User;
import com.eduardo.helper.DB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ModelUser {

    public static String LOG = ModelUser.class.getName();

    private DB db;

    public ModelUser() {
        this.db = new DB();
    }

    public Map<UUID, User> findAll() {
        Map<UUID, User> users = new HashMap();
        try {
            PreparedStatement pstm = db.getConnection().prepareStatement("select*from public.user;");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                users.put(UUID.randomUUID(), new User(rs.getInt("id"), rs.getString("name"), rs.getString("password")));
            }
            pstm.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
        return users;
    }

    public void saveBatch(Map<UUID, User> users) {
        try {
            PreparedStatement pstm = db.getConnection().prepareStatement("insert into public.user(name,password)values(?,?);");
            db.setAutoCommit(false);
            for (Map.Entry<UUID, User> element : users.entrySet()) {
                if (Objects.isNull(element.getValue().getId())) {
                    String hash = BCrypt.with(BCrypt.Version.VERSION_2A).hashToString(12, element.getValue().getPassword().toCharArray());
                    pstm.setString(1, element.getValue().getName());
                    pstm.setString(2, hash);
                    pstm.addBatch();
                }
            }
            pstm.executeBatch();
            db.commit();
            pstm.clearBatch();
            pstm.close();
        } catch (SQLException ex) {
            db.rollback();
            System.out.println(LOG + " : " + ex.getMessage());
        } finally {
            db.setAutoCommit(true);
        }
    }

    public void open() {
        db.open();
    }

    public void close() {
        db.close();
    }
}
