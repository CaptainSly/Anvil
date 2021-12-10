package captainsly.anvil.mechanics.magic;

import captainsly.anvil.mechanics.enums.EnumMagicType;

public class Spell {

    private String spellId, spellName, spellDescription;
    private EnumMagicType spellMagicType;
    private int spellCost;
    private int spellLevel;

    public Spell(String spellId, String spellName, String spellDescription, EnumMagicType spellMagicType, int spellCost, int spellLevel) {
        this.spellId = spellId;
        this.spellName = spellName;
        this.spellDescription = spellDescription;
        this.spellMagicType = spellMagicType;
        this.spellCost = spellCost;
        this.spellLevel = spellLevel;
    }

    public String getSpellId() {
        return spellId;
    }

    public String getSpellName() {
        return spellName;
    }

    public String getSpellDescription() {
        return spellDescription;
    }

    public int getSpellCost() {
        return spellCost;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

}
