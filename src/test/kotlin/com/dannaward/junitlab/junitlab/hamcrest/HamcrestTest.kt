package com.dannaward.junitlab.junitlab.hamcrest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class HamcrestTest {
    class InsufficientFundsException(message: String) : RuntimeException(message)

    class Account(val name: String) {
        var balance: Int = 0
            private set

        fun deposit(dollars: Int) {
            balance += dollars
        }

        fun withdraw(dollars: Int) {
            if (balance < dollars) {
                throw InsufficientFundsException("balance only $balance")
            }
            balance -= dollars
        }

        fun hasPositiveBalance(): Boolean {
            return balance > 0
        }
    }

    class Customer {
        private val accounts = mutableListOf<Account>()

        fun add(account: Account) {
            accounts.add(account)
        }

        fun getAccounts(): Iterator<Account> {
            return accounts.iterator()
        }
    }

    private lateinit var account: Account

    @BeforeEach
    fun createAccount() {
        account = Account("an account name")
    }

    @Test
    fun hasPositiveBalance() {
        // when
        account.deposit(50)

        // then
        assertTrue { account.hasPositiveBalance() }
    }

    @Test
    fun deposit() {
        // when
        account.deposit(50)

        // then
        assertThat(account.balance).isEqualTo(50)
    }

    @Test
    fun depositIncreaseBalance() {
        // when
        account.deposit(50)

        // then
        // NOTE: this isn't a meaningful test
        assertThat(account.balance).isGreaterThan(0)
    }
}