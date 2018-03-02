# Example of genetic algorithm

## Task
Having 9 digits (0 - 9) and 4 operations (+, -, /, *) create an expression to get a target integer value.

The division operator gets an integer value as a result (i.e. 5 / 2 = 2).

Expression have a simple format: "digit operation digit operation digit" etc.

Each operation is applied to the result of previous calculations.

For example "3 + 5 * 7" means: 3 + 5 = 8; 8 * 7 = 56. So, no priority of operations: they are applied one by one from the left to the 
right.


## Solution
This is a Maven project with solution in Java 8.
The solution uses a [genetic algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm) to find the result.
- Each _individual_ represents a possible solution;
- The _Algorithm_ class performs several _iterations_ to get new _populations_ which are closer to the result that we want to get;
- The _AlgorithmParams_ class contains options of the genetic algorithm: count of iterations, probabilities (rates) of crossover and 
mutation, population size, elitism flag.

## Options
**iterations** - number of iterations that algorithm performs. The less this number is - the less is probability to find a solution. The 
more
this 
number is - the 
longer the algorithm works.

**populationSize** - a number of individuals which will be generated for a population. This number can be odd or even, does not matter.

**crossoverRate** - a probability that two selected individuals will be interbred. If they are not interbred, they will be added to a new
 population as is.
 
 **mutationRate** - a probability that a certain individual will mutate.
 
 **elitism** - if this flag is set, one individual which fits best the conditions (fitness function) will be added to the next population
  without changes.  


## How to run
- Clone or download the project;
- Run _AlgorithmTest_ in your IDE or with the command `mvn test -DAlgorithmTest`;
- Add new tests, change parameters, have fun!

## Acknowledges
I found this task here: [http://www.ai-junkie.com/ga/intro/gat3.html](http://www.ai-junkie.com/ga/intro/gat3.html).

Thanks to this guy, it is much more interesting than solving classical tasks like guessing a string of certain length.  
