package by.it_academy.jd2.Mk_jd2_111_25.dto;

import java.time.LocalDate;

public class Message {

    private LocalDate date;
    private String fromWho;
    private String toWhom;
    private String text;

    private Message() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Message message = new Message();

        public Builder date(LocalDate date) {
            message.date = date;
            return this;
        }

        public Builder fromWho(String fromWho) {
            message.fromWho = fromWho;
            return this;
        }

        public Builder toWhom(String toWhom) {
            message.toWhom = toWhom;
            return this;
        }

        public Builder text(String text) {
            message.text = text;
            return this;
        }

        public Message build() {
            return message;
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
