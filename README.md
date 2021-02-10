# TDT4240_Patterns

## Singleton Pattern
The singletoon pattern ensures that only one instance of a particular object can exist in an application.

I find that the most logical way to use Singleton Pattern to use it for the PongGame class.
You can/should only have one PongGame object connected to the Desktop Launcher.
However the Desktop Launcher might also be viable to for using the Singleton Pattern

I chose to implement the Singleton pattern in the PongGame class.
The first time getInstance() is called it creates an object and sets the single_pong variable to that.
And if getInstance() would be called again it would just return the instance

## Entity Component System
After ananlyzing the different patterns I found out that the most appropriate implementation
of one of the patterns was the ECS. 

The Entity Component System uses composision over inheritance to allow greater flexability.
Every object in the game is an entity
Every Entity conists of one or more compnent which adds behaviour or functionality
The behaviour of an entity can be changed at runtime by adding or removing components.

