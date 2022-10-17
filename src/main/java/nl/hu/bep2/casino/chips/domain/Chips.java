package nl.hu.bep2.casino.chips.domain;

import nl.hu.bep2.casino.chips.domain.exception.NegativeNumberException;
import nl.hu.bep2.casino.chips.domain.exception.NotEnoughChipsException;
import nl.hu.bep2.casino.security.data.UserRepository;
import nl.hu.bep2.casino.security.domain.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chips {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name= "username")
    private User user;
  //  private String username;

    private Long amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    private UserRepository userRepository;

    public Chips() {
    }

    public Chips(String username, Long amount) {
        this.user = userRepository.findByUsername(username).orElse(null);
        this.amount = amount;
    }

    public void withdraw(Long amountToWithdraw) {
        if (amountToWithdraw < 0) {
            throw new NegativeNumberException("Cannot withdraw a negative amount: " + amountToWithdraw);
        }

        long newAmount = this.amount - amountToWithdraw;

        if (newAmount < 0) {
            throw new NotEnoughChipsException(
                    String.format(
                            "Cannot withdraw %d chips: %d chips remaining",
                            amountToWithdraw,
                            this.amount
                    )
            );
        }

        this.amount = newAmount;
    }

    public void deposit(Long amountToDeposit) {
        if (amountToDeposit < 0) {
            throw new NegativeNumberException("Cannot deposit a negative amount: " + amountToDeposit);
        }

        this.amount = this.amount + amountToDeposit;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Long getAmount() {
        return amount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
