package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String INSERT_ITEM_SQL =
            "insert into item  (name , description , daily_rate)" +
                    " values (?, ?, ?)";

    private static final String SELECT_ITEM_SQL =
            "select * from item  where item_id  = ?";

    private static final String SELECT_ALL_ITEM_SQL =
            "select * from item ";

    private static final String UPDATE_ITEM_SQL =
            "update item  set name  = ?, item_id = ?, description = ?, daily_rate=? " +
                    "where invoice_item_id = ?";

    private static final String DELETE_ITEM_SQL =
            "delete from item  where item_id =  ?";


    @Override
    public Item addItem(Item item) {
        jdbcTemplate.update(INSERT_ITEM_SQL,item.getName(),item.getDescription(),
                item.getDailyRate());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        item.setItemId(id);
        return item;
    }

    @Override
    public List<Item> getAllItem() {
        return jdbcTemplate.query(
                SELECT_ALL_ITEM_SQL,
                this::mapRowToItem);
    }

    @Override
    public Item getItem(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_ITEM_SQL,
                    this::mapRowToItem,
                    id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no entry with the given id, just return null
            return null;
        }
    }

    @Override
    public Item updateItem(Item item) {
        jdbcTemplate.update(
                UPDATE_ITEM_SQL,item.getName(),item.getDescription(),item.getDailyRate(),item.getItemId());
        return item;
    }

    @Override
    public int deleteItem(int id) {
        return jdbcTemplate.update(DELETE_ITEM_SQL, id);
    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setDailyRate(rs.getDouble("daily_rate"));
        return item;
    }
}
