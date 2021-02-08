# TDT4240_Patterns

##Singleton Pattern
The singletoon pattern ensures that only one instance of a particular object can exist in an application.

I find that the most logical way to use Singleton Pattern to use it for the PongGame class.
You can/should only have one PongGame object connected to the Desktop Launcher.
However the Desktop Launcher might also be viable to for using the Singleton Pattern

I chose to implement the Singleton pattern in the PongGame class.
The first time getInstance() is called it creates an object and sets the single_pong variable to that.
And if getInstance() would be called again it would just return 