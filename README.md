#   *[`The Decider`](http://decider.wallerlab.org)*
[ ![Download](https://api.bintray.com/packages/wallerlab/release-candidates/decider/images/download.svg) ](https://bintray.com/wallerlab/release-candidates/decider/_latestVersion)
[![Apache License](http://img.shields.io/badge/license-APACHE2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/wallerlab/decider.svg?branch=master)](https://travis-ci.org/wallerlab/decider)
[![Coverage Status](https://coveralls.io/repos/github/wallerlab/decider/badge.svg?branch=master)](https://coveralls.io/github/wallerlab/decider?branch=master)
[![Join the chat at https://gitter.im/wallerlab/decider](https://badges.gitter.im/wallerlab/decider.svg)](https://gitter.im/wallerlab/decider?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

A web app that uses game theory to select the optimal density functional and basis set combination.

![decider_small](https://cloud.githubusercontent.com/assets/13583117/17089727/102a6ff6-525c-11e6-87d2-2c1bbe77ab4b.png)

The Decider receives input from user-drawn molecules, and user-chosen parameters, and then calculates Nash Equilibria (NE).  The results 
are then shown to the user, so they can make a more informed choice.

![decider copy](https://cloud.githubusercontent.com/assets/13583117/17089682/a88affdc-525b-11e6-9d13-cf219b42c754.png)

According to [wikipedia](http://en.wikipedia.org/wiki/Nash_equilibrium
), Nash Equilibrium is “a solution concept of a non-cooperative game involving two or more players, in which…no player can benefit by changing strategies while the other players keep theirs unchanged”

![ne](https://cloud.githubusercontent.com/assets/13583117/17047608/65d3f630-5012-11e6-83a6-dc9d57f9ece8.png)


The NE for the decider is based on:

1. Similarity of user-drawn system with benchmarked system 
 
2. Mean percentage deviation of benchmarked system against references
 
3. Complexity of method (combination of basis set, functionals, and system)



