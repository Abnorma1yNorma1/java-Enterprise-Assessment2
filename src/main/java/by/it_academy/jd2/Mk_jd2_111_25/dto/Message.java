package by.it_academy.jd2.Mk_jd2_111_25.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Message {

    private Date date;
    private String fromWho;
    private String toWhom;
    private String text;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
