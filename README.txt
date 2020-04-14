Group Member 1: Benjamin Borden - U53067162
Group Member 2: Elyse Kaczmarek - U65134290

Group Number: 6

How to Run Program!
1.  Verify that you have an up to date JDK installed, if not installed, go to
    https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html
    to download and install a copy

2.  Open a terminal on your device, and navigate until you see Main.java.

3.  Once you can see Main.java, type the command

    javac *.java

    in order to compile the files and

    java Main

    in order to run the program. The prompts will guide you from there.

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
        Nexus.java: a market tile which serves as a "home base" for either a hero or a monster team. Also contains a win condition checking for Legends game.
    CommonTile.java: a common tile is somewhere that a player can encounter monsters and fight.
        BoostTile.java: a boost tile is an abstract class representing a tile which may boost some skill of the hero stepping on it
            PlainTile.java: a BoostTile which boosts no skills of the hero
            BushTile.java: a BoostTile which boosts a hero's dexterity
            CaveTile.java: a BoostTile which boosts a hero's agility
            KoulouTile.java: a BoostTile which boosts a hero's strength

Board.java: a board is made of many tiles and displays where the heroes are on the map
    LegendsBoard.java: an 8x8 board specifically crafted for the Quest of Legends game - also contains methods for Legends-specific movement patterns

Fight.java: a fight is a conflict between heroes and monsters which can occur in a common tile

Game.java: a game loads in data from the .txt files and constructs a turn rotation for a player to continue the game
    LegendsGame.java: contains the start of gameplay specific to Quest of Legends rules

Input.java: input is a class which just static public method which other files use. Notably getChar and getInt allow for safe user input
Player.java: a player has a unique identifier and a set of heroes that they possess, a player has turns in a game
Main.java: a class which initiates gameplay for Quest of Legends (runs the rest of the program).

Armory.txt: holds data for types of armors available in-game
Potions.txt: holds data for types of potions available in-game
Weaponry.txt: holds data for types of weapons available in-game
FireSpells.txt: holds data for types of fire spells available in-game
IceSpells.txt: holds data for types of ice spells available in-game
LightningSpells.txt: holds data for types of lightning spells available in-game
Paladins.txt: holds data for types of paladin heroes available in-game
Sorcerers.txt: holds data for types of sorcerer heroes available in-game
Warriors.txt: holds data for types of warrior heroes available in-game
Dragons.txt: holds data for types of dragon monsters available in-game
Exoskeletons.txt: holds data for types of exoskeleton monsters available in-game
Spirits.txt: holds data for types of spirit monsters available in-game

5. Notes about implementation
We chose to show the HP for monsters to be negative after the hero deals
the mortal blow, since we believed it would be useful for players to be able 
to see how much damage they did to the monster. 
We also decided to allow all heroes to recieve HP and experience after a 
monster has been defeated since it would increase the player enjoyability and
makes the game more fast-paced, since it will allow heroes to level up sooner.
In addition, we thought it was feasible in our world that all the heroes share
the "loot" gained from defeating any particular monster.




