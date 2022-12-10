import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElvishCraneDriverTest {
    @Test
    void isAxis() {
        ElvishCraneDriver r = new ElvishCraneDriver(null);
        assertTrue(r.isAxis(" 1   2   3"));
        assertFalse(r.isAxis(""));
        assertFalse(r.isAxis("[Z] [M] [P]"));
        assertFalse(r.isAxis("move 1 from 1 to 2"));
    }

    @Test
    void setColCount() {
        ElvishCraneDriver r = new ElvishCraneDriver(null);
        r.setColCount(" 1   2   3   4   5   6   7   8   9");
        assertEquals(9, r.getColCount());
    }

    @Test
    void addMove() {
        ElvishCraneDriver r = new ElvishCraneDriver(null);
        r.addMove("move 1 from 2 to 3");
        Move move = r.moves.get(0);
        assertEquals(1, move.getAmount());
        assertEquals(2, move.getFrom());
        assertEquals(3, move.getTo());
    }

    @Test
    void isRow() {
        ElvishCraneDriver r = new ElvishCraneDriver(null);
        assertTrue(r.isRow("    [D]"));
    }
}