/**
 * @file Nice C to CMa Parser
 * @author Leofanamed
 * @license MIT
 */

/// <reference types="tree-sitter-cli/dsl" />
// @ts-check

module.exports = grammar({
  name: "c",

  rules: {
    // TODO: add the actual grammar rules
    source_file: ($) => repeat($.statement),

    statement: ($) => 
      choice(
        $.declaration,
        $.assignment
      ),

    assignment: ($) =>
      seq(
        $.identifier,
        "=",
        $.expression,
        ";"
      ),

    declaration: ($) =>
      choice(
        seq(
          "int",
          $.identifier,
          "=",
          $.expression,
          ";"
        ),
        seq(
          "int",
          $.identifier,
          ";"
        )
      ),

    expression: ($) =>
      choice(
        $.identifier,
        $.number,
        $.brecket_expression,
        $.binary_expression
      ),

    declararation: ($) =>
      seq(
        "int",
        $.identifier,
        "=",
        $.expression,
        ";"
      ),

    identifier: ($) => /[a-zA-Z_][a-zA-Z0-9_]*/,

    number: ($) => /\d+/,
    
    brecket_expression: ($) =>
      seq(
        "(",
        $.expression,
        ")"
      ),

    binary_expression: ($) =>
      prec.left(
      seq(
        $.expression,
        choice("+", "-", "*", "/", "%"),
        $.expression
      )),
  }
});
