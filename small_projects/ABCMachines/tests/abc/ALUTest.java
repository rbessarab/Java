package abc;

import org.junit.Test;
import static org.junit.Assert.*;

public class ALUTest {
    @Test
    public void testDefaultConstructor() {
        ALU alu = new ALU();
        assertEquals("Status should be ZERO", Nzp.ZERO, alu.getStatus());
        assertTrue(alu.getStatus() == Nzp.ZERO);
    }

    @Test
    public void testOperateMethod() {
        ALU alu = new ALU();
        short answer = alu.operate((short) 1, Operator.ADD, (short) 2);
        assertTrue(answer == (short) 3);
        assertTrue(alu.getStatus() == Nzp.POSITIVE);

        answer = alu.operate((short) 3, Operator.SUB, (short) 2);
        assertFalse(answer == (short) 4);
        assertTrue(answer == (short) 1);
        assertTrue(alu.getStatus() == Nzp.POSITIVE);

        answer = alu.operate((short) 2, Operator.SUB, (short) 3);
        assertTrue(answer == (short) -1);
        assertTrue(alu.getStatus() == Nzp.NEGATIVE);

        answer = alu.operate((short) 2, Operator.MULT, (short) 0);
        assertTrue(answer == (short) 0);
        assertTrue(alu.getStatus() == Nzp.ZERO);

        answer = alu.operate((short) 6, Operator.DIV, (short) 3);
        assertTrue(answer == (short) 2);
        assertTrue(alu.getStatus() == Nzp.POSITIVE);
    }
}