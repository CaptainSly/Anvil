package captainsly.anvil.mechanics.magic;

import captainsly.anvil.mechanics.enums.EnumMagicType;

public class Enchantment {

    private String enchantmentId;
    private String enchantmentName;
    private String enchantmentDescription;
    private EnumMagicType magicType;

    public Enchantment(String enchantmentId, String enchantmentName, String enchantmentDescription,
            EnumMagicType magicType) {
        this.enchantmentId = enchantmentId;
        this.enchantmentName = enchantmentName;
        this.enchantmentDescription = enchantmentDescription;
        this.magicType = magicType;
    }

    public String getEnchantmentId() {
        return enchantmentId;
    }

    public String getEnchantmentName() {
        return enchantmentName;
    }

    public String getEnchantmentDescription() {
        return enchantmentDescription;
    }

    public EnumMagicType getMagicType() {
        return magicType;
    }

}
