import java.util.Stack; 

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version A3 Solution
 * 
 * @author Narrthanan Seevananthan
 * @version June 4, 2016
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Room beamerRoom;
    private Stack<Room> previousRoomStack;
    private Item currentItem;
    private int hunger;
        
    /**
     * Create the game and initialise its internal map, as well
     * as the previous room (none) and previous room stack (empty).
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previousRoom = null;
        previousRoomStack = new Stack<Room>();
        currentItem = null;
        hunger = 0;
        beamerRoom = null;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        Item chair, bar, computer, computer2, tree, cookie;
        Beamer beamer1, beamer2; 
        TransporterRoom Troom;
        
        // create some items
        chair = new Item("chair" , "a wooden chair",5);
        bar = new Item("bar" , "a long bar with stools",95.67);
        computer = new Item("computer" , "a PC",10);
        computer2 = new Item("computer2" , "a Mac",5);
        tree = new Item("tree" , "a fir tree",500.5);
        cookie = new Item ("cookie", "a delicious chcolate chip cookie", 0.1);
        beamer1 = new Beamer ("beamer", "a beamer", 2);
        beamer2 = new Beamer ("beamer", "the second beamer", 2);
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        Troom = new TransporterRoom("in the teleportation room");
        
        // put items in the rooms
        outside.addItem(tree);
        outside.addItem(tree);
        theatre.addItem(chair);
        theatre.addItem(cookie);
        pub.addItem(bar);
        pub.addItem(cookie);
        lab.addItem(chair);
        lab.addItem(computer);
        lab.addItem(chair);
        lab.addItem(computer2);
        lab.addItem(beamer1);
        office.addItem(chair);
        office.addItem(computer);
        office.addItem(cookie);
        office.addItem(beamer2);
        
        // initialise room exits
        outside.setExit("east", theatre); 
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("west", Troom);
        
        Troom.setExit("east", lab);

        office.setExit("west", lab);

        outside.addRoom(outside);
        theatre.addRoom(theatre);
        pub.addRoom(pub);
        lab.addRoom(lab);
        office.addRoom(office);
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("take"))
        {
            take(command);
        }
        else if (commandWord.equals("drop"))
        {
            drop(command);
        }
        else if (commandWord.equals("charge"))
        {
            charge(command);
        }
        else if (commandWord.equals("fire"))
        {
            fire(command);
        }
        
        
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * If we go to a new room, update previous room and previous room stack.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            if(currentRoom.getShortDescription().equals("in the teleportation room"))
            {
            Room nextRoom = currentRoom.getExit("west");
            
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
            }
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "Look" was entered. Check the rest of the command to see
     * whether we really want to look.
     * 
     * @param command The command to be processed
     */
    private void look(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        }
        else {
            // output the long description of this room
            System.out.println(currentRoom.getLongDescription());
            if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
        }
    }
    
    /** 
     * "Eat" was entered. Check the rest of the command to see
     * whether we really want to eat.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Eat what?");
        }
        else {
            if(currentItem.getName().equals("cookie"))
            {
            // output that we have eaten
            System.out.println("You have eaten your cookie and are no longer hungry.");
            currentItem = null;
            hunger = 5;
            }
            else
            {
            // output that we have eaten
            System.out.println("You have no food. Searching for a cookie.");
        }
        }
    }
    
    /** 
     * "Back" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        }
        else {
            // go back to the previous room, if possible
            if (previousRoom==null) {
                System.out.println("No room to go back to.");
            } else {
                // go back and swap previous and current rooms,
                // and put current room on previous room stack
                Room temp = currentRoom;
                currentRoom = previousRoom;
                previousRoom = temp;
                previousRoomStack.push(temp);
                // and print description
                System.out.println(currentRoom.getLongDescription());
                if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
            }
        }
    }
    
    /** 
     * "StackBack" was entered. Check the rest of the command to see
     * whether we really want to stackBack.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("StackBack what?");
        }
        else {
            // step back one room in our stack of rooms history, if possible
            if (previousRoomStack.isEmpty()) {
                System.out.println("No room to go stack back to.");
            } else {
                // current room becomes previous room, and
                // current room is taken from the top of the stack
                previousRoom = currentRoom;
                currentRoom = previousRoomStack.pop();
                // and print description
                System.out.println(currentRoom.getLongDescription());
                if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
            }
        }
    }
    
    /**
     * "take" was entered. Check the rest of the command to see
     * whether we really want to take.
     * 
     * @param command The command to be processed
     */
    private void take(Command command)
    {
        if(currentItem != null)
        {
        System.out.println("You are already holding something.");
        }
        //Check if the second word is a valid item contained in the item list for the current room
        else if(currentRoom.getItem(command.getSecondWord()) != null)
        {
            if(hunger == 0 && command.getSecondWord().equals("cookie"))
            {
                currentItem = currentRoom.getItem(command.getSecondWord());
                System.out.println("You picked up " + command.getSecondWord() + ".");
            }
            else if (hunger > 0)
            {
                currentItem = currentRoom.getItem(command.getSecondWord());
                System.out.println("You picked up " + command.getSecondWord() + ".");
                hunger--;
            }
            else if (hunger == 0)
            {
                System.out.println("You are too hungry to take items. First take and eat a cookie");
            }
        }
        else
        {
            System.out.println("The item is not contained within this room.");
        }
    }
    
    /**
     * "drop" was entered. Check the rest of the command to see
     * whether we really want to drop.
     * 
     * @param command The command to be processed
     */
    private void drop(Command command)
    {
    if(command.hasSecondWord()) {
            System.out.println("drop what?");
        }
        else if(currentItem == null)
        {
            System.out.println("You have nothing to drop.");
        }
        else {
            //item is dropped
            System.out.println("You have dropped " + currentItem.getName() + ".");
            currentItem = null;
        }
    }
    
    /**
     * "charge" was entered. Check the rest of the command to see
     * whether we really want to drop.
     * 
     * @param command The command to be processed
     */
    private void charge(Command command)
    {
    if(command.hasSecondWord()) {
            System.out.println("charge what?");
        }
     else if(currentItem.getName().equals("beamer"))
     {
        if(beamerRoom == null)
        {
            beamerRoom = currentRoom;
            System.out.println("You have charged " + currentItem.getName() + ".");
        }
        else {
            //beamer is already charged
            System.out.println("You have already charged this " + currentItem.getName() + ".");
        }
    }
    }
    
    /**
     * "fire" was entered. Check the rest of the command to see
     * whether we really want to drop.
     * 
     * @param command The command to be processed
     */
    private void fire(Command command)
    {
    if(command.hasSecondWord()) {
            System.out.println("fire what?");
        }
     else if(currentItem.getName().equals("beamer"))
     {
        if(beamerRoom == null)
        {
            System.out.println("You must charge the beamer first.");
        }
        else {
            //beamer is already chargedRoom nextRoom = currentRoom.getExit(direction);
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = beamerRoom;
            beamerRoom = null;
            
            System.out.println(currentRoom.getLongDescription());
            if(currentItem == null)
            {
            System.out.println("You are currently not carrying anything.");
            }
            else
            {
            System.out.println("You are currently holding " + currentItem.getName() + ".");
            }
        }
        }
    }
    }

