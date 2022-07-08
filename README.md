# Riddler: The OO-Sudoku Solver

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at
HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract

Welcome to the Riddler project. This project was intended to solve standard (9x9) Suokus of all difficulty levels. With
the help of a homemade backtracking algorithm, this Riddler solves Suokus in a (possibly) efficient manner.

This Riddler reads unsolved Sudokus from a .txt file and outputs them to the standard outout. The Filepath to the
unsolved sudoku must be passed as the only argument. Under the hood, the Riddler relies on an Array, no, a *Collection*
of Features enabling this functionality. These features are listed in the table below.

In the beginning, we struggled with sticking to TDD. Unsure of how we would model the programm, we were afraid of having
to rewrite a large fleet of tests. More interestingly, we discovered the subjectivity of good object-oriented code.
While some coding practices are widely considered objectively displeasing, a fair amount is left to argue about. No team
of developers will ever agree on every line of code. Therefore, everyone involved needs to be able to make compromises
for the sake of progress (and arguably their sanity).

## Feature List

All features required for a rudimentary sudoku solver. These include the components making up the "Riddler" program:

| Number | Feature          | Tests                           |
|--------|------------------|---------------------------------|
| 1      | Sudoku Parser    | hwr.oop.riddler.io.parser       |
| 2      | Sudoku Printer   | hwr.oop.riddler.io              |
| 3      | Sudoku Solver    | hwr.oop.riddler.logic.solver    |
| 4      | Sudoku Validator | hwr.oop.riddler.logic.validator |
| 5      | Sudoku Model     | hwr.oop.riddler.model           |

## Additional Dependencies

We decided to use Lombok for the comfort of all readers of our code, because getters/setters without logic are ugly and
are typically skipped when reading anyway.

| Number | Dependency Name | Dependency Description              | Why is it necessary?              |
|--------|-----------------|-------------------------------------|-----------------------------------|
| 1      | Lombok          | generates getter and setter methods | circumvents getter/setter clutter |

