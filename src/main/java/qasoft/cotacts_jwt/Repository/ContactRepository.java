package qasoft.cotacts_jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qasoft.cotacts_jwt.Model.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository <Contact, Integer>{
    List<Contact> findByIdUser(String nameUser);
}
