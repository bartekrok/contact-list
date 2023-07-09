import { useEffect, useState } from "react";
import Contact from "./Contact";
import { useSelector } from "react-redux";
import axios from "axios";
import AddForm from "./AddForm";

const ContactList = () => {
  const [contacts, setContacts] = useState([]);
  const [isAdd, setIsAdd] = useState(false);
  const token = useSelector((state) => state.auth.token);

  useEffect(() => {
    axios(`http://localhost:8080/contacts`) // Pobranie danych kontaktow z bazy danych
      .then((response) => response.data)
      .then((data) => setContacts(data));
  }, []);

  const handleAdd = () => { // Dodanie kontaktu
    setIsAdd(!isAdd);
  };

  return (
    <div>
      <ul>
        {contacts.map((contact) => (
          <li key={contact.id}>
            <Contact key={contact.id} id={contact.id} />
          </li>
        ))}
      </ul>
      {token ? <button onClick={handleAdd}>Add</button> : null}
      {isAdd ? <AddForm /> : null}
    </div>
  );
};

export default ContactList;
