package cinemaproject.illiaderhun.com.github.dao.entities;

import java.util.Objects;

public class Order {

    private Integer id;
    private Integer row;
    private Integer col;
    private Integer userId;
    private Integer scheduleId;

    public Order(Integer id, Integer row, Integer col, Integer userId, Integer scheduleId) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(row, order.row) &&
                Objects.equals(col, order.col) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(scheduleId, order.scheduleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, row, col, userId, scheduleId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", row=" + row +
                ", col=" + col +
                ", userId=" + userId +
                ", scheduleId=" + scheduleId +
                '}';
    }
}
