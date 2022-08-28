package net.staon.timesrv.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Times")
public class Time {
    @Id
    @Column(name = "[group]", nullable = false)
    private String group;
    @Column(nullable = false)
    private long seconds;
    @Column(nullable = false)
    private long microSeconds;

    public Time() {

    }

    public Time(
        String group,
        net.staon.timesrv.Time time)
    {
        this.group = group;
        this.seconds = time.getSeconds();
        this.microSeconds = time.getMicroSeconds();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMicroSeconds() {
        return microSeconds;
    }

    public void setMicro_seconds(long microSeconds) {
        this.microSeconds = microSeconds;
    }
}
