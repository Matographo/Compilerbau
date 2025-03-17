#include "tree_sitter/parser.h"

#if defined(__GNUC__) || defined(__clang__)
#pragma GCC diagnostic ignored "-Wmissing-field-initializers"
#endif

#define LANGUAGE_VERSION 14
#define STATE_COUNT 10
#define LARGE_STATE_COUNT 7
#define SYMBOL_COUNT 44
#define ALIAS_COUNT 0
#define TOKEN_COUNT 38
#define EXTERNAL_TOKEN_COUNT 0
#define FIELD_COUNT 0
#define MAX_ALIAS_SEQUENCE_LENGTH 2
#define PRODUCTION_ID_COUNT 1

enum ts_symbol_identifiers {
  anon_sym_load = 1,
  anon_sym_store = 2,
  anon_sym_add = 3,
  anon_sym_sub = 4,
  anon_sym_mul = 5,
  anon_sym_div = 6,
  anon_sym_mod = 7,
  anon_sym_and = 8,
  anon_sym_or = 9,
  anon_sym_xor = 10,
  anon_sym_not = 11,
  anon_sym_eq = 12,
  anon_sym_neq = 13,
  anon_sym_le = 14,
  anon_sym_leq = 15,
  anon_sym_gr = 16,
  anon_sym_geq = 17,
  anon_sym_return = 18,
  anon_sym_halt = 19,
  anon_sym_pop = 20,
  anon_sym_dup = 21,
  anon_sym_new = 22,
  anon_sym_mark = 23,
  anon_sym_loadc = 24,
  anon_sym_jump = 25,
  anon_sym_jumpz = 26,
  anon_sym_call = 27,
  anon_sym_alloc = 28,
  anon_sym_loada = 29,
  anon_sym_storea = 30,
  anon_sym_move = 31,
  anon_sym_enter = 32,
  anon_sym_loadrc = 33,
  anon_sym_loadr = 34,
  anon_sym_storer = 35,
  sym_number = 36,
  sym_comment = 37,
  sym_source_file = 38,
  sym_instruction = 39,
  sym_simple_instruction = 40,
  sym_instruction_with_operand = 41,
  sym_mnemonic_with_operand = 42,
  aux_sym_source_file_repeat1 = 43,
};

static const char * const ts_symbol_names[] = {
  [ts_builtin_sym_end] = "end",
  [anon_sym_load] = "load",
  [anon_sym_store] = "store",
  [anon_sym_add] = "add",
  [anon_sym_sub] = "sub",
  [anon_sym_mul] = "mul",
  [anon_sym_div] = "div",
  [anon_sym_mod] = "mod",
  [anon_sym_and] = "and",
  [anon_sym_or] = "or",
  [anon_sym_xor] = "xor",
  [anon_sym_not] = "not",
  [anon_sym_eq] = "eq",
  [anon_sym_neq] = "neq",
  [anon_sym_le] = "le",
  [anon_sym_leq] = "leq",
  [anon_sym_gr] = "gr",
  [anon_sym_geq] = "geq",
  [anon_sym_return] = "return",
  [anon_sym_halt] = "halt",
  [anon_sym_pop] = "pop",
  [anon_sym_dup] = "dup",
  [anon_sym_new] = "new",
  [anon_sym_mark] = "mark",
  [anon_sym_loadc] = "loadc",
  [anon_sym_jump] = "jump",
  [anon_sym_jumpz] = "jumpz",
  [anon_sym_call] = "call",
  [anon_sym_alloc] = "alloc",
  [anon_sym_loada] = "loada",
  [anon_sym_storea] = "storea",
  [anon_sym_move] = "move",
  [anon_sym_enter] = "enter",
  [anon_sym_loadrc] = "loadrc",
  [anon_sym_loadr] = "loadr",
  [anon_sym_storer] = "storer",
  [sym_number] = "number",
  [sym_comment] = "comment",
  [sym_source_file] = "source_file",
  [sym_instruction] = "instruction",
  [sym_simple_instruction] = "simple_instruction",
  [sym_instruction_with_operand] = "instruction_with_operand",
  [sym_mnemonic_with_operand] = "mnemonic_with_operand",
  [aux_sym_source_file_repeat1] = "source_file_repeat1",
};

static const TSSymbol ts_symbol_map[] = {
  [ts_builtin_sym_end] = ts_builtin_sym_end,
  [anon_sym_load] = anon_sym_load,
  [anon_sym_store] = anon_sym_store,
  [anon_sym_add] = anon_sym_add,
  [anon_sym_sub] = anon_sym_sub,
  [anon_sym_mul] = anon_sym_mul,
  [anon_sym_div] = anon_sym_div,
  [anon_sym_mod] = anon_sym_mod,
  [anon_sym_and] = anon_sym_and,
  [anon_sym_or] = anon_sym_or,
  [anon_sym_xor] = anon_sym_xor,
  [anon_sym_not] = anon_sym_not,
  [anon_sym_eq] = anon_sym_eq,
  [anon_sym_neq] = anon_sym_neq,
  [anon_sym_le] = anon_sym_le,
  [anon_sym_leq] = anon_sym_leq,
  [anon_sym_gr] = anon_sym_gr,
  [anon_sym_geq] = anon_sym_geq,
  [anon_sym_return] = anon_sym_return,
  [anon_sym_halt] = anon_sym_halt,
  [anon_sym_pop] = anon_sym_pop,
  [anon_sym_dup] = anon_sym_dup,
  [anon_sym_new] = anon_sym_new,
  [anon_sym_mark] = anon_sym_mark,
  [anon_sym_loadc] = anon_sym_loadc,
  [anon_sym_jump] = anon_sym_jump,
  [anon_sym_jumpz] = anon_sym_jumpz,
  [anon_sym_call] = anon_sym_call,
  [anon_sym_alloc] = anon_sym_alloc,
  [anon_sym_loada] = anon_sym_loada,
  [anon_sym_storea] = anon_sym_storea,
  [anon_sym_move] = anon_sym_move,
  [anon_sym_enter] = anon_sym_enter,
  [anon_sym_loadrc] = anon_sym_loadrc,
  [anon_sym_loadr] = anon_sym_loadr,
  [anon_sym_storer] = anon_sym_storer,
  [sym_number] = sym_number,
  [sym_comment] = sym_comment,
  [sym_source_file] = sym_source_file,
  [sym_instruction] = sym_instruction,
  [sym_simple_instruction] = sym_simple_instruction,
  [sym_instruction_with_operand] = sym_instruction_with_operand,
  [sym_mnemonic_with_operand] = sym_mnemonic_with_operand,
  [aux_sym_source_file_repeat1] = aux_sym_source_file_repeat1,
};

static const TSSymbolMetadata ts_symbol_metadata[] = {
  [ts_builtin_sym_end] = {
    .visible = false,
    .named = true,
  },
  [anon_sym_load] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_store] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_add] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_sub] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_mul] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_div] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_mod] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_and] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_or] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_xor] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_not] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_eq] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_neq] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_le] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_leq] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_gr] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_geq] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_return] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_halt] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_pop] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_dup] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_new] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_mark] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_loadc] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_jump] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_jumpz] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_call] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_alloc] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_loada] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_storea] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_move] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_enter] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_loadrc] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_loadr] = {
    .visible = true,
    .named = false,
  },
  [anon_sym_storer] = {
    .visible = true,
    .named = false,
  },
  [sym_number] = {
    .visible = true,
    .named = true,
  },
  [sym_comment] = {
    .visible = true,
    .named = true,
  },
  [sym_source_file] = {
    .visible = true,
    .named = true,
  },
  [sym_instruction] = {
    .visible = true,
    .named = true,
  },
  [sym_simple_instruction] = {
    .visible = true,
    .named = true,
  },
  [sym_instruction_with_operand] = {
    .visible = true,
    .named = true,
  },
  [sym_mnemonic_with_operand] = {
    .visible = true,
    .named = true,
  },
  [aux_sym_source_file_repeat1] = {
    .visible = false,
    .named = false,
  },
};

static const TSSymbol ts_alias_sequences[PRODUCTION_ID_COUNT][MAX_ALIAS_SEQUENCE_LENGTH] = {
  [0] = {0},
};

static const uint16_t ts_non_terminal_alias_map[] = {
  0,
};

static const TSStateId ts_primary_state_ids[STATE_COUNT] = {
  [0] = 0,
  [1] = 1,
  [2] = 2,
  [3] = 3,
  [4] = 4,
  [5] = 5,
  [6] = 6,
  [7] = 7,
  [8] = 8,
  [9] = 9,
};

static bool ts_lex(TSLexer *lexer, TSStateId state) {
  START_LEXER();
  eof = lexer->eof(lexer);
  switch (state) {
    case 0:
      if (eof) ADVANCE(54);
      ADVANCE_MAP(
        '-', 53,
        '/', 1,
        'a', 8,
        'c', 3,
        'd', 20,
        'e', 28,
        'g', 13,
        'h', 5,
        'j', 50,
        'l', 14,
        'm', 2,
        'n', 15,
        'o', 39,
        'p', 31,
        'r', 18,
        's', 49,
        'x', 32,
      );
      if (('\t' <= lookahead && lookahead <= '\r') ||
          lookahead == ' ') SKIP(0);
      if (('0' <= lookahead && lookahead <= '9')) ADVANCE(90);
      END_STATE();
    case 1:
      if (lookahead == '/') ADVANCE(91);
      END_STATE();
    case 2:
      if (lookahead == 'a') ADVANCE(40);
      if (lookahead == 'o') ADVANCE(11);
      if (lookahead == 'u') ADVANCE(22);
      END_STATE();
    case 3:
      if (lookahead == 'a') ADVANCE(26);
      END_STATE();
    case 4:
      if (lookahead == 'a') ADVANCE(12);
      END_STATE();
    case 5:
      if (lookahead == 'a') ADVANCE(25);
      END_STATE();
    case 6:
      if (lookahead == 'b') ADVANCE(58);
      END_STATE();
    case 7:
      if (lookahead == 'c') ADVANCE(82);
      END_STATE();
    case 8:
      if (lookahead == 'd') ADVANCE(9);
      if (lookahead == 'l') ADVANCE(24);
      if (lookahead == 'n') ADVANCE(10);
      END_STATE();
    case 9:
      if (lookahead == 'd') ADVANCE(57);
      END_STATE();
    case 10:
      if (lookahead == 'd') ADVANCE(62);
      END_STATE();
    case 11:
      if (lookahead == 'd') ADVANCE(61);
      if (lookahead == 'v') ADVANCE(16);
      END_STATE();
    case 12:
      if (lookahead == 'd') ADVANCE(55);
      END_STATE();
    case 13:
      if (lookahead == 'e') ADVANCE(37);
      if (lookahead == 'r') ADVANCE(70);
      END_STATE();
    case 14:
      if (lookahead == 'e') ADVANCE(68);
      if (lookahead == 'o') ADVANCE(4);
      END_STATE();
    case 15:
      if (lookahead == 'e') ADVANCE(38);
      if (lookahead == 'o') ADVANCE(45);
      END_STATE();
    case 16:
      if (lookahead == 'e') ADVANCE(85);
      END_STATE();
    case 17:
      if (lookahead == 'e') ADVANCE(56);
      END_STATE();
    case 18:
      if (lookahead == 'e') ADVANCE(47);
      END_STATE();
    case 19:
      if (lookahead == 'e') ADVANCE(42);
      END_STATE();
    case 20:
      if (lookahead == 'i') ADVANCE(52);
      if (lookahead == 'u') ADVANCE(34);
      END_STATE();
    case 21:
      if (lookahead == 'k') ADVANCE(77);
      END_STATE();
    case 22:
      if (lookahead == 'l') ADVANCE(59);
      END_STATE();
    case 23:
      if (lookahead == 'l') ADVANCE(81);
      END_STATE();
    case 24:
      if (lookahead == 'l') ADVANCE(30);
      END_STATE();
    case 25:
      if (lookahead == 'l') ADVANCE(46);
      END_STATE();
    case 26:
      if (lookahead == 'l') ADVANCE(23);
      END_STATE();
    case 27:
      if (lookahead == 'm') ADVANCE(36);
      END_STATE();
    case 28:
      if (lookahead == 'n') ADVANCE(48);
      if (lookahead == 'q') ADVANCE(66);
      END_STATE();
    case 29:
      if (lookahead == 'n') ADVANCE(72);
      END_STATE();
    case 30:
      if (lookahead == 'o') ADVANCE(7);
      END_STATE();
    case 31:
      if (lookahead == 'o') ADVANCE(35);
      END_STATE();
    case 32:
      if (lookahead == 'o') ADVANCE(41);
      END_STATE();
    case 33:
      if (lookahead == 'o') ADVANCE(44);
      END_STATE();
    case 34:
      if (lookahead == 'p') ADVANCE(75);
      END_STATE();
    case 35:
      if (lookahead == 'p') ADVANCE(74);
      END_STATE();
    case 36:
      if (lookahead == 'p') ADVANCE(79);
      END_STATE();
    case 37:
      if (lookahead == 'q') ADVANCE(71);
      END_STATE();
    case 38:
      if (lookahead == 'q') ADVANCE(67);
      if (lookahead == 'w') ADVANCE(76);
      END_STATE();
    case 39:
      if (lookahead == 'r') ADVANCE(63);
      END_STATE();
    case 40:
      if (lookahead == 'r') ADVANCE(21);
      END_STATE();
    case 41:
      if (lookahead == 'r') ADVANCE(64);
      END_STATE();
    case 42:
      if (lookahead == 'r') ADVANCE(86);
      END_STATE();
    case 43:
      if (lookahead == 'r') ADVANCE(29);
      END_STATE();
    case 44:
      if (lookahead == 'r') ADVANCE(17);
      END_STATE();
    case 45:
      if (lookahead == 't') ADVANCE(65);
      END_STATE();
    case 46:
      if (lookahead == 't') ADVANCE(73);
      END_STATE();
    case 47:
      if (lookahead == 't') ADVANCE(51);
      END_STATE();
    case 48:
      if (lookahead == 't') ADVANCE(19);
      END_STATE();
    case 49:
      if (lookahead == 't') ADVANCE(33);
      if (lookahead == 'u') ADVANCE(6);
      END_STATE();
    case 50:
      if (lookahead == 'u') ADVANCE(27);
      END_STATE();
    case 51:
      if (lookahead == 'u') ADVANCE(43);
      END_STATE();
    case 52:
      if (lookahead == 'v') ADVANCE(60);
      END_STATE();
    case 53:
      if (('0' <= lookahead && lookahead <= '9')) ADVANCE(90);
      END_STATE();
    case 54:
      ACCEPT_TOKEN(ts_builtin_sym_end);
      END_STATE();
    case 55:
      ACCEPT_TOKEN(anon_sym_load);
      if (lookahead == 'a') ADVANCE(83);
      if (lookahead == 'c') ADVANCE(78);
      if (lookahead == 'r') ADVANCE(88);
      END_STATE();
    case 56:
      ACCEPT_TOKEN(anon_sym_store);
      if (lookahead == 'a') ADVANCE(84);
      if (lookahead == 'r') ADVANCE(89);
      END_STATE();
    case 57:
      ACCEPT_TOKEN(anon_sym_add);
      END_STATE();
    case 58:
      ACCEPT_TOKEN(anon_sym_sub);
      END_STATE();
    case 59:
      ACCEPT_TOKEN(anon_sym_mul);
      END_STATE();
    case 60:
      ACCEPT_TOKEN(anon_sym_div);
      END_STATE();
    case 61:
      ACCEPT_TOKEN(anon_sym_mod);
      END_STATE();
    case 62:
      ACCEPT_TOKEN(anon_sym_and);
      END_STATE();
    case 63:
      ACCEPT_TOKEN(anon_sym_or);
      END_STATE();
    case 64:
      ACCEPT_TOKEN(anon_sym_xor);
      END_STATE();
    case 65:
      ACCEPT_TOKEN(anon_sym_not);
      END_STATE();
    case 66:
      ACCEPT_TOKEN(anon_sym_eq);
      END_STATE();
    case 67:
      ACCEPT_TOKEN(anon_sym_neq);
      END_STATE();
    case 68:
      ACCEPT_TOKEN(anon_sym_le);
      if (lookahead == 'q') ADVANCE(69);
      END_STATE();
    case 69:
      ACCEPT_TOKEN(anon_sym_leq);
      END_STATE();
    case 70:
      ACCEPT_TOKEN(anon_sym_gr);
      END_STATE();
    case 71:
      ACCEPT_TOKEN(anon_sym_geq);
      END_STATE();
    case 72:
      ACCEPT_TOKEN(anon_sym_return);
      END_STATE();
    case 73:
      ACCEPT_TOKEN(anon_sym_halt);
      END_STATE();
    case 74:
      ACCEPT_TOKEN(anon_sym_pop);
      END_STATE();
    case 75:
      ACCEPT_TOKEN(anon_sym_dup);
      END_STATE();
    case 76:
      ACCEPT_TOKEN(anon_sym_new);
      END_STATE();
    case 77:
      ACCEPT_TOKEN(anon_sym_mark);
      END_STATE();
    case 78:
      ACCEPT_TOKEN(anon_sym_loadc);
      END_STATE();
    case 79:
      ACCEPT_TOKEN(anon_sym_jump);
      if (lookahead == 'z') ADVANCE(80);
      END_STATE();
    case 80:
      ACCEPT_TOKEN(anon_sym_jumpz);
      END_STATE();
    case 81:
      ACCEPT_TOKEN(anon_sym_call);
      END_STATE();
    case 82:
      ACCEPT_TOKEN(anon_sym_alloc);
      END_STATE();
    case 83:
      ACCEPT_TOKEN(anon_sym_loada);
      END_STATE();
    case 84:
      ACCEPT_TOKEN(anon_sym_storea);
      END_STATE();
    case 85:
      ACCEPT_TOKEN(anon_sym_move);
      END_STATE();
    case 86:
      ACCEPT_TOKEN(anon_sym_enter);
      END_STATE();
    case 87:
      ACCEPT_TOKEN(anon_sym_loadrc);
      END_STATE();
    case 88:
      ACCEPT_TOKEN(anon_sym_loadr);
      if (lookahead == 'c') ADVANCE(87);
      END_STATE();
    case 89:
      ACCEPT_TOKEN(anon_sym_storer);
      END_STATE();
    case 90:
      ACCEPT_TOKEN(sym_number);
      if (('0' <= lookahead && lookahead <= '9')) ADVANCE(90);
      END_STATE();
    case 91:
      ACCEPT_TOKEN(sym_comment);
      if (lookahead != 0 &&
          lookahead != '\n') ADVANCE(91);
      END_STATE();
    default:
      return false;
  }
}

static const TSLexMode ts_lex_modes[STATE_COUNT] = {
  [0] = {.lex_state = 0},
  [1] = {.lex_state = 0},
  [2] = {.lex_state = 0},
  [3] = {.lex_state = 0},
  [4] = {.lex_state = 0},
  [5] = {.lex_state = 0},
  [6] = {.lex_state = 0},
  [7] = {.lex_state = 0},
  [8] = {.lex_state = 0},
  [9] = {.lex_state = 0},
};

static const uint16_t ts_parse_table[LARGE_STATE_COUNT][SYMBOL_COUNT] = {
  [0] = {
    [ts_builtin_sym_end] = ACTIONS(1),
    [anon_sym_load] = ACTIONS(1),
    [anon_sym_store] = ACTIONS(1),
    [anon_sym_add] = ACTIONS(1),
    [anon_sym_sub] = ACTIONS(1),
    [anon_sym_mul] = ACTIONS(1),
    [anon_sym_div] = ACTIONS(1),
    [anon_sym_mod] = ACTIONS(1),
    [anon_sym_and] = ACTIONS(1),
    [anon_sym_or] = ACTIONS(1),
    [anon_sym_xor] = ACTIONS(1),
    [anon_sym_not] = ACTIONS(1),
    [anon_sym_eq] = ACTIONS(1),
    [anon_sym_neq] = ACTIONS(1),
    [anon_sym_le] = ACTIONS(1),
    [anon_sym_leq] = ACTIONS(1),
    [anon_sym_gr] = ACTIONS(1),
    [anon_sym_geq] = ACTIONS(1),
    [anon_sym_return] = ACTIONS(1),
    [anon_sym_halt] = ACTIONS(1),
    [anon_sym_pop] = ACTIONS(1),
    [anon_sym_dup] = ACTIONS(1),
    [anon_sym_new] = ACTIONS(1),
    [anon_sym_mark] = ACTIONS(1),
    [anon_sym_loadc] = ACTIONS(1),
    [anon_sym_jump] = ACTIONS(1),
    [anon_sym_jumpz] = ACTIONS(1),
    [anon_sym_call] = ACTIONS(1),
    [anon_sym_alloc] = ACTIONS(1),
    [anon_sym_loada] = ACTIONS(1),
    [anon_sym_storea] = ACTIONS(1),
    [anon_sym_move] = ACTIONS(1),
    [anon_sym_enter] = ACTIONS(1),
    [anon_sym_loadrc] = ACTIONS(1),
    [anon_sym_loadr] = ACTIONS(1),
    [anon_sym_storer] = ACTIONS(1),
    [sym_number] = ACTIONS(1),
    [sym_comment] = ACTIONS(3),
  },
  [1] = {
    [sym_source_file] = STATE(8),
    [sym_instruction] = STATE(2),
    [sym_simple_instruction] = STATE(5),
    [sym_instruction_with_operand] = STATE(5),
    [sym_mnemonic_with_operand] = STATE(9),
    [aux_sym_source_file_repeat1] = STATE(2),
    [ts_builtin_sym_end] = ACTIONS(5),
    [anon_sym_load] = ACTIONS(7),
    [anon_sym_store] = ACTIONS(7),
    [anon_sym_add] = ACTIONS(9),
    [anon_sym_sub] = ACTIONS(9),
    [anon_sym_mul] = ACTIONS(9),
    [anon_sym_div] = ACTIONS(9),
    [anon_sym_mod] = ACTIONS(9),
    [anon_sym_and] = ACTIONS(9),
    [anon_sym_or] = ACTIONS(9),
    [anon_sym_xor] = ACTIONS(9),
    [anon_sym_not] = ACTIONS(9),
    [anon_sym_eq] = ACTIONS(9),
    [anon_sym_neq] = ACTIONS(9),
    [anon_sym_le] = ACTIONS(7),
    [anon_sym_leq] = ACTIONS(9),
    [anon_sym_gr] = ACTIONS(9),
    [anon_sym_geq] = ACTIONS(9),
    [anon_sym_return] = ACTIONS(9),
    [anon_sym_halt] = ACTIONS(9),
    [anon_sym_pop] = ACTIONS(9),
    [anon_sym_dup] = ACTIONS(9),
    [anon_sym_new] = ACTIONS(9),
    [anon_sym_mark] = ACTIONS(9),
    [anon_sym_loadc] = ACTIONS(11),
    [anon_sym_jump] = ACTIONS(13),
    [anon_sym_jumpz] = ACTIONS(11),
    [anon_sym_call] = ACTIONS(11),
    [anon_sym_alloc] = ACTIONS(11),
    [anon_sym_loada] = ACTIONS(11),
    [anon_sym_storea] = ACTIONS(11),
    [anon_sym_move] = ACTIONS(11),
    [anon_sym_enter] = ACTIONS(11),
    [anon_sym_loadrc] = ACTIONS(11),
    [anon_sym_loadr] = ACTIONS(13),
    [anon_sym_storer] = ACTIONS(11),
    [sym_comment] = ACTIONS(3),
  },
  [2] = {
    [sym_instruction] = STATE(3),
    [sym_simple_instruction] = STATE(5),
    [sym_instruction_with_operand] = STATE(5),
    [sym_mnemonic_with_operand] = STATE(9),
    [aux_sym_source_file_repeat1] = STATE(3),
    [ts_builtin_sym_end] = ACTIONS(15),
    [anon_sym_load] = ACTIONS(7),
    [anon_sym_store] = ACTIONS(7),
    [anon_sym_add] = ACTIONS(9),
    [anon_sym_sub] = ACTIONS(9),
    [anon_sym_mul] = ACTIONS(9),
    [anon_sym_div] = ACTIONS(9),
    [anon_sym_mod] = ACTIONS(9),
    [anon_sym_and] = ACTIONS(9),
    [anon_sym_or] = ACTIONS(9),
    [anon_sym_xor] = ACTIONS(9),
    [anon_sym_not] = ACTIONS(9),
    [anon_sym_eq] = ACTIONS(9),
    [anon_sym_neq] = ACTIONS(9),
    [anon_sym_le] = ACTIONS(7),
    [anon_sym_leq] = ACTIONS(9),
    [anon_sym_gr] = ACTIONS(9),
    [anon_sym_geq] = ACTIONS(9),
    [anon_sym_return] = ACTIONS(9),
    [anon_sym_halt] = ACTIONS(9),
    [anon_sym_pop] = ACTIONS(9),
    [anon_sym_dup] = ACTIONS(9),
    [anon_sym_new] = ACTIONS(9),
    [anon_sym_mark] = ACTIONS(9),
    [anon_sym_loadc] = ACTIONS(11),
    [anon_sym_jump] = ACTIONS(13),
    [anon_sym_jumpz] = ACTIONS(11),
    [anon_sym_call] = ACTIONS(11),
    [anon_sym_alloc] = ACTIONS(11),
    [anon_sym_loada] = ACTIONS(11),
    [anon_sym_storea] = ACTIONS(11),
    [anon_sym_move] = ACTIONS(11),
    [anon_sym_enter] = ACTIONS(11),
    [anon_sym_loadrc] = ACTIONS(11),
    [anon_sym_loadr] = ACTIONS(13),
    [anon_sym_storer] = ACTIONS(11),
    [sym_comment] = ACTIONS(3),
  },
  [3] = {
    [sym_instruction] = STATE(3),
    [sym_simple_instruction] = STATE(5),
    [sym_instruction_with_operand] = STATE(5),
    [sym_mnemonic_with_operand] = STATE(9),
    [aux_sym_source_file_repeat1] = STATE(3),
    [ts_builtin_sym_end] = ACTIONS(17),
    [anon_sym_load] = ACTIONS(19),
    [anon_sym_store] = ACTIONS(19),
    [anon_sym_add] = ACTIONS(22),
    [anon_sym_sub] = ACTIONS(22),
    [anon_sym_mul] = ACTIONS(22),
    [anon_sym_div] = ACTIONS(22),
    [anon_sym_mod] = ACTIONS(22),
    [anon_sym_and] = ACTIONS(22),
    [anon_sym_or] = ACTIONS(22),
    [anon_sym_xor] = ACTIONS(22),
    [anon_sym_not] = ACTIONS(22),
    [anon_sym_eq] = ACTIONS(22),
    [anon_sym_neq] = ACTIONS(22),
    [anon_sym_le] = ACTIONS(19),
    [anon_sym_leq] = ACTIONS(22),
    [anon_sym_gr] = ACTIONS(22),
    [anon_sym_geq] = ACTIONS(22),
    [anon_sym_return] = ACTIONS(22),
    [anon_sym_halt] = ACTIONS(22),
    [anon_sym_pop] = ACTIONS(22),
    [anon_sym_dup] = ACTIONS(22),
    [anon_sym_new] = ACTIONS(22),
    [anon_sym_mark] = ACTIONS(22),
    [anon_sym_loadc] = ACTIONS(25),
    [anon_sym_jump] = ACTIONS(28),
    [anon_sym_jumpz] = ACTIONS(25),
    [anon_sym_call] = ACTIONS(25),
    [anon_sym_alloc] = ACTIONS(25),
    [anon_sym_loada] = ACTIONS(25),
    [anon_sym_storea] = ACTIONS(25),
    [anon_sym_move] = ACTIONS(25),
    [anon_sym_enter] = ACTIONS(25),
    [anon_sym_loadrc] = ACTIONS(25),
    [anon_sym_loadr] = ACTIONS(28),
    [anon_sym_storer] = ACTIONS(25),
    [sym_comment] = ACTIONS(3),
  },
  [4] = {
    [ts_builtin_sym_end] = ACTIONS(31),
    [anon_sym_load] = ACTIONS(33),
    [anon_sym_store] = ACTIONS(33),
    [anon_sym_add] = ACTIONS(31),
    [anon_sym_sub] = ACTIONS(31),
    [anon_sym_mul] = ACTIONS(31),
    [anon_sym_div] = ACTIONS(31),
    [anon_sym_mod] = ACTIONS(31),
    [anon_sym_and] = ACTIONS(31),
    [anon_sym_or] = ACTIONS(31),
    [anon_sym_xor] = ACTIONS(31),
    [anon_sym_not] = ACTIONS(31),
    [anon_sym_eq] = ACTIONS(31),
    [anon_sym_neq] = ACTIONS(31),
    [anon_sym_le] = ACTIONS(33),
    [anon_sym_leq] = ACTIONS(31),
    [anon_sym_gr] = ACTIONS(31),
    [anon_sym_geq] = ACTIONS(31),
    [anon_sym_return] = ACTIONS(31),
    [anon_sym_halt] = ACTIONS(31),
    [anon_sym_pop] = ACTIONS(31),
    [anon_sym_dup] = ACTIONS(31),
    [anon_sym_new] = ACTIONS(31),
    [anon_sym_mark] = ACTIONS(31),
    [anon_sym_loadc] = ACTIONS(31),
    [anon_sym_jump] = ACTIONS(33),
    [anon_sym_jumpz] = ACTIONS(31),
    [anon_sym_call] = ACTIONS(31),
    [anon_sym_alloc] = ACTIONS(31),
    [anon_sym_loada] = ACTIONS(31),
    [anon_sym_storea] = ACTIONS(31),
    [anon_sym_move] = ACTIONS(31),
    [anon_sym_enter] = ACTIONS(31),
    [anon_sym_loadrc] = ACTIONS(31),
    [anon_sym_loadr] = ACTIONS(33),
    [anon_sym_storer] = ACTIONS(31),
    [sym_comment] = ACTIONS(3),
  },
  [5] = {
    [ts_builtin_sym_end] = ACTIONS(35),
    [anon_sym_load] = ACTIONS(37),
    [anon_sym_store] = ACTIONS(37),
    [anon_sym_add] = ACTIONS(35),
    [anon_sym_sub] = ACTIONS(35),
    [anon_sym_mul] = ACTIONS(35),
    [anon_sym_div] = ACTIONS(35),
    [anon_sym_mod] = ACTIONS(35),
    [anon_sym_and] = ACTIONS(35),
    [anon_sym_or] = ACTIONS(35),
    [anon_sym_xor] = ACTIONS(35),
    [anon_sym_not] = ACTIONS(35),
    [anon_sym_eq] = ACTIONS(35),
    [anon_sym_neq] = ACTIONS(35),
    [anon_sym_le] = ACTIONS(37),
    [anon_sym_leq] = ACTIONS(35),
    [anon_sym_gr] = ACTIONS(35),
    [anon_sym_geq] = ACTIONS(35),
    [anon_sym_return] = ACTIONS(35),
    [anon_sym_halt] = ACTIONS(35),
    [anon_sym_pop] = ACTIONS(35),
    [anon_sym_dup] = ACTIONS(35),
    [anon_sym_new] = ACTIONS(35),
    [anon_sym_mark] = ACTIONS(35),
    [anon_sym_loadc] = ACTIONS(35),
    [anon_sym_jump] = ACTIONS(37),
    [anon_sym_jumpz] = ACTIONS(35),
    [anon_sym_call] = ACTIONS(35),
    [anon_sym_alloc] = ACTIONS(35),
    [anon_sym_loada] = ACTIONS(35),
    [anon_sym_storea] = ACTIONS(35),
    [anon_sym_move] = ACTIONS(35),
    [anon_sym_enter] = ACTIONS(35),
    [anon_sym_loadrc] = ACTIONS(35),
    [anon_sym_loadr] = ACTIONS(37),
    [anon_sym_storer] = ACTIONS(35),
    [sym_comment] = ACTIONS(3),
  },
  [6] = {
    [ts_builtin_sym_end] = ACTIONS(39),
    [anon_sym_load] = ACTIONS(41),
    [anon_sym_store] = ACTIONS(41),
    [anon_sym_add] = ACTIONS(39),
    [anon_sym_sub] = ACTIONS(39),
    [anon_sym_mul] = ACTIONS(39),
    [anon_sym_div] = ACTIONS(39),
    [anon_sym_mod] = ACTIONS(39),
    [anon_sym_and] = ACTIONS(39),
    [anon_sym_or] = ACTIONS(39),
    [anon_sym_xor] = ACTIONS(39),
    [anon_sym_not] = ACTIONS(39),
    [anon_sym_eq] = ACTIONS(39),
    [anon_sym_neq] = ACTIONS(39),
    [anon_sym_le] = ACTIONS(41),
    [anon_sym_leq] = ACTIONS(39),
    [anon_sym_gr] = ACTIONS(39),
    [anon_sym_geq] = ACTIONS(39),
    [anon_sym_return] = ACTIONS(39),
    [anon_sym_halt] = ACTIONS(39),
    [anon_sym_pop] = ACTIONS(39),
    [anon_sym_dup] = ACTIONS(39),
    [anon_sym_new] = ACTIONS(39),
    [anon_sym_mark] = ACTIONS(39),
    [anon_sym_loadc] = ACTIONS(39),
    [anon_sym_jump] = ACTIONS(41),
    [anon_sym_jumpz] = ACTIONS(39),
    [anon_sym_call] = ACTIONS(39),
    [anon_sym_alloc] = ACTIONS(39),
    [anon_sym_loada] = ACTIONS(39),
    [anon_sym_storea] = ACTIONS(39),
    [anon_sym_move] = ACTIONS(39),
    [anon_sym_enter] = ACTIONS(39),
    [anon_sym_loadrc] = ACTIONS(39),
    [anon_sym_loadr] = ACTIONS(41),
    [anon_sym_storer] = ACTIONS(39),
    [sym_comment] = ACTIONS(3),
  },
};

static const uint16_t ts_small_parse_table[] = {
  [0] = 2,
    ACTIONS(3), 1,
      sym_comment,
    ACTIONS(43), 1,
      sym_number,
  [7] = 2,
    ACTIONS(3), 1,
      sym_comment,
    ACTIONS(45), 1,
      ts_builtin_sym_end,
  [14] = 2,
    ACTIONS(3), 1,
      sym_comment,
    ACTIONS(47), 1,
      sym_number,
};

static const uint32_t ts_small_parse_table_map[] = {
  [SMALL_STATE(7)] = 0,
  [SMALL_STATE(8)] = 7,
  [SMALL_STATE(9)] = 14,
};

static const TSParseActionEntry ts_parse_actions[] = {
  [0] = {.entry = {.count = 0, .reusable = false}},
  [1] = {.entry = {.count = 1, .reusable = false}}, RECOVER(),
  [3] = {.entry = {.count = 1, .reusable = true}}, SHIFT_EXTRA(),
  [5] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_source_file, 0, 0, 0),
  [7] = {.entry = {.count = 1, .reusable = false}}, SHIFT(4),
  [9] = {.entry = {.count = 1, .reusable = true}}, SHIFT(4),
  [11] = {.entry = {.count = 1, .reusable = true}}, SHIFT(7),
  [13] = {.entry = {.count = 1, .reusable = false}}, SHIFT(7),
  [15] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_source_file, 1, 0, 0),
  [17] = {.entry = {.count = 1, .reusable = true}}, REDUCE(aux_sym_source_file_repeat1, 2, 0, 0),
  [19] = {.entry = {.count = 2, .reusable = false}}, REDUCE(aux_sym_source_file_repeat1, 2, 0, 0), SHIFT_REPEAT(4),
  [22] = {.entry = {.count = 2, .reusable = true}}, REDUCE(aux_sym_source_file_repeat1, 2, 0, 0), SHIFT_REPEAT(4),
  [25] = {.entry = {.count = 2, .reusable = true}}, REDUCE(aux_sym_source_file_repeat1, 2, 0, 0), SHIFT_REPEAT(7),
  [28] = {.entry = {.count = 2, .reusable = false}}, REDUCE(aux_sym_source_file_repeat1, 2, 0, 0), SHIFT_REPEAT(7),
  [31] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_simple_instruction, 1, 0, 0),
  [33] = {.entry = {.count = 1, .reusable = false}}, REDUCE(sym_simple_instruction, 1, 0, 0),
  [35] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_instruction, 1, 0, 0),
  [37] = {.entry = {.count = 1, .reusable = false}}, REDUCE(sym_instruction, 1, 0, 0),
  [39] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_instruction_with_operand, 2, 0, 0),
  [41] = {.entry = {.count = 1, .reusable = false}}, REDUCE(sym_instruction_with_operand, 2, 0, 0),
  [43] = {.entry = {.count = 1, .reusable = true}}, REDUCE(sym_mnemonic_with_operand, 1, 0, 0),
  [45] = {.entry = {.count = 1, .reusable = true}},  ACCEPT_INPUT(),
  [47] = {.entry = {.count = 1, .reusable = true}}, SHIFT(6),
};

#ifdef __cplusplus
extern "C" {
#endif
#ifdef TREE_SITTER_HIDE_SYMBOLS
#define TS_PUBLIC
#elif defined(_WIN32)
#define TS_PUBLIC __declspec(dllexport)
#else
#define TS_PUBLIC __attribute__((visibility("default")))
#endif

TS_PUBLIC const TSLanguage *tree_sitter_cma(void) {
  static const TSLanguage language = {
    .version = LANGUAGE_VERSION,
    .symbol_count = SYMBOL_COUNT,
    .alias_count = ALIAS_COUNT,
    .token_count = TOKEN_COUNT,
    .external_token_count = EXTERNAL_TOKEN_COUNT,
    .state_count = STATE_COUNT,
    .large_state_count = LARGE_STATE_COUNT,
    .production_id_count = PRODUCTION_ID_COUNT,
    .field_count = FIELD_COUNT,
    .max_alias_sequence_length = MAX_ALIAS_SEQUENCE_LENGTH,
    .parse_table = &ts_parse_table[0][0],
    .small_parse_table = ts_small_parse_table,
    .small_parse_table_map = ts_small_parse_table_map,
    .parse_actions = ts_parse_actions,
    .symbol_names = ts_symbol_names,
    .symbol_metadata = ts_symbol_metadata,
    .public_symbol_map = ts_symbol_map,
    .alias_map = ts_non_terminal_alias_map,
    .alias_sequences = &ts_alias_sequences[0][0],
    .lex_modes = ts_lex_modes,
    .lex_fn = ts_lex,
    .primary_state_ids = ts_primary_state_ids,
  };
  return &language;
}
#ifdef __cplusplus
}
#endif
