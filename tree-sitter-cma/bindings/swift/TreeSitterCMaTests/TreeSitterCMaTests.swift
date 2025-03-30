import XCTest
import SwiftTreeSitter
import TreeSitterCma

final class TreeSitterCmaTests: XCTestCase {
    func testCanLoadGrammar() throws {
        let parser = Parser()
        let language = Language(language: tree_sitter_cma())
        XCTAssertNoThrow(try parser.setLanguage(language),
                         "Error loading CMa grammar")
    }
}
