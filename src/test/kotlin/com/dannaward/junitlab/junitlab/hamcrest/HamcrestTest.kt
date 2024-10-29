package com.dannaward.junitlab.junitlab.hamcrest

import com.dannaward.junitlab.junitlab.util.ExpectToFail
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
//import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*

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
        assertThat(account.balance, equalTo(50))
    }

    @Test
    fun depositIncreaseBalance() {
        // when
        account.deposit(50)

        // then
        // NOTE: this isn't a meaningful test
        assertThat(account.balance, greaterThan(0))
    }

    @Test
    @Ignore
    @ExpectToFail
    fun compareArrayListFailing() {
        assertThat(arrayListOf("a", "b", "c"), equalTo(arrayListOf("a", "b")))
    }

    @Test
    fun compareArrayListPassing() {
        assertThat(arrayListOf("a", "b", "c"), equalTo(arrayListOf("a", "b", "c")))
    }

    @Test
    fun accountNameIsNotCorrect() {
        assertNotEquals(account.name, "random name")
    }

    @Test
    fun accountNameNotNull() {
        // it's often regarded meaningless to test for null
        assertNotNull(account.name)

        // rather test if it's equal
        assertEquals(account.name, "an account name")
    }

    @Test
    @Ignore
    @ExpectToFail
    fun floatPrecisionFailing() {
        // Expected :6.959999999999999
        // Actual   :6.96
        assertEquals(2.32 * 3, 6.96)
    }

    @Test
    fun floatPrecisionPassingButNotIntuitive() {
        // this is passing but it's not intuitive
        assertTrue { Math.abs((2.32 * 3) - 6.96) < 0.0005 }
    }

    @Test
    fun floatPrecisionPassingAndIntuitive() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005))
    }
}