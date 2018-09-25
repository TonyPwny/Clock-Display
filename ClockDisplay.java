
/**
 * The ClockDisplay class implements a digital clock display for a
 * 12 hour clock. The clock shows hours and minutes and an indicator for AM/PM. The 
 * range of the clock is 12:00am (midnight) to 11:59pm (one minute before 
 * midnight).
 * 
 * The clock maintains the hour time internally as values from 1-12, not 0-23.
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Anthony Tiongson
 * @version 2018.09.24
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private boolean beforeMidday;
    private String displayString;    // simulates the actual display
    private String meridiem;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setMeridiem(beforeMidday = true);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, boolean beforeMidday)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        this.beforeMidday = beforeMidday;
        setTime(hour, minute, beforeMidday);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        
        if(hours.getValue() == 0 && minutes.getValue() == 0) // flip AM/PM
        {
            
            beforeMidday = !beforeMidday;
            setMeridiem(beforeMidday);
        }
        
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean beforeMidday)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        
        setMeridiem(beforeMidday);
        
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    private void setMeridiem(boolean beforeMidday)
    {
        
        if(beforeMidday)
        {
            
            meridiem = " AM";
        }
        else
        {
            
            meridiem = " PM";
        }
    }
    
    private String getMeridiem()
    {
        
        return meridiem;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if(hours.getValue() == 0)
        {
            
            displayString = "12:" + 
                           minutes.getDisplayValue() + getMeridiem();
        }
        else
        {
            
            displayString = hours.getDisplayValue() + ":" + 
                           minutes.getDisplayValue() + getMeridiem();
        }
    }
}
