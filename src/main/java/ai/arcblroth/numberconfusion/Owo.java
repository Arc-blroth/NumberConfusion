package ai.arcblroth.numberconfusion;

import net.devtech.grossfabrichacks.entrypoints.PrePreLaunch;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;

public class Owo implements PrePreLaunch {

    public static final boolean shuffleInts   = true;
    public static final boolean shuffleBytes  = true;
    public static final boolean shuffleShorts = true;
    public static final boolean shuffleLongs  = true;
    public static final boolean shuffleChars  = true;
    public static final boolean swapBooleans  = true;

    @Override
    public void onPrePreLaunch() {
        try {
            Logger LOGGER = LogManager.getLogger("Arc'blroth");
            LOGGER.log(Level.ERROR, "Shuffling caches! Let the party begin!");

            Class<?> intCacheClass = Class.forName("java.lang.Integer$IntegerCache");
            Class<?> byteCacheClass = Class.forName("java.lang.Byte$ByteCache");
            Class<?> shortCacheClass = Class.forName("java.lang.Short$ShortCache");
            Class<?> longCacheClass = Class.forName("java.lang.Long$LongCache");
            Class<?> charCacheClass = Class.forName("java.lang.Character$CharacterCache");
            Class<?> booleanClass = Class.forName("java.lang.Boolean");

            Integer[] newIntCache = new Integer[0];
            Byte[] newByteCache = new Byte[0];
            Short[] newShortCache = new Short[0];
            Long[] newLongCache = new Long[0];
            Character[] newCharCache = new Character[0];

            // this a point where I wish java had macros
            if(shuffleInts) {
                int low = getPrivateStaticInt(intCacheClass, "low");
                int high = getPrivateStaticInt(intCacheClass, "high");
                ArrayList<Integer> newCacheList = new ArrayList<>();
                for (int i = low; i < high; i++) {
                    newCacheList.add(i);
                }
                newCacheList.add(high);
                Collections.shuffle(newCacheList);
                newIntCache = newCacheList.toArray(newIntCache);
            }
            if(shuffleBytes) {
                byte low = -128;
                byte high = 127;
                ArrayList<Byte> newCacheList = new ArrayList<>();
                for (byte i = low; i < high; i++) {
                    newCacheList.add(i);
                }
                newCacheList.add(high);
                Collections.shuffle(newCacheList);
                newByteCache = newCacheList.toArray(newByteCache);
            }
            if(shuffleShorts) {
                short low = -128;
                short high = 127;
                ArrayList<Short> newCacheList = new ArrayList<>();
                for (short i = low; i < high; i++) {
                    newCacheList.add(i);
                }
                newCacheList.add(high);
                Collections.shuffle(newCacheList);
                newShortCache = newCacheList.toArray(newShortCache);
            }
            if(shuffleLongs) {
                long low = -128;
                long high = 127;
                ArrayList<Long> newCacheList = new ArrayList<>();
                for (long i = low; i < high; i++) {
                    newCacheList.add(i);
                }
                newCacheList.add(high);
                Collections.shuffle(newCacheList);
                newLongCache = newCacheList.toArray(newLongCache);
            }
            if(shuffleChars) {
                char high = 128;
                ArrayList<Character> newCacheList = new ArrayList<>();
                for (char i = 0; i < high; i++) {
                    newCacheList.add(i);
                }
                newCacheList.add(high);
                Collections.shuffle(newCacheList);
                newCharCache = newCacheList.toArray(newCharCache);
            }

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            if(shuffleInts) {
                Field intCacheField = intCacheClass.getDeclaredField("cache");
                intCacheField.setAccessible(true);
                modifiersField.setInt(intCacheField, intCacheField.getModifiers() & ~Modifier.FINAL);
                intCacheField.set(null, newIntCache);
            }

            if(shuffleBytes) {
                Field byteCacheField = byteCacheClass.getDeclaredField("cache");
                byteCacheField.setAccessible(true);
                modifiersField.setInt(byteCacheField, byteCacheField.getModifiers() & ~Modifier.FINAL);
                byteCacheField.set(null, newByteCache);
            }

            if(shuffleShorts) {
                Field shortCacheField = shortCacheClass.getDeclaredField("cache");
                shortCacheField.setAccessible(true);
                modifiersField.setInt(shortCacheField, shortCacheField.getModifiers() & ~Modifier.FINAL);
                shortCacheField.set(null, newShortCache);
            }

            if(shuffleLongs) {
                Field longCacheField = longCacheClass.getDeclaredField("cache");
                longCacheField.setAccessible(true);
                modifiersField.setInt(longCacheField, longCacheField.getModifiers() & ~Modifier.FINAL);
                longCacheField.set(null, newLongCache);
            }

            if(shuffleChars) {
                Field charCacheField = charCacheClass.getDeclaredField("cache");
                charCacheField.setAccessible(true);
                modifiersField.setInt(charCacheField, charCacheField.getModifiers() & ~Modifier.FINAL);
                charCacheField.set(null, newCharCache);
            }

            if(swapBooleans) {
                Field origTrueField = booleanClass.getDeclaredField("TRUE");
                origTrueField.setAccessible(true);
                modifiersField.setInt(origTrueField, origTrueField.getModifiers() & ~Modifier.FINAL);
                Field origFalseField = booleanClass.getDeclaredField("TRUE");
                origFalseField.setAccessible(true);
                modifiersField.setInt(origFalseField, origFalseField.getModifiers() & ~Modifier.FINAL);
                Object origTrue = origTrueField.get(null);
                Object origFalse = origFalseField.get(null);
                origTrueField.set(null, origFalse);
                origFalseField.set(null, origTrue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPrivateStaticInt(Class<?> clazz, String fieldName) throws ReflectiveOperationException {
        Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        return (int) f.get(null);
    }

}
