package com.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void accountObjectIsNotNullTest(){
        assertNotNull(account);
    }

    @Test
    void accountObjectCanReceiveDeposit(){
        account.depositMoney(2500);
        assertEquals(2500,account.getAccountBalance());

        account.depositMoney(5000);
        assertEquals(7500,account.getAccountBalance());
    }

    @Test
    void accountObjectWillNotTakeNegativeAmountDepositTest(){
        account.depositMoney(6000);
        account.depositMoney(-1300);
        int accountBalance = account.getAccountBalance();
        assertEquals(6000,accountBalance);
    }

    @Test
    void accountObjectCanWithdrawCash() throws Exception {
        account.setAccountPin(1344);
        account.depositMoney(5000);
        account.withdrawMoney(2000,1344);
        assertEquals(3000,account.getAccountBalance());
    }

    @Test
    void accountObjectCannotWithdrawANegativeAmount() throws Exception {
        try {
            account.setAccountPin(1345);
            account.depositMoney(5000);
            account.withdrawMoney(-2000, 1345);
        }
        catch(Exception e){
            assertEquals("You can't withdraw an amount that is less than or equal to zero",e.getMessage());
        }
       // assertEquals(5000,account.getAccountBalance());
    }

    @Test
    void accountObjectCannotWithdrawMoreThanBalance() throws Exception {
        try {
            account.setAccountPin(1345);
            account.depositMoney(7000);
            account.withdrawMoney(8000, 1345);
        }
        catch(Exception e){assertEquals("You can't withdraw an amount that is more than your balance",e.getMessage());}
       // assertEquals("You can't withdraw an amount that is more than your balance",account.getErrorMessage());
    }

    @Test
    void accountObjectWithdrawsOnlyWithin1000BalanceLimit() throws Exception {
        try {
            account.setAccountPin(1345);
            account.depositMoney(5000);
            account.withdrawMoney(4000, 1345);
            account.withdrawMoney(800, 1345);
        }
        catch(Exception e){assertEquals("You have 1000 left, you can't withdraw from it",e.getMessage());}
        //assertEquals("You have 1000 left, you can't withdraw from it",account.getErrorMessage());
    }

    @Test
    void accountObjectCanSetAPin() throws Exception {
        account.setAccountPin(1234);
        assertEquals(1234,account.getAccountPin());
    }

    @Test
    void accountObjectTakesOnlyFourDigitsForAPin() throws Exception {
       try{
           account.setAccountPin(1);
           account.setAccountPin(10000000);
       }
       catch (Exception e){
         assertEquals("Your pin has to be a four(4) digit number",e.getMessage());
       }
       // assertEquals("Your pin has to be a four(4) digit number",account.getErrorMessage());
    }

    @Test
    void accountObjectWithdrawsWithPin() throws Exception {
        account.setAccountPin(1345);
        account.depositMoney(5000);
        account.withdrawMoney(2000,1345);
        assertEquals(3000,account.getAccountBalance());
    }

}