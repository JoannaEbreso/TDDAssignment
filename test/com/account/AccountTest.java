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
    void accountObjectWillNotTakeNegativeAmountTest(){
        account.depositMoney(5000);
        account.depositMoney(-1500);
        int accountBalance = account.getAccountBalance();
        assertEquals(5000,accountBalance);
    }

    @Test
    void accountObjectCanWithdrawCash(){
        account.depositMoney(5000);
        account.withdrawMoney(2000);
        assertEquals(3000,account.getAccountBalance());
    }

    @Test
    void accountObjectCannotWithdrawANegativeAmount(){
        account.depositMoney(5000);
        account.withdrawMoney(-2000);
        assertEquals(5000,account.getAccountBalance());
    }

    @Test
    void accountObjectCannotWithdrawMoreThanBalance(){
        account.depositMoney(7000);
        account.withdrawMoney(8000);
        assertEquals("You can't withdraw an amount that is more than your balance",account.getErrorMessage());
    }

    @Test
    void accountObjectWithdrawsOnlyWithin1000BalanceLimit(){
        account.depositMoney(5000);
        account.withdrawMoney(4000);
        account.withdrawMoney(800);
        assertEquals("You have 1000v left, you can't withdraw from it",account.getErrorMessage());
    }

    @Test
    void accountObjectCanSetAPin(){
        account.setAccountPin(1234);
        assertEquals(1234,account.getPin());
    }

    @Test
    void accountObjectTakesOnlyFourDigitsForAPin(){
        account.setAccountPin(1);
        assertEquals("Your pin has to be a four(4) digit number",account.getErrorMessage());

        account.setAccountPin(10000000);
        assertEquals("Your pin has to be a four(4) digit number",account.getErrorMessage());
    }

    @Test
    void accountObjectWithdrawsWithPin(){
        account.setAccountPin(1345);
        account.depositMoney(5000);
        account.withdrawMoney(2000,1345);
        assertEquals(3000,account.getAccountBalance());
    }

}