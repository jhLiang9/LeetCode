package design;

/**
 * 2043. 简易银行系统
 * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
 * boolean transfer(int account1, int account2, long money) 从编号为account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
 * boolean deposit(int account, long money) 向编号为account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 */
public class Bank {
    long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!isAccountValid(account1) || !isAccountValid(account2)) return false;
        long valid = balance[account1 - 1] - money;
        if (valid < 0) return false;
        balance[account2 - 1] += money;
        balance[account1 - 1] -= money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (!isAccountValid(account)) return false;
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!isAccountValid(account)) return false;
        long valid = balance[account - 1] - money;
        if (valid < 0) return false;
        balance[account - 1] -= money;
        return true;
    }

    public boolean isAccountValid(int account) {
        return account <= balance.length;
    }
}
