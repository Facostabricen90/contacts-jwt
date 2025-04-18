package qasoft.cotacts_jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import qasoft.cotacts_jwt.Model.Contact;
import qasoft.cotacts_jwt.Repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> listAllContacts() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return contactRepository.findByNameUser(username);
    }


    public Contact addContact(Contact contact) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        contact.setNameUser(username);

        return contactRepository.save(contact);
    }


    public Optional<Contact> getContactById(Integer id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(Integer id, Contact contactDetails) {
        return contactRepository.findById(id).map(contact -> {
            contact.setName(contactDetails.getName()); // Cambiar los campos correspondientes
            contact.setEmail(contactDetails.getEmail()); // Ejemplo
            contact.setPhone(contactDetails.getPhone()); // Ejemplo
            return contactRepository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contacto no encontrado con ID: " + id));
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }

}
