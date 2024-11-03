package application;


public class entities {
	
	
    String name;
    double health;
    static double damage;
    static double placeholderDamage;
    static int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
    static int accuracy; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
    int crit_chance;
    static int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
    int position;
    int size;
    int challenge; // For determine team comps (testing this)
    int speed;
}