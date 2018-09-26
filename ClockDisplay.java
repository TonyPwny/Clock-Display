
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
    private boolean beforeMidday;    // added field for true = AM, false = PM
    private String displayString;    // simulates the actual display
    private String meridiem;         // added String field to store AM/PM indicator
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00, where updateDisplay() now displays
     * 12 instead of 00 hours, looping at 12 instead of 24.  It sets
     * beforeMidday as true before passing it to the setMeridiem method.
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
     * setTime now also takes into account the appropriate meridiem
     * by using the beforeMidday boolean.
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
        
        // New conditional to check when to flip AM/PM indicator
        if(hours.getValue() == 0 && minutes.getValue() == 0)
        {
            
            beforeMidday = !beforeMidday;
            setMeridiem(beforeMidday);
        }
        
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     * Added boolean beforeMidday and the setMeridiem method that uses it.
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
    
    /**
     * Method which modifies the String meridiem accordingly.
     */
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
    
    /**
     * Method which returns the String meridiem, the AM/PM indicator.
     */
    private String getMeridiem()
    {
        
        return meridiem;
    }
    
    /**
     * Update the internal string that represents the display.
     * Modified to show 12-hour formatting with AM/PM indicator.
     */
    private void updateDisplay()
    {
        // New conditional to accomodate 12-hour formatting
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
