
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
 * @version 2018.09.28
 * original @author Michael Kölling and David J. Barnes
 * original @version 2011.07.31
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
     * creates a new clock set at 00:00.  Added default meridiem AM.
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
     * parameters.  Added a boolean input to signify meridiem.
     */
    public ClockDisplay(int hour, int minute, boolean beforeMidday)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, this.beforeMidday = beforeMidday);
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
        
        if(minutes.getValue() == 0 && hours.getValue() == 0) { // AM/PM flipper
            setMeridiem(beforeMidday = !beforeMidday);
        }
        
        updateDisplay();
    }

    /**
     * Set the meridiem String.
     */
    private void setMeridiem(boolean beforeMidday)
    {
        if(beforeMidday){
            meridiem = " AM";
        }
        else {
            meridiem = " PM";
        }
    }
    
    /**
     * Return the current meridiem of this display.
     */
    private String getMeridiem()
    {
        return meridiem;
    }
    
    /**
     * Set the time of the display to the specified hour and
     * minute. Modified it to take in a value to signify AM/PM.
     */
    public void setTime(int hour, int minute, boolean beforeMidday)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        setMeridiem(this.beforeMidday = beforeMidday);
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
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
    }
    
    /**
     * Return the current time of this display in the 12H format HH:MM XM
     */
    public String get12HourInternalDisplay()
    {
        if(hours.getValue() == 0) {
            return "12:" + minutes.getDisplayValue() + getMeridiem();
        }
        else {
            return getTime() + getMeridiem();
        }
    }

}
