/**
 * Archer Unit Class
 * Attack: 12
 * Defence: 10
 * Health: 45
 * Movement: 5
 * Abilities:
 * -#1 Shoot: Damages unit at a distance
 *
 * @author Matthew Oh
 * @version 5/22/18
 */
public class Archer extends Unit
{
    /**
     * Constructor for Archer
     */
    public Archer()
    {
        super(12,10,45,5);
    }
    
    /**
     * Gets Archer's ability
     * @param target target of the ability
     * @param num number of ability to be used
     */
    public Action getAbility(Position target, int num)
    {
        return new Action(target, 15, "attack");
    }
    
    /**
     * Returns target restrictions for Archer's Abilities
     * 0: adjacent spaces
     * 1: anywhere
     * @param num number of ability 
     */
    public int abilityRestriction(int num)
    {
        return 1;
    }
}
