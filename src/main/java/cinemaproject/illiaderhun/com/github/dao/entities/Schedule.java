package cinemaproject.illiaderhun.com.github.dao.entities;

import java.sql.Date;
import java.util.Objects;

public class Schedule {

    private Integer id;
    private Date dateTime;
    private String dayOfWeek;
    private Integer movieId;
    private Integer orderId;

    public Schedule(Builder builder) {
        this.id = builder.id;
        this.dateTime = builder.dateTime;
        this.dayOfWeek = builder.dayOfWeek;
        this.movieId = builder.movieId;
        this.orderId = builder.orderId;
    }

    public static class Builder {
        // required fields
        private Date dateTime;
        private String dayOfWeek;

        // optional fields
        private Integer id;
        private Integer movieId;
        private Integer orderId;

        public Builder(Date dateTime, String dayOfWeek) {
            this.dateTime = dateTime;
            this.dayOfWeek = dayOfWeek;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setMovieId(Integer movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder setOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public Schedule build() {
            return new Schedule(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) &&
                Objects.equals(dateTime, schedule.dateTime) &&
                Objects.equals(dayOfWeek, schedule.dayOfWeek) &&
                Objects.equals(movieId, schedule.movieId) &&
                Objects.equals(orderId, schedule.orderId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateTime, dayOfWeek, movieId, orderId);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", movieId=" + movieId +
                ", orderId=" + orderId +
                '}';
    }
}
