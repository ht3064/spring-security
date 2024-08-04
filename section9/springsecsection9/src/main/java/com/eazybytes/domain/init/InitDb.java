package com.eazybytes.domain.init;

import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.accounttransaction.domain.AccountTransaction;
import com.eazybytes.domain.card.domain.Card;
import com.eazybytes.domain.customer.domain.Authority;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.loan.domain.Loan;
import com.eazybytes.domain.notice.domain.Notice;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.eazybytes.domain.account.domain.Account.createAccount;
import static com.eazybytes.domain.accounttransaction.domain.AccountTransaction.createAccountTransaction;
import static com.eazybytes.domain.card.domain.Card.createCard;
import static com.eazybytes.domain.customer.domain.Authority.createAuthority;
import static com.eazybytes.domain.customer.domain.Customer.createCustomer;
import static com.eazybytes.domain.loan.domain.Loan.createLoan;
import static com.eazybytes.domain.notice.domain.Notice.createNotice;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void Init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            Customer customer = createCustomer(
                    "Happy",
                    "happy@example.com",
                    "9876548337",
                    "$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2",
                    "admin");

            em.persist(customer);

            Account account = createAccount(
                    186576453434L,
                    customer,
                    "Savings",
                    "123 Main Street, New York");

            em.persist(account);

            AccountTransaction accountTransaction1 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(7),
                    "Coffee Shop",
                    "Withdrawal",
                    30,
                    34500,
                    LocalDateTime.now().minusDays(7));

            AccountTransaction accountTransaction2 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(6),
                    "Uber",
                    "Withdrawal",
                    100,
                    34400,
                    LocalDateTime.now().minusDays(6));

            AccountTransaction accountTransaction3 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(5),
                    "Self Deposit",
                    "Deposit",
                    500,
                    34900,
                    LocalDateTime.now().minusDays(5));

            AccountTransaction accountTransaction4 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(4),
                    "Ebay",
                    "Withdrawal",
                    600,
                    34300,
                    LocalDateTime.now().minusDays(4));

            AccountTransaction accountTransaction5 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(2),
                    "OnlineTransfer",
                    "Deposit",
                    700,
                    35000,
                    LocalDateTime.now().minusDays(2));

            AccountTransaction accountTransaction6 = createAccountTransaction(
                    UUID.randomUUID().toString(),
                    account,
                    customer,
                    LocalDateTime.now().minusDays(1),
                    "Amazon.com",
                    "Withdrawal",
                    100,
                    34900,
                    LocalDateTime.now().minusDays(1));

            em.persist(accountTransaction1);
            em.persist(accountTransaction2);
            em.persist(accountTransaction3);
            em.persist(accountTransaction4);
            em.persist(accountTransaction5);
            em.persist(accountTransaction6);

            Loan loan1 = createLoan(
                    customer,
                    LocalDateTime.of(2020, 10, 13, 0, 0),
                    "Home",
                    200000,
                    50000,
                    150000,
                    LocalDateTime.of(2020, 10, 13, 0, 0));

            Loan loan2 = createLoan(
                    customer,
                    LocalDateTime.of(2020, 6, 6, 0, 0),
                    "Vehicle",
                    40000,
                    10000,
                    30000,
                    LocalDateTime.of(2020, 6, 6, 0, 0));

            Loan loan3 = createLoan(
                    customer,
                    LocalDateTime.of(2018, 2, 14, 0, 0),
                    "Home",
                    50000,
                    10000,
                    40000,
                    LocalDateTime.of(2018, 2, 14, 0, 0));

            Loan loan4 = createLoan(
                    customer,
                    LocalDateTime.of(2018, 2, 14, 0, 0),
                    "Personal",
                    10000,
                    3500,
                    6500,
                    LocalDateTime.of(2018, 2, 14, 0, 0));

            em.persist(loan1);
            em.persist(loan2);
            em.persist(loan3);
            em.persist(loan4);

            Card card1 = createCard(
                    "4565XXXX4656",
                    customer,
                    "Credit",
                    10000,
                    500,
                    9500);

            Card card2 = createCard(
                    "3455XXXX8673",
                    customer,
                    "Credit",
                    7500,
                    600,
                    6900);

            Card card3 = createCard(
                    "2359XXXX9346",
                    customer,
                    "Credit",
                    20000,
                    4000,
                    16000);

            em.persist(card1);
            em.persist(card2);
            em.persist(card3);

            Notice notice1 = createNotice(
                    "Home Loan Interest rates reduced",
                    "Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            Notice notice2 = createNotice(
                    "Net Banking Offers",
                    "Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            Notice notice3 = createNotice(
                    "Mobile App Downtime",
                    "The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            Notice notice4 = createNotice(
                    "E Auction notice",
                    "There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            Notice notice5 = createNotice(
                    "Launch of Millennia Cards",
                    "Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            Notice notice6 = createNotice(
                    "COVID-19 Insurance",
                    "EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details",
                    LocalDateTime.now().minusDays(30),
                    LocalDateTime.now().plusDays(30));

            em.persist(notice1);
            em.persist(notice2);
            em.persist(notice3);
            em.persist(notice4);
            em.persist(notice5);
            em.persist(notice6);

            Authority authority1 = createAuthority(
                    customer,
                    "ROLE_USER");

            Authority authority2 = createAuthority(
                    customer,
                    "ROLE_ADMIN");

            em.persist(authority1);
            em.persist(authority2);
        }
    }
}