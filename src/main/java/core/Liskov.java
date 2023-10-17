package core;

/**
 * core.Liskov substitution principle used to subclass should be replaced by the superclass.
 * If we substitute the reference of superclass to subclass then should not break.
 */

public class Liskov {

    public static void main(String[] args) {
        AccountService accountService = null;
        FixedDeposit fd = new FixedDeposit();
        accountService = new FixedDeposit();
        System.out.println("Before substitution-->");
        fd.deposit();
        System.out.println("After superclass substitution:-->");
        accountService.deposit();
    }
}

abstract class AccountService {
    abstract void deposit();
}

class CurrentAccount extends AccountService {
    @Override
    void deposit(){
        System.out.println("Additional val for current account.");
    }
}

class FixedDeposit extends AccountService {
    @Override
    void deposit() {
        System.out.println("Additional FD val");
    }
}

class SavingsAccount extends AccountService {
    @Override
    void deposit() {
        System.out.println("Additional val");
    }

    void withdraw(){
        System.out.println("Withdraw");
    }
}