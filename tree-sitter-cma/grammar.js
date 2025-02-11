/**
 * @file Nice C to CMa Parser
 * @author Leofanamed
 * @license MIT
 */

/// <reference types="tree-sitter-cli/dsl" />
// @ts-check

module.exports = grammar({
  name: "cma",

  rules: {
    // TODO: add the actual grammar rules
    source_file: ($) => repeat($.instruction),

    tokens: ($) =>
      choice(
        "load",
        seq("loadc", $.number),
        "store",
        "add",
        "sub",
        "mul",
        "div",
        "mod",
        "and",
        "or",
        "xor",
        "not",
        "eq",
        "neq",
        "le",
        "leq",
        "gr",
        "geq",
        seq("jump", $.number),
        seq("jumpz", $.number),
        seq("call", $.number),
        "return",
        "halt",
        seq("alloc", $.number),
        "pop",
        seq("loada", $.number),
        seq("storea", $.number),
        "dup",
        "new",
        "mark",
        seq("move", $.number),
        seq("enter", $.number),
        seq("loadrc", $.number),
        seq("loadr", $.number),
        seq("storer", $.number)
      ),

    instruction: ($) => choice(seq($.tokens, $.number)),

    identifier: ($) => /[a-z]+/,

    number: ($) => /[-]?\d+/,
  },
});
