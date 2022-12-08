
# Bouncing Ball

A simple bouncing ball animation in the CLI.

![Alt Text](/static/bouncing_ball_demo.gif)

## Usage

The project is built using sbt. To run the animation use the following command:

```bash
sbt run
```

## Parameters

This is the list of configurable parameters to play with the animation:

*Grid parameters*

- `--w`: Set the width of the Grid. Integer field.
- `--h`: Set the height of the Grid. Integer field.
- `--g`: Set the gravity of the Grid. Two float numbers that define a 2D vector, specified as *number1*=*number2*, where each number is the respective coordinate.
- `--floord`: Set the density of the ceil, i.e. the ratio of lost energy when the ball touches the ceil. Float field.
- `--walld`: Set the density of the walls, i.e. the ratio of lost energy when the ball touches a wall. Float field.

*Ball parameters*

- `--p`: Set the starting position of the ball. Two integer numbers that define the center of the ball, specified as *number1*=*number2*, where each number is the respective coordinate.
- `--r`: Set the radius of the ball. Integer field.
- `--v`: Set the starting velocity of the ball. Integer field. Two integer numbers that define the center of the ball, specified as *number1*=*number2*, where each number is the respective coordinate.

*Display parameters*

- `--background`: Set the background character of the display. Character field.
- `--fps`: Set the FPS of the animation. Integer field.

The parameters are passed to the program as follows:

```bash
sbt "run --grid --w 20 --h 20 --g 0=-100 --ball --p 5=5 --r 2"
```

This command uses a 20x20 grid with a gravity of (0, -100) and a ball starting in position (5, 5) and radius 2.







