// package decl
package captainsly.anvil.mechanics.enums;

// imports

// class decl
public enum EnumMagicType {
    FIRE, LIGHT, DARK, WATER, FROST, EARTH;

    // Get the magic type from string by looping values and comparing the name
    // toLowerCase and seeing if they match
    public static EnumMagicType getMagicType(String name) {
        for (EnumMagicType magicType : values())
            if (magicType.name().toLowerCase().equals(name.toLowerCase()))
                return magicType;

        return null;
    }

}