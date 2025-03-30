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

    toplevel: ($) =>
      choice($.function_definition, $.gdeclaration, $.struct_definition),

    // Top-level statements
    statement: ($) =>
      choice(
        $.ldeclaration,
        $.assignment,
        $.expression_statement,
        $.return_statement,
        $.if_statement,
        $.while_statement,
        $.for_statement
      ),

    gdeclaration: ($) => $.declaration,

    ldeclaration: ($) => $.declaration,

    // int x = 5;
    declaration: ($) =>
      seq(
        field("type", $.type),
        optional("*"),
        field("name", $.identifier),
        optional(seq("=", field("value", $.expression))),
        ";"
      ),

    // x = 5;
    assignment: ($) =>
      seq(field("name", $.identifier), "=", field("value", $.expression), ";"),

    // function(); or (a + b);
    expression_statement: ($) => seq($.expression, ";"),

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
        field("name", $.identifier),
        "(",
        optional(commaSep($.expression)),
        ")"
      ),

    function_definition: ($) =>
      seq(
        field("return_type", $.type),
        field("name", $.identifier),
        "(",
        optional($.parameter_list),
        ")",
        $.block
      ),

    parameter_list: ($) => commaSep(seq($.type, optional("*"), $.identifier)),

    block: ($) => seq("{", repeat($.statement), "}"),

    return_statement: ($) => seq("return", optional($.expression), ";"),

    if_statement: ($) =>
      seq(
        "if",
        "(",
        $.expression,
        ")",
        $.block,
        optional(seq("else", choice($.block, $.if_statement)))
      ),

    while_statement: ($) => seq("while", "(", $.expression, ")", $.block),

    for_statement: ($) =>
      seq(
        "for",
        "(",
        optional($.assignment),
        ";",
        optional($.expression),
        ";",
        optional($.expression),
        ")",
        $.block
      ),

    struct_definition: ($) =>
      seq("struct", $.identifier, "{", repeat($.declaration), "}", ";"),

    // a.b
    struct_access: ($) => seq($.identifier, ".", $.identifier),

    // a[5] or 5[a]
    array_access: ($) => seq($.expression, "[", $.expression, "]"),

    // *i
    pointer_access: ($) => prec.left(1, seq("*", $.identifier)),

    // (a + b)
    bracket_expression: ($) => seq("(", $.expression, ")"),

    binary_expression: ($) =>
      prec.left(
        seq(
          $.expression,
          choice(
            "+",
            "-",
            "*",
            "/",
            "%",
            "<",
            ">",
            "<=",
            ">=",
            "==",
            "!=",
            "&&",
            "||"
          ),
          $.expression
        )
      ),

    unary_expression: ($) =>
      choice(
        prec(1, seq(choice("!", "-", "*"), $.expression)),
        prec(2, seq(choice("++", "--"), $.identifier)),
        prec(2, seq($.identifier, choice("++", "--")))
      ),

    type: ($) => "int",

    identifier: ($) => /[a-zA-Z_]+[a-zA-Z0-9_]*/,

    number: ($) => /\d+/,
  },
});

// Helper for comma-separated lists
function commaSep(rule) {
  return seq(rule, optional(repeat(seq(",", rule))));
}
