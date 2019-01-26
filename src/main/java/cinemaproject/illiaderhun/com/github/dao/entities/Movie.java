package cinemaproject.illiaderhun.com.github.dao.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Movie {

    private Integer id;
    private String title;
    private String description;
    private String time;
    private BigDecimal price;
    private String poster;
    private Integer scheduleId;

    public Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.time = builder.time;
        this.price = builder.price;
        this.poster = builder.poster;
        this.scheduleId = builder.scheduleId;
    }

    public static class Builder {
        // required fields
        private String title;
        private String description;

        // optional fields
        private Integer id;
        private String time;
        private BigDecimal price;
        private String poster;
        private Integer scheduleId;

        public Builder(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setPoster(String poster) {
            this.poster = poster;
            return this;
        }

        public Builder setScheduleId(Integer scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(time, movie.time) &&
                Objects.equals(price, movie.price) &&
                Objects.equals(poster, movie.poster) &&
                Objects.equals(scheduleId, movie.scheduleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, time, price, poster, scheduleId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", poster='" + poster + '\'' +
                ", scheduleId=" + scheduleId +
                '}';
    }
}
