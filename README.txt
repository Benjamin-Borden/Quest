Name - Borden
ID   - U53067162

How to Run Program!
1.  Verify that you have an up to date JDK installed, if not installed, go to
    https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html
    to download and install a copy

2.  Open a terminal on your device, and navigate until you see Main.java. the
    steps needed to perform this are OS dependent and are better googled

3.  Once you can see Main.java, type the command

    javac Armor.java BlockingTile.java Board.java CommonTile.java Dragon.java Entity.java Exosleleton.java Fight.java Game.java Hero.java Input.java Item.java LivingEntity.java Main.java Market.java Monster.java Paladin.java Player.java Potion.java Sorcerer.java Spell.java Spirit.java Tile.java Warrior.java Weaponry.java

    In order to compile the files and

    java Main.java

    in order to run the program, the prompts will guide you from there.

4. Purpose of each file:

Entity.java: Entity is an abstract class. Entities are anything with names in this games, such as heroes, monsters, or weapons.
LivingEntity.java: Living entity is an abstract class descended from entity, living entities can fight and have health and levels such as heroes and monsters.
Monster.java: Monster is an abstract class descended from living entity. Monsters have defense dodge and damage. Exoskeletons dragons and spirits are monsters
Exosleleton.java: a descendent of monster, no notable unique code. Exists only to distinguish from dragons and spirits
Spirit.java: a descendent of monster, no notable unique code. Exists only to distinguish from dragons and exoskeletons
Dragon.java: a descendent of monster, no notable unique code. Exists only to distinguish from exoskeletons and spirits
Hero.java: a hero is an abstract class descended from living entity. They have the ability to level up, they have agility, dexterity, and strength, and have backpacks and equipment
Paladin.java: a hero which favors strength and agility
Sorcerer.java: a hero which favors agility and dexterity
Warrior.java: a hero which favors dexterity and strength
Item.java: an abstract descendent of entity. Items have costs and can be put in backpacks
Armor.java: an item that can be equipped and reduces damage
Spell.java: an item (scroll) that lets a spell be cast
Potion.java: an item that can be drank and increase a stat or stats
Weaponry.java: an item which can be equipped and increases damage
Tile.java: a tile is an abstract class which represents the segments that the game board is made out of
BlockingTile.java: a blocking tile is a tile that isn't passable
Market.java: a market is a tile that players can buy and sell items in, every market has a unique shopkeep
CommonTile.java: a common tile is somewhere that a player can encounter monsters and fight.
Board.java: a board is made of many tiles and shows where the heroes are
Fight.java: a fight is a conflict between heroes and monsters which can occur in a common tile
Game.java: a game loads in data from the .txt files and constructs a turn rotation for a player to continue the game
Input.java: input is a class which just static public method which other files use. Notably getChar and getInt allow for safe user input
Player.java: a player has a unique identifier and a set of heroes that they possess, a player has turns in a game
Main.java: main runs the rest of the program.






