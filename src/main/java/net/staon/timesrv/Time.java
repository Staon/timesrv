package net.staon.timesrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of UNIX timestamp
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "time")
public class Time {
    @XmlElement(name = "seconds")
    private long seconds;
    @XmlElement(name = "micro_seconds")
    private long microSeconds;

    public Time() {

    }

    public Time(long seconds, long microSeconds) {
        this.seconds = seconds;
        this.microSeconds = microSeconds;
    }

    public Time(net.staon.timesrv.data.Time time) {
        this.seconds = time.getSeconds();
        this.microSeconds = time.getMicroSeconds();
    }

    /**
     * Get seconds
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * Get microseconds
     * @implNote Be aware that most of the sources doesn't work
     *     with microseconds precision
     */
    public long getMicroSeconds() {
        return microSeconds;
    }

    /**
     * Set seconds
     */
    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    /**
     * Set microseconds
     */
    public void setMicroSeconds(long microSeconds) {
        this.microSeconds = microSeconds;
    }
}
