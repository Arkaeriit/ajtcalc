# ajtcalc

A program made to let you do some small calculations in your terminal.

Example of small calculations:

![Alt text](https://i.imgur.com/22gJfoj.png "Some simple math.")

## User manual

### Installation
To install this just do
```bash
make && sudo make install
```

### Usage

Usage: 
* `ajtcalc [expression to solve]` to solve the expression
* `ajtcalc shell` to start a shell where you will be able to enter expressions
* `ajtcalc file [filename] <expressionw>` to interpret a file where there is a list of expressions
* `ajtcalc help` to print a user manual


### Expressions

The expression to solve is a sequence of number or sub-expression put between parentheses and operations such as: `3 x ( 4 + 1 )`

Spaces are ignored, so you can use them to make your expression easier to read while you write them.

#### Operations

List of operations:
* addition, symbolized by +
* substation, symbolized by -
* multiplication, symbolized by \* or x
* division, symbolized by /
* exponentiation, symbolized by ^ or \*\*
* multiplication by a power of 10, e

For operation with more than one symbol such as multiplication, you can use each one in the same expression.

#### Numbers

Numbers are typed in the decimal system. You can use decimal numbers.

#### Parenthesis

To compute a sub-expression first you can put it between parentheses. You can use `(`, `{` or `[` to open parenthesis and `)`, `}` or `]` to close them.

#### Basic Functions

You can apply functions on numbers or expressions.
To do that you have to write the name of the function then it's arguments between parentheses, bracket or curly brackets.
List of basic functions:
* abs(x) : return the absolute value of x
* floor(x) : return x rounded down
* ceil(x) : return x rounded up
* max(x,y) : return the biggest number between x and y
* min(x,y) : return the smallest number between x and y
* solve(var,Exp,min,max) : solve Exp(var) = 0 with var being between `min` and `max`
* e() : return the constant e, Euler's number
* pi() : return the number pi
* exit() : exit a shell or stop an interpreter

#### Comments

If you put a semi-colon after an expression to ignore everything coming after the semi-colon. You can use this to put a comment on your operations.

You can also put a '#' at the beginning of a line to prevent the interpreter from reading that line.

#### Examples

Here is a list of some valid expressions:
* ( 3 * (4.0 + 0.01)) ^3
* { (3+2) * 4\*\*2 x 6 - 5 ]^0.3
* 3 \*\* (4 - 5)
* 4\*10\*\*1+2x10^0
* 2\*\*max(4,abs(-76\*2))
* abs(floor(-3.5)\*3)

### The stack

When using a shell or running a file thought an interpreter the previous answers are stored in a stack. You can access this with the function `ans`. `ans()` or `ans(0)` will give you the last calculated answer and `ans(n)` will give you the nth previous answer.

If the previous expression had a mistake in it or just printed something with `echo()` NaN will be added on the stack.

If you start an Interpreter by giving an expression after the filename in augment the result of the expression will be on top of the stack.

#### Preserving the stack

If you want to preserve the state of the stack, you can use the function `stackSave()`. To revert the stack to the previously saved state you can use `stackBack()`. Theses two functions don't change the stack in any other way. When you save the state of the stack, you can still access the values that were there before you saved. When you revert to the previous saved the changes on the stack made between the save and the revert are lost.

#### Example

Here is an example of the use of the shell where the stack is used:

```
> 1+2
3
> 4*4
16
> ans(1)
3
> 7
7
> stackSave()
> 8*8
64
> 9*9
81
> stackBack()
> ans()
7
> ans(2)
3
```

### Programming with ajtcalc

Even if I don't advise it because it is quite tedious you can use ajtcalc as an interpreter for a turing compatible language. To access a value you have to call its position on the stack. You can call a function you wrote with the `run` function. You can do conditional branching with the functions `ifBE`, `ifGT` and `ifEQ`. You should hide the calculations that aren't interesting with the functions `q` ou `quiet`.

#### Creating a function

To create a function, you must write it in a new file. You should start it with `stackSave()` to ensure that the code executed inside the function doesn't affect the rest of the program. The file must end with `stackBack(Exp1,...,ExpN)` where the content of the stackBack is what your function return. To call your function from a file you mist put its argument on top of the stack and then call it with `run(nameOfTheFileOfYourFunction)`.

#### #!
You can make an ajtcalc file executable. With this repository's makefile the interpreter you will have ta call /usr/local/share/ajtcalc/ajtcalc-crushbang.

#### Example

Inside the example folder is a program that asks the user for three numbers and then returns them sorted. You can try it by typing `ajtcalc file main.ajt` inside of the example folder.

### List of all functions

* abs(x) : return the absolute value of x
* floor(x) : return x rounded down
* ceil(x) : return x rounded up
* max(x,y) : return the biggest number between x and y
* min(x,y) : return the smallest number between x and y
* echo(message) : print message, ignore everything else
* exit() : exit a shell or stop an interpreter
* ans(n) : in a shell or in an interpreter, return the nth previous answer (starting at 0), if no argument is given the previous answer will be chosen
* define(var,Exp1,Exp2) : replace every instance of `var` in `Exp2` by `Exp1`
* solve(var,Exp,min,max) : solve Exp(var) = 0 with var being between `min` and `max`
* e() : return the constant e, Euler's number
* pi() : return the number pi
* stackSave() : save the state of the stack
* stackBack() : revert to the previously saved state of the stack
* stackBack(Exp1,...,ExpN) : revert to the previously saved state of the stack and put the evaluation of each arguments on to of the stack, at the end the last argument will be on top of the stack
* quiet(Exp) : solve `Exp` and put the resut on the stack but don't print anything
* q(Exp) : like quiet
* run(filename) : interpret the file named filename, the results of each expression from the file are put on the stack ans the results are printed
* ifBE(Exp1,Exp2,Exp3) : evaluates Exp1, if Exp1 is above 0 the function evaluates Exp3, if Exp1 is equal or below 0 it evaluates Exp2
* ifGT(Exp1,Exp2,Exp3) : evaluates Exp1, if Exp1 is below or equal to 0 the function evaluates Exp3, if Exp1 is above it evaluates Exp2
* ifEQ(Exp1,Exp2,Exp3) : evaluates Exp1, if Exp1 is equal to 0 the function evaluates Exp2, if Exp1 is not 0 it evaluates Exp2
* input(prompt) : ask the user for input, asking with the desired prompt or no prompt if none is given
* disp() : display all the answer of the interpreter so far
* showStack() : show all the content of the stack, useful for debugging, the stack is not changed

