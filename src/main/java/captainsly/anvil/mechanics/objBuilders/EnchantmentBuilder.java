package captainsly.anvil.mechanics.objBuilders;

import captainsly.anvil.mechanics.enums.EnumMagicType;
import captainsly.anvil.mechanics.magic.Enchantment;

public class EnchantmentBuilder {

    private Enchantment enchantment;

    // Create a new enchantment (String, String, String, EnumMagicType) then return
    // EnchantmentBuilder
    public EnchantmentBuilder createEnchantment(String name, String description, String type, EnumMagicType magicType) {
        enchantment = new Enchantment(name, description, type, magicType);

        return this;
    }

    // Build enchantment and return it
    public Enchantment build() {
        return enchantment;
    }

}
