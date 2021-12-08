package captainsly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Random;

import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceInterpreter;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;

import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;

public class Utils {

    public static final String WORKING_DIRECTORY = System.getProperty("user.dir") + "/anvil/";
    public static final String SAVE_DIRECTORY = WORKING_DIRECTORY + "saves/";
    public static final String SCRIPT_DIRECTORY = WORKING_DIRECTORY + "scripts/";

    public static void createDefaultWorkingDir() {
        File workingDir = new File(WORKING_DIRECTORY);
        if (!workingDir.exists()) {
            workingDir.mkdir();
        }
    }

    public static String toNormalCase(String s) {
        // If there is an underscore in the string, it will be replaced with a space
        // and the first letter of the string will be capitalized
        // If there is no underscore, the first letter of the string will be capitalized
        // If there is no string, an empty string will be returned

        if (s == null) {
            return "";
        }

        String newString = s.replace("_", " ");
        String[] words = newString.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)));
            sb.append(word.substring(1).toLowerCase());
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static void serializeObject(Object o, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String objectPath) throws ClassNotFoundException, IOException {
        FileInputStream fileIn = new FileInputStream(objectPath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object o = in.readObject();
        in.close();
        fileIn.close();

        return o;
    }

    public static boolean doesWorkingDirExist() {
        return new File(WORKING_DIRECTORY).exists();
    }

    public static int rollDice(String expression) {
        final DiceParser parser = new DefaultDiceParser();
        final DiceInterpreter<RollHistory> roller = new DiceRoller();
        final RollHistory rolls = parser.parse(expression, roller);

        return rolls.getTotalRoll();
    }

    public static Iterator<RollResult> getDiceRollResults(String expression) {
        final DiceParser parser = new DefaultDiceParser();
        final DiceInterpreter<RollHistory> roller = new DiceRoller();
        final RollHistory rolls = parser.parse(expression, roller);

        return rolls.getRollResults().iterator();
    }

    public static int getAbilityModifier(int abilityScore) {
        // Returns the modifier for the ability score using dnd rules
        // The modifier is calculated by subtracting 10 from the ability score divided by 2
        // If the result is negative, the modifier is 0
        // If the result is positive, the modifier is the result

        int modifier = abilityScore - 10;
        modifier = modifier < 0 ? 0 : modifier;

        return modifier;
    }

    public static int[] generateAbilityScores() {
        int[] abilityScores = new int[EnumAbility.values().length];
        int[] rolls = new int[EnumAbility.values().length];

        // Roll six d6, line them up and assign a letter
        // Then calculate as followed
        // 13 + 1 - 2
        // 13 + 2 - 3
        // 13 + 3 - 4
        // 13 + 4 - 5
        // 13 + 5 - 6
        // 13 + 6 - 1

        // Get the Roll Result Iterator
        int j = 0;
        Iterator<RollResult> rollResultIterator = getDiceRollResults("6d6");
        while (rollResultIterator.hasNext()) {
            // Get The Rolls
            Iterator<Integer> _rolls = rollResultIterator.next().getAllRolls().iterator();
            while (_rolls.hasNext()) {
                // Add the _rolls to the Rolls array
                rolls[j] = _rolls.next();
                j++;
            }
        }

        // Calculate rolls
        abilityScores[0] = 13 + rolls[0] - rolls[1];
        abilityScores[1] = 13 + rolls[1] - rolls[2];
        abilityScores[2] = 13 + rolls[2] - rolls[3];
        abilityScores[3] = 13 + rolls[3] - rolls[4];
        abilityScores[4] = 13 + rolls[4] - rolls[5];
        abilityScores[5] = 13 + rolls[5] - rolls[0];

        Random rand = new Random();
        // Shuffle the ability scores before returning
        for (int i = 0; i < abilityScores.length; i++) {
            int randomIndexToSwap = rand.nextInt(abilityScores.length);
            int temp = abilityScores[randomIndexToSwap];
            abilityScores[randomIndexToSwap] = abilityScores[i];
            abilityScores[i] = temp;
        }

        return abilityScores;
    }

    public static int[] generateSkillScores() {
        int[] skillScores = new int[EnumSkill.values().length];
        int[] rolls = new int[EnumSkill.values().length];

        // Get the Roll Result Iterator
        int j = 0;
        Iterator<RollResult> rollResultIterator = getDiceRollResults(EnumSkill.values().length + "d6");
        while (rollResultIterator.hasNext()) {
            // Get The Rolls
            Iterator<Integer> _rolls = rollResultIterator.next().getAllRolls().iterator();
            while (_rolls.hasNext()) {
                // Add the _rolls to the Rolls array
                rolls[j] = _rolls.next();
                j++;
            }
        }

        // Calculate rolls
        for (int i = 0; i < skillScores.length; i++) {

            int z = i + 1;
            if (i <= skillScores.length)
                z = 0;

            skillScores[i] = 8 + rolls[i] - rolls[z];
        }

        Random rand = new Random();
        // Shuffle the ability scores before returning
        for (int i = 0; i < skillScores.length; i++) {
            int randomIndexToSwap = rand.nextInt(skillScores.length);
            int temp = skillScores[randomIndexToSwap];
            skillScores[randomIndexToSwap] = skillScores[i];
            skillScores[i] = temp;
        }

        return skillScores;
    }

}
