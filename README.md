# The Decider
[![Build Status](https://travis-ci.org/wallerlab/decider.svg?branch=master)](https://travis-ci.org/wallerlab/decider)
[![Coverage Status](https://coveralls.io/repos/github/wallerlab/decider/badge.svg?branch=master)](https://coveralls.io/github/wallerlab/decider?branch=master)

A web app that uses game theory to select the optimal density functional and basis set combination.

The Decider receives input from user-drawn molecules, and user-chosen parameters, and then calculates Nash Equilibria (NE).  The results 
are then shown to the user, so they can make a more informed choice.

According to [wikipedia](http://en.wikipedia.org/wiki/Nash_equilibrium
), Nash Equilibrium is “a solution concept of a non-cooperative game involving two or more players, in which…no player can benefit by changing strategies while the other players keep theirs unchanged”

![ne](https://cloud.githubusercontent.com/assets/13583117/17047608/65d3f630-5012-11e6-83a6-dc9d57f9ece8.png)


The NE for the decider is based on:

1. Similarity of user-drawn system with benchmarked system 
 
2. Mean percentage deviation of benchmarked system against references
 
3. Complexity of method (combination of basis set, functionals, and system)



