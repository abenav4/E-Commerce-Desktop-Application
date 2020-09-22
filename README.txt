Welcome to Group 0147's Trading System.

Javadocs are available under phase1/javadoc/.
Accessing index.html will allow you to view the javadocs for the whole program.

The UML is available in the UML folder.


                                            ----------------------------


** Instructions to Run Program **

VIDEO: https://www.youtube.com/watch?v=ubOZN-V_8oo

Steps to use JavaFX program:
[1] Download  JavaFX Runtime. Scroll down and download the installer for your operating system.:
https://gluonhq.com/products/javafx/
[2] Unzip to a location you remember.
[3] Pull our Phase 2 project on Markus, open /phase2/.

[4] (In IntelliJ) File -> Project Structure -> Project
[4.1] Project SDK should be set to "1.8 java version "1.8.0_261""
[4.2] SDK default should be set to (8 - Lambdas, type annotations etc.)
[5] Go to File -> Project Structure -> Libraries
[6] Click on plus sign to add JavaFX by pointing to the lib folder of the JavaFX SDK you just unzipped.

The data folder (/phase2/data/) should either be empty or existent. If there are any .ser files, delete them manually, without safe search.
Running StartScreen.main() should generate new .ser files for which any errors will not occur.

Be in /phase2/ of the project before running the program.

Run StartScreen.main() to run the program.
When exiting the program, please use the provided Exit button rather than exiting through Task Manager
or through closing through the taskbar


                                            ----------------------------


** Extended Feature List from Phase 1 **
    We are 6 people also so the prof said we can do 3 optional type 2 features and 1 unique type 3 feature.

    * All mandatory Type 1 features
    - Undo features: Admin undo trades before first meetup date, undo deleted items from user inventory.
    - Suggest trade items
    - Demo user
    - Adjust all threshold values when admin is browsing users
    - New account status: Banned

    * 3 optional type 2 features
    - Extending the trade type: adding multiple items to a single trade
    - GUI (counts as 2 features)

    * 1 unique type 3 feature
    - Private message and reporting function


                                            ----------------------------


** Instructions to Explore Features **
    Upon loading in, you will see 3 menu options:

    - option to login

    - option to create a new user account. There are no users in the system at the moment.
      You will have to create a user account to access user functionality.

    - option to login to a administrator account.
        - An admin account already exists in the system. Username: admin, pass: admin.
        Login under this account to access admin functionality.

    - option to login as a demo user to explore the program. you can directly click login in the login page



                                            ----------------------------


User Account:
    Upon logging in as a USER, you will see a menu of options.
    Many of these options will not be available since your account is new.

[1] Account Information:

    Opens up a submenu that allows you to access your account info
	[1] View your trade history : allows you to view your trade history
	[2] Set new password
	[3] View your three most frequent trading partners : will only present what partners you do have if you
	    have fewer than 3 trading partners
	[4] Your three most recent trades
	[5] Inventory : allows you to view and remove items in your personal inventory
	[6] Wishlist : allows you to view and remove items in your personal wishlist
	[7] Exit

[2] Browse through global inventory:

    Allows you to access all user's items, and add items to your wishlist and
    (if you aren't frozen or don't have more borrows than loans), will allow you to send the owner of that item a
    trade request or trade offer.
    Note: Upon initial user account creation, your first trade cannot be a borrow.

[3] Loan one of your items to another user:

    This function will automatically find someone to loan an item to.

[4] Look at your message inbox:

    Will let you browse through your messages. Any trade offers, updates, etc.
    sent to your account will be viewed here.
    You will be able to edit any trade offers through this menu, and you will be warned if you have met the maximum
    number of trades, and if you continue to edit, the offer will be deleted.
    You can also view and report private messages

[5] Add a new item to the system:

    Will allow you to request an administrator to approve of your item.
    This menu option will ask for the name and description of the item.

[6] Send admins an unfreeze request:

    If your account is frozen by an administrator, this menu option will send the
    administrators a request to unfreeze your account.

[7] Sending a private message to another user

    Will allow you to enter in a username and the message content you want to sent. If the your exist the message will
    be sent to that user's message inbox

[8] Exit.

    Should you confirm a meeting/transaction, and log back in after the meeting was supposed to occur,
    the system will automatically prompt you to confirm whether or not this meeting
    (as well as any other meetings that were supposed to have happened) actually occurred.

    If the meeting did happen,
    the items involved in the trade will be removed from their owner's accounts,
    and the items will no longer exist on the
    global inventory. We are assuming that after trading an item,
    the user would not want to automatically trade it again.

    If you have more borrows than loans, your account is frozen,
    you have too many incomplete trades,
    or if you have sent too many trade offers this week,
    the system will automatically notify you that you are unable to
    trade, and if the requirements are met,
will automatically send the admin system a request to freeze your account.


                                            ----------------------------


Admin Account:
Upon logging in as an ADMINISTRATOR, you will see a menu of options

[1] Check your message inbox:

    Allows you to view and interact with admin system messages.
    All admin accounts have access to these admin messages.
    Any item approval, freeze, and unfreeze requests will be handled here.


[2] Manage admin account:

    Accesses a submenu to manage this admin account
	[1] Change your password
	[2] Add a new admin account : adds a new admin account to the system. Username must be unique.


[3] Access the information of users:

    Browsing functionality that allows you to directly enter a username
    to manage that user's account.

    Before selecting an user, it will ask you to input a username.
    If you want to change the threshold limits for all users, on the right side are 3 buttons and a
    textfield to do that.

    After selecting a valid user, several options will show up. The user's account info is displayed on left side.

    You have the ability to do the following:
	- Change lending (number of borrows v. loans) threshold
	- banning/unbanning a user
	- Freeze/unfreeze a user
	- Change maximum number of trades per week
	- Changes the max number of incomplete trades
    - In the bottom left you can also undo and restore user's deleted items
    - restoring the last item that user deleted from their inventory


[4] Undo the on-going Trade of Users: Allows you to search one user and open a window with all on-going Trades
    of that User before the first meetup date.

[5] Logout

                                            ----------------------------


** Program Demo **

    Enter any username and password to log into the program demo instantly. You do not need
    to create an account. The program demo account has limited functionality.

                                            ----------------------------


** Usage (and Arguments) of Design Patterns **

---------Strategy Method---------

    Class: MessageResponseFactory, all the classes that implements MessageResponse
    (inside frontend/messageReplyGUI).

    The strategy is how to response to the a type of message. The reason for this is to better re use the same GUI for
    all types of messages and to encapsulate the constructors of the MessageResponses and the type of MessageResponses used
    This will make it very easy to add a new type of Message or another MessageResponse in the future.


---------Dependency Injection---------
    Classes: UserManager, Message and it's subclasses
    (UserManager inside usecase)
    Injecting the constructor of the Message from the UserManager. The reason is that we wanted to loosen of the
    UserManager with the Message classes


---------Other Notes---------
    MessageBuilder, is not a builder or factory.

    The reason we did so is to encapsulate the message constructors, but
    due to the different parameters, it could not be made into a factory.


                                            ----------------------------

** Assumptions and Extensions in Mind **

    Some assumptions we made for the admin is what user actions they can do.
    We decided that the admin can undo trades that have been agreed upon but before the meetup date.

    Another action they can do is restore items the user may have deleted.

    We did not allow the admin to undo an user adding an item
    to the system since the admin can just deny the item from being added to the system.

    We made our code adhere to SOLID principles like having
    enum classes for account statuses, so you can just add
    the string of the new account type if you want.
    So our code is  open for extension, closed to modification.

    We also assume that in the future there could be new types of users, user status and message types. So
    by using inheritance or interfaces we made it easy to add new types in the future.
    Right now it looks
    like there is a parallel hierarchy between the message response and messages, when there are new types of messages such
    as private message with attachments they will probably re use the same message response as private messages.

                                            ----------------------------

** How code has improved **

    The code has improved in terms of using generics.
    Before our instance variable types and function return types
    were HashMap and Arraylist, we changed this to Map and
    List so our code is more extensible and adheres to open/closed
    principle in case in the future we change the exact
    data structure we use in our classes. The power of generics
    will allow us to do so flawlessly.

    The code also improved in terms of extensibility.
    As explained in the previous section the account types are
    now stored in an enum class so adding an account type is
    as easy as just adding a string to the enum class.


                                            ----------------------------

