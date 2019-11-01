package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.IllegalAmountException;
import com.priceminister.account.implementation.*;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
	private static final double DELTA = 0.00001D;

	private static final double ZERO_AMOUNT = 0D;

	private static final double ILLEGAL_AMOUNT = -10D;

	private static final double LEGAL_AMOUNT = 10D;

	private Account customerAccount;
    private AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
    	Double balance = customerAccount.getBalance();
    	assertNotNull(balance);
    	assertEquals(ZERO_AMOUNT, balance, DELTA);
    	assertTrue(balance >= ZERO_AMOUNT);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     * @throws IllegalAmountException 
     */
    @Test
    public void testAddPositiveAmount() throws IllegalAmountException {
        customerAccount.add(LEGAL_AMOUNT);
        assertEquals(LEGAL_AMOUNT, customerAccount.getBalance(), DELTA);
        assertNotSame(customerAccount.getBalance() - LEGAL_AMOUNT, customerAccount.getBalance());
        assertTrue(customerAccount.getBalance() >= ZERO_AMOUNT);
    }
    
    /**
     * Adds Zero amount to the account and checks that the new balance is as expected.
     * @throws IllegalAmountException 
     */
    @Test
    public void testAddZeroAmount() throws IllegalAmountException {
        customerAccount.add(ZERO_AMOUNT);
        assertEquals(ZERO_AMOUNT, customerAccount.getBalance(), DELTA);
        assertTrue(customerAccount.getBalance() >= ZERO_AMOUNT);
    }
    

    /**
     *  Handle operations of adding negative amounts to the balance (even it is not possible in real life :) )
     * @throws IllegalAmountException
     */
    @Test(expected = IllegalAmountException.class)
    public void testAddNegativeAmount() throws IllegalAmountException {
        customerAccount.add(ILLEGAL_AMOUNT);
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalAmountException
     * @throws IllegalBalanceException
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalAmountException, IllegalBalanceException {
    	customerAccount.add(LEGAL_AMOUNT);
    	customerAccount.withdrawAndReportBalance((2 * LEGAL_AMOUNT), rule);
    }
    
    /**
     * Handle operations of withdrawing a negative (illegal) amount 
     * @throws IllegalAmountException
     * @throws IllegalBalanceException
     */
    @Test(expected = IllegalAmountException.class)
    public void testWithdrawAndReportBalanceNegativeAmountException() throws IllegalAmountException, IllegalBalanceException {
    	customerAccount.add(LEGAL_AMOUNT);
    	customerAccount.withdrawAndReportBalance(ILLEGAL_AMOUNT, rule);
    }
    
    /**
     * Withdraw a legal (positive) amount and return the new balance
     * @throws IllegalAmountException
     * @throws IllegalBalanceException
     */
    @Test
    public void testWithdrawAndReportBalance() throws IllegalAmountException, IllegalBalanceException {
    	customerAccount.add(LEGAL_AMOUNT);
    	Double newBalance = customerAccount.withdrawAndReportBalance(LEGAL_AMOUNT, rule);
    	assertEquals(ZERO_AMOUNT, newBalance, DELTA);
    	assertTrue(newBalance >= ZERO_AMOUNT);
    }
    
    /**
     * Withdraw a zero amount and return the new balance
     * @throws IllegalAmountException
     * @throws IllegalBalanceException
     */
    @Test
    public void testWithdrawZeroAmountAndReportBalance() throws IllegalAmountException, IllegalBalanceException {
    	customerAccount.add(LEGAL_AMOUNT);
    	Double newBalance = customerAccount.withdrawAndReportBalance(ZERO_AMOUNT, rule);
    	assertEquals(LEGAL_AMOUNT, newBalance, DELTA);
    	assertTrue(newBalance >= ZERO_AMOUNT);
    }
    
}
