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

Usage: `ajtcalc [expression to solve]`

The expression to solve is a sequence of number or sub-expression put between parentheses and operations such as: `3 x ( 4 + 1 )`

Spaces are ignored, so you can use them to make your expression easier to read while you write them.

#### Operations

List of operations:
* addition, symbolised by +
* substation, symbolised by -
* multiplication, symbolised by \* or x
* division, symbolised by /
* exponentiation, symbolised by ^ or \*\*

For operation with more than one symbol such as multiplication you can use each one in the same expression.

#### Number

Number are typed in the decimal system. You can use decimal numbers.

#### Parenthesis

To compute a sub-expression first you can put it between parentheses. You can use `(`, `{` or `[` to open parenthesis and `)`, `}` or `]` to close them.

#### functions

You can apply function on numbers or expressions.
To do that you have to write the name of the function then it's attributes between parentheses, bracket or curly brackets.
List of functions:
* abs(x) : return the absolute value of x
* floor(x) : return x rounded down
* ceil(x) : return x rounded up
* max(x,y) : return the biggest number between x and y
* min(x,y) : return the smallest number between x and y

### Examples

Here is a list of some valid expressions:
* ( 3 * (4.0 + 0.01)) ^3
* { (3+2) * 4\*\*2 x 6 - 5 ]^0.3
* 3 \*\* (4 - 5)
* 4\*10\*\*1+2x10^0
* 2\*\*max(4,abs(-76\*2))
* abs(floor(-3.5)\*3)

