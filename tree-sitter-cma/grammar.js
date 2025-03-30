/**
 * @file Nice C to CMa Parser
 * @authors Leofanamed & Michael Jordan
 * @license MIT
 */

/// <reference types="tree-sitter-cli/dsl" />
// @ts-check

module.exports = grammar({
  name: "c",

  rules: {
    source_file: ($) => repeat($.statement),

    // Top-level statements
    statement: ($) => choice(
      $.declaration,
      $.assignment,
      $.expression_statement,
      $.function_definition,
      $.return_statement,
      $.if_statement,
      $.while_statement,
      $.for_statement,
      $.struct_definition
    ),

    // int x = 5;
    declaration: ($) =>
      seq(
        field('type', $.type),
        optional('*'),
        field('name', $.identifier),
        optional(seq('=', field('value', $.expression))),
        ';'
      ),

    // x = 5;
    assignment: ($) =>
      seq(
        field('name', $.identifier),
        '=',
        field('value', $.expression),
        ';'
      ),

    // function(); or (a + b);
    expression_statement: ($) =>
      seq($.expression, ';'),

    // Expressions: numbers, vars, math, function calls, usw.
    expression: ($) =>
      choice(
        $.number,
        $.identifier,
        $.function_call,
        $.binary_expression,
        $.unary_expression,
        $.bracket_expression,
        $.array_access,
        $.struct_access,
        $.pointer_access
      ),

    function_call: ($) =>
      seq(
        field('name', $.identifier),
        '(',
        optional(commaSep($.expression)),
        ')'
      ),

    function_definition: ($) =>
      seq(
        field('return_type', $.type),
        field('name', $.identifier),
        '(',
        optional($.parameter_list),
        ')',
        $.block
      ),

    parameter_list: ($) =>
      commaSep(seq($.type, optional('*'), $.identifier)),

    block: ($) =>
      seq('{', repeat($.statement), '}'),

    return_statement: ($) =>
      seq('return', optional($.expression), ';'),

    if_statement: ($) =>
      seq(
        'if', '(', $.expression, ')',
        $.block,
        optional(seq('else', choice($.block, $.if_statement)))
      ),

    while_statement: ($) =>
      seq('while', '(', $.expression, ')', $.block),

    for_statement: ($) =>
      seq(
        'for', '(', optional($.assignment), optional($.expression), ';', optional($.expression), ')',
        $.block
      ),

    struct_definition: ($) =>
      seq(
        'struct',
        $.identifier,
        '{',
        repeat($.declaration),
        '}',
        ';'
      ),

    // a.b
    struct_access: ($) =>
      seq($.identifier, '.', $.identifier),

    // a[5] or 5[a]
    array_access: ($) =>
      choice(
        seq($.identifier, '[', $.expression, ']'),
        seq($.expression, '[', $.identifier, ']')
      ),

    // *i
    pointer_access: ($) =>
      seq('*', $.identifier),

    // (a + b)
    bracket_expression: ($) =>
      seq('(', $.expression, ')'),

    binary_expression: ($) =>
      prec.left(seq(
        $.expression,
        choice('+', '-', '*', '/', '%', '<', '>', '<=', '>=', '==', '!=', '&&', '||'),
        $.expression
      )),

    unary_expression: ($) =>
      prec(1, seq(
        choice('!', '-', '*'),
        $.expression
      )),

    type: ($) =>
      choice('int', 'float', 'void', 'char', 'bool', 'struct'),

    identifier: ($) => /[a-zA-Z_]\w*/,

    number: ($) => /\d+/,
  }
});

// Helper for comma-separated lists
function commaSep(rule) {
  return seq(rule, repeat(seq(',', rule)));
}