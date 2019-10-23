package com.niklas;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkurupAIFProgramTest {

    @Test
    public void addEmployee() {

        SkurupAIFProgram program = new SkurupAIFProgram();
        int arraySizePlayers = program.getPlayersInClub().size();
        String[] info = {"Janne", "Andersson", "30", "Striker"};

        program.addEmployee(program.getPlayersInClub(), EmployeeFactory.EmployeeType.PLAYER, info, SkurupAIFProgram.MAX_PLAYERS);
        assertEquals(arraySizePlayers + 1, program.getPlayersInClub().size());

        program.addEmployee(program.getPlayersInClub(), EmployeeFactory.EmployeeType.PLAYER, info, SkurupAIFProgram.MAX_PLAYERS);
        assertEquals(arraySizePlayers + 2, program.getPlayersInClub().size());

        // Om du har lust, testa om spelare verkligen finns på den sista platsen i listan.



    }
}