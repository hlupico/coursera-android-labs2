##The Activity Class  (Lab 2)

####Objective: 
Become familiar with the Activity class, Activity lifecycle and the Android reconfiguration process by creating an application that monitors routine calls to lifecycle methods while navigating between two Activities. 
                
####Application Summary: 
Upon opening the ActivityLab application the user will see a simple layout associated with ActivityOne. The number of calls made to ActivityOne’s lifecycle methods (onCreate, onStart, onResume and onRestart) will be shown on the screen. A button prompting the user to “Start Activity Two” will also appear. Selecting the “Start Activity Two” button will start ActivityTwo and bring the user to layout similar to ActivityOne’s layout. 

Again, the number of calls to lifecycle methods (onCreate, onStart, onResume and onRestart) for the current activity, ActivityTwo, will be shown. A button prompting the user to “Close Activity” will bring the user back to ActivityOne. The number of calls to the Activity lifecycle methods are updated and displayed on the screen.  
A series of Logcat statements displays the order in which the Activity lifecycle methods are called. 

##Intents (Lab 3, Part 1)

####Objective: 
Work with implicit and explicit intents to start activities. Use an implicit intent to start an activity through an App Chooser. Implement <intent-filters> to enable secondary applications to receive implicit intents.

####Application Summary: 
The opening layout of the IntentsLab application will give the user the option to start an Implicit or Explicit Intent by selecting respective buttons. The two scenarios result in the following:

Explicit Intent Selected - The user is brought to another activity, ExplicitlyLoadedActivity, and prompted to enter a text. When the user selects the Enter button they are brought back to the main activity, ActivityLoaderActivity. The message that was entered in the ExplicitlyLoadedActivity is displayed in the ActivityLoaderActivity layout. 

Implicit Intent Selected - An App Chooser is displayed, prompting the user to select the Android default browser or a dummy “My Browser” app to load Google.com. Selecting the Android default browser brings the user to Google.com in the browser window. If “MyBrowser” is chosen, another application is activated to load and display Google.com.

##Permissions (Lab 3, Part 2)

####Objective: 
Become familiar with Android Permissions. Create applications that use, define and enforce Android Permissions. 
####Application Summary: 
The user will be presented with a button labeled “Bookmarks Activity” - selecting this button will bring them to the “Bookmarks Activity”. The user is then given the option to “Get Bookmarks” or “Go to Dangerous Activity”

“Get Bookmarks” Selected - The application retrieves the user’s bookmarks from the browser and displays them in a TextView on the screen.

“Go to Dangerous Activity” Selected - The GoToDangerousActivity is started. The custom permissions defined will allow the user to navigate to the Dangerous Application when “Start Dangerous Activity” is selected.

##Fragments (Lab 3, Part 3)

####Objective: 
Create a simple application that uses Fragments (ListFragments & Fragments) to produce a two-pane or single-pane user interface depending on the current device’s screen size.

####Application Summary: 
This application simulates Twitter in a sense that it displays a list of “friends” names. When a name from the list is selected the tweets associated with the friend selected are displayed in a TextView. As mentioned above, the application will appear as a two-pane or a single-pane interface depending on the screen size of the device in use.

##The User Interface (Lab 4)

####Objective: 
Become familiar with Android’s UI classes (Buttons, EditText, RadioButton, Textview). Use BaseAdpater and ListView to display application data.

####Application Summary: 
A ToDo application which displays ToDo tasks in a ListView. Upon opening the application, the user can select the “Add ToDo Item” footer to add a new ToDo item. The user enters the title of the ToDo item, due date, priority level and status of the task. If the user saved the new ToDo item, the item is added to the ListView. The user also has the option to cancel items or adjust the item’s description and details.   

##AsyncTask (Lab 5)

####Objective: 
Develop a better understanding of the AsyncTask class. Download data without blocking the main UI Thread.

####Application Summary: 
An extension of the Fragments Lab - this application uses the AsyncTask to “download” simulated Twitter data. Three fragments are used in this application: FriendsFragment, DownloaderTaskFragment and FeedFragment. When a friend’s name is selected (FriendsFragment) the application starts an AsyncTask (DownloaderTaskFragment) which reads data and returns results to the main UI to display (FeedFragment).

##Notifications (Lab 6)

####Objective: 
Implement User Notifications and Broadcast Receivers to display download updates to a user. Pass data from Main UI to Fragments with Bundle arguments. Send and receive broadcast intents.

####Application Summary: 
This application extends the work completed in Lab 5, the AsyncTask Lab. The application simulates a Twitter feed and implements user notifications and broadcast receivers or Toast messages to notify the user when the data downloads have been completed. If the user is within the application when a data download has been completed a Toast message will appear stating that new data is available. Otherwise, if the user has left the application a Notification Area Notification will appear in the Notification Drawer. Selecting the notification from the Notification Drawer will bring the user back to the NotificationsLab application.  

##Graphics & Animation (Lab 7)

####Objective: 
To gain a better understanding of Graphics, Animation, Touch and Multimedia. Become familiar with the AudioManager, Soundpool, BitMap, GestureDetector classes. 

####Application Summary: 
The user will be presented with an empty screen. When the screen is touched a bubble will appear at the touch location. The bubble will begin rotating and will move across the screen at a randomized rate and direction. Touching other areas of the screen will cause new bubbles to appear. If an existing bubble is "popped" the bubble will disappear and a popping sound will be played. If a user chooses to "fling" an existing bubble, the bubble will change its direction and velocity based on the onFling() event.

##Location (Lab 8)

####Objective: 
Learn how to use location information and have a better understanding of how to listen for/respond to Location measurements. MockLocationProvider and LocationListener used. 

####Application Summary: 
This application displays a ListView containing a set of Place Badges. Each Place Badge contains a country flag, a country name, and a place name corresponding to the user's location when the Place Badge was acquired. The Footer for the ListView displays the words "Get New Place." When the user clicks on the Footer, the application will attempt to capture a new Place Badge based on the user's current location. 

###Note: The source of the labs completed in this repo can be found in Adam Porter’s Coursera course for Android Development. Some of the Lab Objectives/Application Summaries were taken from Porter’s course documentation but abridged for quick reference.
