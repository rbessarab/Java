package abc;

/**
 * @author Susan Uland, Ruslan Bessarab
 * @version 1.0
 * This class represents the Control Unit for the
 * ABC Machine
 */
public class ControlUnit {

    private ABCMachine machine;

    /**
     * @param machine The ABC machine this ControlUnit belongs to
     */
    public ControlUnit(ABCMachine machine) {
        this.machine = machine;
    }

    /*
     * This method will continuously fetch, decode, execute, and store instructions/data that are
     * loaded into the ABCMachine's memory map. The program "halts" when it reaches an instruction
     * that is zero.
     */
    public void startProcessing() {

        while (!halt()) {
            fetch();
            decodeExecuteStore();
        }

    }

    /*
     * This method performs the fetch step for the ControlUnit
     */
    public void fetch() {
        // get machine's memory
       short[] memory = machine.getMemory();

       // get program counter
       byte pc = machine.getPc();

       // get instruction
        short instruction = memory[pc];

        // load instruction into IR
        machine.setIr(instruction);

        // increment pc
        pc = (byte)(pc + 1);
        machine.setPc(pc);

    }

    /*
     * This method decodes and executes the instruction according to the
     * ABCMachine Instruction Set and stores the result
     */
    public void decodeExecuteStore() {
        byte opCode = getOpcode();
        short[] registers = machine.getRegisters();
        short src1 = registers[getSrc1Register()];
        short src2 = registers[getSrc2Register()];
        short[] memory = machine.getMemory();

        switch(opCode){
            case 0: // ADD
                registers[getDestRegister()] = machine.getAlu().operate(src1,Operator.ADD,src2);
                break;

            case 1: //SUB
                registers[getDestRegister()] = machine.getAlu().operate(src1, Operator.SUB, src2);
                break;

            case 2: //MULT
                registers[getDestRegister()] = machine.getAlu().operate(src1, Operator.MULT, src2);
                break;

            case 3: //DIV
                registers[getDestRegister()] = machine.getAlu().operate(src1, Operator.DIV, src2);
                break;

            case 4: //STORE
                memory[getMemAddr()] = registers[getSrc1Register()];
                break;

            case 5: //LD
                registers[getSrc1Register()] = memory[getMemAddr()];
                break;

            case 6: //BRANCH
                short nzp = getSrc1Register(); //4 0b100 Negative, 2 0b010 Zero, 1 0b001 Positive
                if(nzp == 1 && machine.getAlu().getStatus() == Nzp.POSITIVE) {
                    machine.setPc(getMemAddr());
                }
                else if(nzp == 4 && machine.getAlu().getStatus() == Nzp.NEGATIVE) {
                    machine.setPc(getMemAddr());
                }
                else if(nzp == 2 && machine.getAlu().getStatus() == Nzp.ZERO) {
                    break;
                }
                break;

            case 7: // JMP
                machine.setPc(getMemAddr());
                break;
        }

    }

    /**
     * This method returns the numerical value stored in bits numbered 14 - 16
     * of the instruction register (IR) so the proper opcode can be determined
     *
     * @return a number in range 0 - 7
     * 000 - ADD,  001 - SUB, 010 - MULT, 011 - DIV, 100 - ST(ORE), 101 - LD (LOAD)
     * 110 - BR (BRANCH), 111 - JMP (JUMP)
     */
    public byte getOpcode() {
        final byte SHIFT_RIGHT = 13;
        short instruction = machine.getIr();
        // bit shift right
        instruction = (short)(instruction >>> SHIFT_RIGHT);
        return (byte)(instruction & 0b111);
    }

    /**
     * This method returns the numerical value stored in bits numbered 11 - 13
     * of the instruction register (IR)
     *
     * @return a number in range 0 - 7 - indicates
     */
    public byte getSrc1Register() {
        final byte SHIFT_RIGHT = 10;
        short instruction = machine.getIr();
        instruction = (short) (instruction >>> SHIFT_RIGHT);
        return (byte) (instruction & 0b111);
    }

    /**
     * This method returns the numerical value stored in bits numbered 8 - 10
     * of the instruction register (IR)
     * IR     0  0  0  0   0  0  1  1   1  0  0  0   0  0  0  0
     * Bit # 16 15 14 13  12 11 10  9   8  7  6  5   4  3  2  1
     *
     * @return a number in range 0 - 7
     */
    public byte getSrc2Register() {
        final byte SHIFT_RIGHT = 7;
        short instruction = machine.getIr();
        instruction = (short) (instruction >>> SHIFT_RIGHT);
        return (byte) (instruction & 0b111);
    }

    /**
     * This method returns the numerical value stored in bits numbered 5 - 7
     * of the instruction register (IR)
     * IR     0  0  0  0   0  0  0  0   0  1  1  1   0  0  0  0
     * Bit # 16 15 14 13  12 11 10  9   8  7  6  5   4  3  2  1
     *
     * @return a number in range 0 - 7
     */
    public byte getDestRegister() {
        final byte SHIFT_RIGHT = 4;
        short instruction = machine.getIr();
        instruction = (short) (instruction >>> SHIFT_RIGHT);
        return (byte) (instruction & 0b111);
    }

    /**
     * This method returns the numerical value stored in bits numbered 1 - 4
     * of the instruction register (IR)
     * IR     0  0  0  0   0  0  0  0   0  0  0  0   1  1  1  1
     * Bit # 16 15 14 13  12 11 10  9   8  7  6  5   4  3  2  1
     *
     * @return a number in range 0 - 15
     */
    public byte getMemAddr() {
        short instruction = machine.getIr();
        return (byte)(instruction & 0b1111);
    }

    /**
     *
     * @return true if next instruction contains all zeros, otherwise false
     */
    public boolean halt() {
        return machine.getMemory()[machine.getPc()] == 0;
    }
}
