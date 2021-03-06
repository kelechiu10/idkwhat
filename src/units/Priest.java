package units;
import logic.*;
/**
 * Priest Unit Class
 * Attack: 10
 * Defence: 14
 * Health: 40
 * Movement: 2.5
 * Abilities:
 * -#1 Heal: Heals a unit at a distance
 *
 * @author Matthew Oh
 * @version 5/25/18
 */
public class Priest extends Unit
{
    /**
     * Constructor for Priest
     * @param t team of the unit
     */
    public Priest(String t)
    {
        super(10,14,40,2.5,t,"Priest");
    }
    
    /**
     * Gets Priest's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, current, "heal");
    }
    
    /**
     * Returns the range of the Priest's ability
     * @return the range of the ability 
     */
    public double getRange()
    {
        return 2.5;
    }
    
    /**
     * Gets the power of the Priest's ability
     * @return the power of the Preist's ability
     */
    public int getAbilityPower()
    {
        return 10;
    }
}
