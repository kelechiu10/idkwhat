/**
 * Mage Unit Class
 * Attack: 12
 * Defence: 15
 * Health: 40
 * Movement: 4
 * Abilities:
 * -#1 Fireball: Damages unit at a distance
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class Mage extends Unit
{
    /**
     * Constructor for Mage
     */
    public Mage()
    {
        super(12,15,40,4);
    }
    
    /**
     * Gets Mage's ability
     * @param target target of the ability
     */
    public Action getAbility(Position target)
    {
        return new Action(target, 17, "attack");
    }
    
    /**
     * Returns range for Mage's Ability
     * @return range of the ability
     */
    public int getRange()
    {
        return 5;
    }
}
