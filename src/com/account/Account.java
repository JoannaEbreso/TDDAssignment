package com.account;

public class Account {
    private int accountBalance;
    private int accountPin;
    //private String error;

    public void depositMoney(int amount) {
        if(amount>0){
            accountBalance += amount;
        }
    }

    public int getAccountBalance() {
        return accountBalance;
    }


    public void withdrawMoney(int amount, int accountPin) throws Exception{
        if (this.accountPin == accountPin){
            if(accountBalance <= 1000){
                throw new Exception("You have 1000 left, you can't withdraw from it");
            }
            else
            if(amount <= 0){
                throw new Exception("You can't withdraw an amount that is less than or equal to zero");
            }
            else if (amount > accountBalance) {
                 throw new Exception("You can't withdraw an amount that is more than your balance");
            }
            else{
                accountBalance -= amount;
            }
        }
    }


    public void setAccountPin(int chosenPin) throws Exception {
        String chosenPinString = String.valueOf(chosenPin);
        if (chosenPinString.length() == 4) {
            accountPin = chosenPin;
        }
        else{
            throw new Exception("Your pin has to be a four(4) digit number");
        }
    }

    public int getAccountPin() {
        return accountPin;
    }

//    public String getErrorMessage(){
//        return error;
//    }
}
