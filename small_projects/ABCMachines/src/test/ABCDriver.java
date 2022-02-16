package test;

import abc.*;

public class ABCDriver
{
	public static void main(String[] args)
	{
		System.out.println("Machine 1:");
		//create a new ABCMachine and pass it a new program to run
		ABCMachine mach = new ABCMachine("programs/program1.abc");
		mach.runProgram();
		//print out the registers and memory after the program runs
		System.out.println("Register dump");
		mach.printRegisters();
		System.out.println("Memory dump");
		mach.printMemory();

		System.out.println("\n");

		System.out.println("Machine 2:");
		ABCMachine machine2 = new ABCMachine("programs/program2.abc");
		machine2.runProgram();
		System.out.println("Register dump");
		machine2.printRegisters();
		System.out.println("Memory dump");
		machine2.printMemory();
	}
}
