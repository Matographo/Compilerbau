/**
 * @file Nice C to CMa Parser
 * @authors Leofanamed & Michael Jordan
 * @license MIT
 */

/// <reference types="tree-sitter-cli/dsl" />
// @ts-check

module.exports = grammar({
  name: "cma",

  rules: {
    source_file: ($) => repeat($.instruction),

    // instructions are separated into two:
    // 1. instructions without operands ('halt', 'add', etc.)
    // 2. instructions with operands (loadc 5', 'jump 10', etc.)
    // it makes the grammar clearer, to ensure correct pasring,
    // also produces an intuitive abstarct syntax tree

    instruction: ($) =>
      choice(
        $.simple_instruction,
        $.instruction_with_operand
      ),

    // without operands
    simple_instruction: ($) =>
      choice(
        "load", "store", "add", "sub", "mul", "div", "mod",
        "and", "or", "xor", "not", "eq", "neq", "le", "leq",
        "gr", "geq", "return", "halt", "pop", "dup", "new", "mark"
      ),

    instruction_with_operand: ($) =>
      seq($.mnemonic_with_operand, $.number),

    mnemonic_with_operand: ($) =>
      choice(
        "loadc", "jump", "jumpz", "call", "alloc", "loada",
        "storea", "move", "enter", "loadrc", "loadr", "storer"
      ),

    number: ($) => /-?\d+/,

    comment: ($) => token(seq("//", /.*/)),

    extras: ($) => [
      token(/\s+/),  // handles whitespace as token
      $.comment
    ],
  },
});
