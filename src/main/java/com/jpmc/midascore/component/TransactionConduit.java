package com.jpmc.midascore.component;


import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepo;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConduit {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DatabaseConduit db;

    @Autowired
    private TransactionRepo transactionRepo;

    public boolean validateTransaction(Transaction t1){
        UserRecord sender = userRepo.findById(t1.getSenderId());
        if (sender.getId() == null)
            return false;
        UserRecord recipient = userRepo.findById(t1.getRecipientId());
        if (recipient.getId() == null)
            return false;
        return !(sender.getBalance() < t1.getAmount());
    }

    public void doTransaction(Transaction t){
        if(validateTransaction(t)){
            UserRecord sender = userRepo.findById(t.getSenderId());
            UserRecord recipient = userRepo.findById(t.getRecipientId());
            TransactionRecord t1 = new TransactionRecord(
                    sender,
                    recipient,
                    t.getAmount()
            );
            sender.setBalance(sender.getBalance()-t.getAmount());
            recipient.setBalance(recipient.getBalance()+t.getAmount());

            db.save(sender);
            db.save(recipient);
        }
    }
}
