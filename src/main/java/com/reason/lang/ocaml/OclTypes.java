package com.reason.lang.ocaml;

import com.reason.lang.core.psi.type.MlCompositeElementType;
import com.reason.lang.core.psi.type.MlTokenElementType;
import com.reason.lang.core.psi.type.MlTypes;
import com.reason.lang.core.stub.type.*;

public class OclTypes extends MlTypes {

    public static final OclTypes INSTANCE = new OclTypes();

    private OclTypes() {
        // Composite element types

        ANNOTATION_EXPR = new MlCompositeElementType("ANNOTATION_EXPRESSION", OclLanguage.INSTANCE);
        EXTERNAL_STMT = new PsiExternalStubElementType("EXTERNAL_STMT", OclLanguage.INSTANCE);
        EXCEPTION_EXPR = new MlCompositeElementType("EXCEPTION_EXPRESSION", OclLanguage.INSTANCE);
        INCLUDE_STMT = new MlCompositeElementType("INCLUDE_STMT", OclLanguage.INSTANCE);
        LET_STMT = new PsiLetStubElementType("LET_STMT", OclLanguage.INSTANCE);
        MACRO_NAME = new MlCompositeElementType("MACRO_NAME", OclLanguage.INSTANCE);
        MODULE_STMT = new PsiModuleStubElementType("MODULE_STMT", OclLanguage.INSTANCE);
        MODULE_PATH = new MlCompositeElementType("MODULE_PATH", OclLanguage.INSTANCE);
        OPEN_STMT = new MlCompositeElementType("OPEN_STMT", OclLanguage.INSTANCE);
        EXP_TYPE = new PsiTypeStubElementType("EXP_TYPE", OclLanguage.INSTANCE);
        VAL_EXPR = new PsiValStubElementType("VAL_EXPRESSION", OclLanguage.INSTANCE);
        ASSERT_STMT = new MlCompositeElementType("ASSERT_STMT", OclLanguage.INSTANCE);
        FUN_EXPR = new MlCompositeElementType("FUN_EXPR", OclLanguage.INSTANCE);
        FUN_PARAMS = new MlCompositeElementType("FUN_PARAMS", OclLanguage.INSTANCE);
        FUN_BODY = new MlCompositeElementType("FUN_BODY", OclLanguage.INSTANCE);
        IF_STMT = new MlCompositeElementType("IF_STMT", OclLanguage.INSTANCE);
        LET_BINDING = new MlCompositeElementType("LET_BINDING", OclLanguage.INSTANCE);
        TYPE_CONSTR_NAME = new MlCompositeElementType("TYPE_CONSTR_NAME", OclLanguage.INSTANCE);
        TYPE_BINDING = new MlCompositeElementType("TYPE_BINDING", OclLanguage.INSTANCE);
        MATCH_EXPR = new MlCompositeElementType("MATCH_EXPR", OclLanguage.INSTANCE);
        PATTERN_MATCH_EXPR = new MlCompositeElementType("PATTERN_MATCH_EXPR", OclLanguage.INSTANCE);
        SCOPED_EXPR = new MlCompositeElementType("SCOPED_EXPR", OclLanguage.INSTANCE);
        SIG_SCOPE = new MlCompositeElementType("SIG_SCOPE", OclLanguage.INSTANCE);
        NAMED_SYMBOL = new MlCompositeElementType("NAMED_SYMBOL", OclLanguage.INSTANCE);
        BIN_CONDITION = new MlCompositeElementType("BIN_CONDITION", OclLanguage.INSTANCE);
        VARIANT_EXP = new MlCompositeElementType("VARIANT_EXP", OclLanguage.INSTANCE);
        SWITCH_EXPR = new MlCompositeElementType("SWITCH_EXPR", OclLanguage.INSTANCE);
        TAG_START = new MlCompositeElementType("TAG_START", OclLanguage.INSTANCE);
        TAG_CLOSE = new MlCompositeElementType("TAG_CLOSE", OclLanguage.INSTANCE);
        TAG_PROPERTY = new MlCompositeElementType("TAG_PROPERTY", OclLanguage.INSTANCE);
        RECORD_EXPR = new MlCompositeElementType("RECORD_EXPR", OclLanguage.INSTANCE);
        RECORD_FIELD = new MlCompositeElementType("RECORD_FIELD", OclLanguage.INSTANCE);
        INTERPOLATION_EXPR = new MlCompositeElementType("INTERPOLATION_EXPR", OclLanguage.INSTANCE);
        TRY_EXPR = new MlCompositeElementType("TRY_EXPR", OclLanguage.INSTANCE);
        WITH_EXPR = new MlCompositeElementType("WITH_EXPR", OclLanguage.INSTANCE);
        UPPER_SYMBOL = new MlCompositeElementType("UPPER_SYMBOL", OclLanguage.INSTANCE);
        LOWER_SYMBOL = new MlCompositeElementType("LOWER_SYMBOL", OclLanguage.INSTANCE);
        STRUCT_EXPR = new MlCompositeElementType("STRUCT_EXPR", OclLanguage.INSTANCE);

        // Token element types

        BOOL = new MlTokenElementType("BOOL", OclLanguage.INSTANCE);
        STRING = new MlTokenElementType("STRING", OclLanguage.INSTANCE);
        FLOAT = new MlTokenElementType("FLOAT", OclLanguage.INSTANCE);
        CHAR = new MlTokenElementType("CHAR", OclLanguage.INSTANCE);
        INT = new MlTokenElementType("INT", OclLanguage.INSTANCE);

        BOOL_VALUE = new MlTokenElementType("BOOL_VALUE", OclLanguage.INSTANCE);
        STRING_VALUE = new MlTokenElementType("STRING_VALUE", OclLanguage.INSTANCE);
        FLOAT_VALUE = new MlTokenElementType("FLOAT_VALUE", OclLanguage.INSTANCE);
        CHAR_VALUE = new MlTokenElementType("CHAR_VALUE", OclLanguage.INSTANCE);
        INT_VALUE = new MlTokenElementType("INT_VALUE", OclLanguage.INSTANCE);
        SWITCH = new MlTokenElementType("SWITCH", OclLanguage.INSTANCE);
        GENERIC_COND = new MlTokenElementType("GENERIC_COND", OclLanguage.INSTANCE);
        FUNCTION = new MlTokenElementType("FUNCTION", OclLanguage.INSTANCE);
        FUN = new MlTokenElementType("FUN", OclLanguage.INSTANCE);
        FUNCTOR = new MlTokenElementType("FUNCTOR", OclLanguage.INSTANCE);
        IF = new MlTokenElementType("IF", OclLanguage.INSTANCE);
        EXCEPTION_NAME = new MlTokenElementType("EXCEPTION_NAME", OclLanguage.INSTANCE);
        LOCAL_OPEN = new MlTokenElementType("LOCAL_OPEN", OclLanguage.INSTANCE);
        PROPERTY_NAME = new MlTokenElementType("PROPERTY_NAME", OclLanguage.INSTANCE);
        AND = new MlTokenElementType("AND", OclLanguage.INSTANCE);
        ANDAND = new MlTokenElementType("ANDAND", OclLanguage.INSTANCE);
        ARROBASE = new MlTokenElementType("ARROBASE", OclLanguage.INSTANCE);
        ARROW = new MlTokenElementType("ARROW", OclLanguage.INSTANCE);
        ASSERT = new MlTokenElementType("ASSERT", OclLanguage.INSTANCE);
        AS = new MlTokenElementType("AS", OclLanguage.INSTANCE);
        BACKTICK = new MlTokenElementType("BACKTICK", OclLanguage.INSTANCE);
        BEGIN = new MlTokenElementType("BEGIN", OclLanguage.INSTANCE);
        CARRET = new MlTokenElementType("CARRET", OclLanguage.INSTANCE);
        COLON = new MlTokenElementType("COLON", OclLanguage.INSTANCE);
        COMMA = new MlTokenElementType("COMMA", OclLanguage.INSTANCE);
        COMMENT = new MlTokenElementType("COMMENT", OclLanguage.INSTANCE);
        DIFF = new MlTokenElementType("DIFF", OclLanguage.INSTANCE);
        LT_OR_EQUAL = new MlTokenElementType("LT_OR_EQUAL", OclLanguage.INSTANCE);
        GT_OR_EQUAL = new MlTokenElementType("GT_OR_EQUAL", OclLanguage.INSTANCE);
        DOLLAR = new MlTokenElementType("DOLLAR", OclLanguage.INSTANCE);
        DOT = new MlTokenElementType("DOT", OclLanguage.INSTANCE);
        DOTDOTDOT = new MlTokenElementType("DOTDOTDOT", OclLanguage.INSTANCE);
        DO = new MlTokenElementType("DO", OclLanguage.INSTANCE);
        DONE = new MlTokenElementType("DONE", OclLanguage.INSTANCE);
        ELSE = new MlTokenElementType("ELSE", OclLanguage.INSTANCE);
        END = new MlTokenElementType("END", OclLanguage.INSTANCE);
        NOT_EQ = new MlTokenElementType("EQ", OclLanguage.INSTANCE);
        NOT_EQEQ = new MlTokenElementType("EQEQ", OclLanguage.INSTANCE);
        EQ = new MlTokenElementType("EQ", OclLanguage.INSTANCE);
        EQEQ = new MlTokenElementType("EQEQ", OclLanguage.INSTANCE);
        EQEQEQ = new MlTokenElementType("EQEQEQ", OclLanguage.INSTANCE);
        EXCEPTION = new MlTokenElementType("EXCEPTION", OclLanguage.INSTANCE);
        EXCLAMATION_MARK = new MlTokenElementType("EXCLAMATION_MARK", OclLanguage.INSTANCE);
        EXTERNAL = new MlTokenElementType("EXTERNAL", OclLanguage.INSTANCE);
        FOR = new MlTokenElementType("FOR", OclLanguage.INSTANCE);
        TYPE_ARGUMENT = new MlTokenElementType("TYPE_ARGUMENT", OclLanguage.INSTANCE);
        GT = new MlTokenElementType("GT", OclLanguage.INSTANCE);
        IN = new MlTokenElementType("IN", OclLanguage.INSTANCE);
        LAZY = new MlTokenElementType("LAZY", OclLanguage.INSTANCE);
        INCLUDE = new MlTokenElementType("INCLUDE", OclLanguage.INSTANCE);
        LARRAY = new MlTokenElementType("LARRAY", OclLanguage.INSTANCE);
        LBRACE = new MlTokenElementType("LBRACE", OclLanguage.INSTANCE);
        LBRACKET = new MlTokenElementType("LBRACKET", OclLanguage.INSTANCE);
        LET = new MlTokenElementType("LET", OclLanguage.INSTANCE);
        LIDENT = new MlTokenElementType("LIDENT", OclLanguage.INSTANCE);
        LIST = new MlTokenElementType("LIST", OclLanguage.INSTANCE);
        LPAREN = new MlTokenElementType("LPAREN", OclLanguage.INSTANCE);
        LT = new MlTokenElementType("LT", OclLanguage.INSTANCE);
        MATCH = new MlTokenElementType("MATCH", OclLanguage.INSTANCE);
        MINUS = new MlTokenElementType("MINUS", OclLanguage.INSTANCE);
        MINUSDOT = new MlTokenElementType("MINUSDOT", OclLanguage.INSTANCE);
        MODULE = new MlTokenElementType("MODULE", OclLanguage.INSTANCE);
        MUTABLE = new MlTokenElementType("MUTABLE", OclLanguage.INSTANCE);
        NONE = new MlTokenElementType("NONE", OclLanguage.INSTANCE);
        OF = new MlTokenElementType("OF", OclLanguage.INSTANCE);
        OPEN = new MlTokenElementType("OPEN", OclLanguage.INSTANCE);
        OPTION = new MlTokenElementType("OPTION", OclLanguage.INSTANCE);
        POLY_VARIANT = new MlTokenElementType("POLY_VARIANT", OclLanguage.INSTANCE);
        VARIANT_NAME = new MlTokenElementType("VARIANT_NAME", OclLanguage.INSTANCE);
        PIPE = new MlTokenElementType("PIPE", OclLanguage.INSTANCE);
        PIPE_FORWARD = new MlTokenElementType("PIPE_FORWARD", OclLanguage.INSTANCE);
        PIPE_FIRST = new MlTokenElementType("PIPE_FIRST", OclLanguage.INSTANCE);
        PLUS = new MlTokenElementType("PLUS", OclLanguage.INSTANCE);
        PERCENT = new MlTokenElementType("PERCENT", OclLanguage.INSTANCE);
        PLUSDOT = new MlTokenElementType("PLUSDOT", OclLanguage.INSTANCE);
        QUESTION_MARK = new MlTokenElementType("QUESTION_MARK", OclLanguage.INSTANCE);
        QUOTE = new MlTokenElementType("QUOTE", OclLanguage.INSTANCE);
        RAISE = new MlTokenElementType("RAISE", OclLanguage.INSTANCE);
        RARRAY = new MlTokenElementType("RARRAY", OclLanguage.INSTANCE);
        RBRACE = new MlTokenElementType("RBRACE", OclLanguage.INSTANCE);
        RBRACKET = new MlTokenElementType("RBRACKET", OclLanguage.INSTANCE);
        REC = new MlTokenElementType("REC", OclLanguage.INSTANCE);
        REF = new MlTokenElementType("REF", OclLanguage.INSTANCE);
        RPAREN = new MlTokenElementType("RPAREN", OclLanguage.INSTANCE);
        SEMI = new MlTokenElementType("SEMI", OclLanguage.INSTANCE);
        SIG = new MlTokenElementType("SIG", OclLanguage.INSTANCE);
        SHARP = new MlTokenElementType("SHARP", OclLanguage.INSTANCE);
        SHARPSHARP = new MlTokenElementType("SHARPSHARP", OclLanguage.INSTANCE);
        SHORTCUT = new MlTokenElementType("SHORTCUT", OclLanguage.INSTANCE);
        SLASH = new MlTokenElementType("SLASH", OclLanguage.INSTANCE);
        SLASHDOT = new MlTokenElementType("SLASHDOT", OclLanguage.INSTANCE);
        SOME = new MlTokenElementType("SOME", OclLanguage.INSTANCE);
        STAR = new MlTokenElementType("STAR", OclLanguage.INSTANCE);
        STARDOT = new MlTokenElementType("STARDOT", OclLanguage.INSTANCE);
        STRUCT = new MlTokenElementType("STRUCT", OclLanguage.INSTANCE);
        TAG_AUTO_CLOSE = new MlTokenElementType("TAG_AUTO_CLOSE", OclLanguage.INSTANCE);
        TAG_NAME = new MlTokenElementType("TAG_NAME", OclLanguage.INSTANCE);
        TAG_LT = new MlTokenElementType("TAG_LT", OclLanguage.INSTANCE);
        TAG_LT_SLASH = new MlTokenElementType("TAG_LT_SLASH", OclLanguage.INSTANCE);
        TAG_GT = new MlTokenElementType("TAG_GT", OclLanguage.INSTANCE);
        TILDE = new MlTokenElementType("TILDE", OclLanguage.INSTANCE);
        TO = new MlTokenElementType("TO", OclLanguage.INSTANCE);
        THEN = new MlTokenElementType("THEN", OclLanguage.INSTANCE);
        TRY = new MlTokenElementType("TRY", OclLanguage.INSTANCE);
        TYPE = new MlTokenElementType("TYPE", OclLanguage.INSTANCE);
        UIDENT = new MlTokenElementType("UIDENT", OclLanguage.INSTANCE);
        UNIT = new MlTokenElementType("UNIT", OclLanguage.INSTANCE);
        VAL = new MlTokenElementType("VAL", OclLanguage.INSTANCE);
        PUB = new MlTokenElementType("PUB", OclLanguage.INSTANCE);
        WHEN = new MlTokenElementType("WHEN", OclLanguage.INSTANCE);
        WHILE = new MlTokenElementType("WHILE", OclLanguage.INSTANCE);
        WITH = new MlTokenElementType("WITH", OclLanguage.INSTANCE);

        ASR = new MlTokenElementType("ASR", OclLanguage.INSTANCE);
        CLASS = new MlTokenElementType("CLASS", OclLanguage.INSTANCE);
        CONSTRAINT = new MlTokenElementType("CONSTRAINT", OclLanguage.INSTANCE);
        DOWNTO = new MlTokenElementType("DOWNTO", OclLanguage.INSTANCE);
        INHERIT = new MlTokenElementType("INHERIT", OclLanguage.INSTANCE);
        INITIALIZER = new MlTokenElementType("INITIALIZER", OclLanguage.INSTANCE);
        LAND = new MlTokenElementType("LAND", OclLanguage.INSTANCE);
        LOR = new MlTokenElementType("LOR", OclLanguage.INSTANCE);
        LSL = new MlTokenElementType("LSL", OclLanguage.INSTANCE);
        LSR = new MlTokenElementType("LSR", OclLanguage.INSTANCE);
        LXOR = new MlTokenElementType("LXOR", OclLanguage.INSTANCE);
        METHOD = new MlTokenElementType("METHOD", OclLanguage.INSTANCE);
        MOD = new MlTokenElementType("MOD", OclLanguage.INSTANCE);
        NEW = new MlTokenElementType("NEW", OclLanguage.INSTANCE);
        NONREC = new MlTokenElementType("NONREC", OclLanguage.INSTANCE);
        OR = new MlTokenElementType("OR", OclLanguage.INSTANCE);
        PRIVATE = new MlTokenElementType("PRIVATE", OclLanguage.INSTANCE);
        VIRTUAL = new MlTokenElementType("VIRTUAL", OclLanguage.INSTANCE);

        COLON_EQ = new MlTokenElementType("COLON_EQ", OclLanguage.INSTANCE);
        COLON_GT = new MlTokenElementType("COLON_GT", OclLanguage.INSTANCE);
        DOTDOT = new MlTokenElementType("DOTDOT", OclLanguage.INSTANCE);
        SEMISEMI = new MlTokenElementType("SEMISEMI", OclLanguage.INSTANCE);
        GT_BRACKET = new MlTokenElementType("GT_BRACKET", OclLanguage.INSTANCE);
        GT_BRACE = new MlTokenElementType("GT_BRACE", OclLanguage.INSTANCE);
        LEFT_ARROW = new MlTokenElementType("LEFT_ARROW", OclLanguage.INSTANCE);
        RIGHT_ARROW = new MlTokenElementType("RIGHT_ARROW", OclLanguage.INSTANCE);

        OBJECT = new MlTokenElementType("OBJECT", OclLanguage.INSTANCE);
        RECORD = new MlTokenElementType("RECORD", OclLanguage.INSTANCE);

        AMPERSAND = new MlTokenElementType("AMPERSAND", OclLanguage.INSTANCE);
        BRACKET_GT = new MlTokenElementType("BRACKET_GT", OclLanguage.INSTANCE);
        BRACKET_LT = new MlTokenElementType("BRACKET_LT", OclLanguage.INSTANCE);
        BRACE_LT = new MlTokenElementType("BRACE_LT", OclLanguage.INSTANCE);

        ML_STRING_OPEN = new MlTokenElementType("ML_STRING_OPEN", OclLanguage.INSTANCE);
        ML_STRING_CLOSE = new MlTokenElementType("ML_STRING_CLOSE", OclLanguage.INSTANCE);
        JS_STRING_OPEN = new MlTokenElementType("JS_STRING_OPEN", OclLanguage.INSTANCE);
        JS_STRING_CLOSE = new MlTokenElementType("JS_STRING_CLOSE", OclLanguage.INSTANCE);
        UNDERSCORE = new MlTokenElementType("UNDERSCORE", OclLanguage.INSTANCE);
    }
}
