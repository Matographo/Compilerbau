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
    source_file: ($) => repeat($.toplevel),

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
      choice(
        seq($.type, $.identifier, optional(seq("=", $.expression)), ";"),
        seq($.type, "*", $.identifier, ";"),
        seq($.type, $.identifier, "[", $.expression, "]", ";")
      ),

    lvalue: ($) =>
      choice($.identifier, $.array_access, $.pointer_access, $.struct_access),

    // x = 5;
    assignment: ($) => seq($.lvalue, "=", $.expression, ";"),

    assignment_id: ($) => choice(
      $.identifier,
      $.array_access,
      $.struct_access,
      $.pointer_access
    ),

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
      seq($.identifier, "(", optional(commaSep($.expression)), ")"),

    function_definition: ($) =>
      seq($.type, $.identifier, "(", optional($.parameter_list), ")", $.block),

    parameter_list: ($) => commaSep(seq($.type, optional("*"), $.identifier)),

    block: ($) => seq("{", repeat($.statement), "}"),

    loopBlock: ($) =>
      seq("{", repeat(choice($.statement, prec(5, $.break_statement))), "}"),

    return_statement: ($) => seq("return", optional($.expression), ";"),

    if_statement: ($) =>
      seq(
        "if",
        "(",
        $.expression,
        ")",
        $.block,
        optional(repeat(seq("else", "if", "(", $.expression, ")", $.block))),
        optional(choice(seq("else", $.block)))
      ),

    while_statement: ($) => seq("while", "(", $.expression, ")", $.loopBlock),

    for_statement: ($) =>
      seq(
        "for",
        "(",
        optional(choice($.ldeclaration, $.assignment)),
        ";",
        $.expression,
        ";",
        optional($.expression),
        ")",
        $.loopBlock,
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

    break_statement: ($) => seq("break", ";"),

    unary_expression: ($) =>
      choice(
        prec(1, seq(choice("!", "-", "*"), $.expression)),
        prec(2, seq(choice("++", "--"), $.identifier)),
        prec(2, seq($.identifier, choice("++", "--")))
      ),

    type: ($) => choice("int", "void"),

    identifier: ($) => /[a-zA-Z_]+[a-zA-Z0-9_]*/,

    number: ($) => /\d+/,
  },
});

// Helper for comma-separated lists
function commaSep(rule) {
  return seq(rule, optional(repeat(seq(",", rule))));
}
